package com.test.nolan.statemachine.bank.service;

import com.test.nolan.statemachine.common.util.OrderGenerator;
import com.test.nolan.statemachine.pay.bean.Constant;
import com.test.nolan.statemachine.bank.model.BankAccount;
import com.test.nolan.statemachine.bank.model.BankPayRecordModel;
import com.test.nolan.statemachine.common.service.MQMockService;
import com.test.nolan.statemachine.pay.enums.PayStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午11:38
 * @email: nolan.zhun@gmail.com
 * @description: 模拟银行操作
 */
@Service
public class BankMockService {
    private static final Logger logger = LoggerFactory.getLogger(BankMockService.class);

    private BankAccount userAccount = new BankAccount(Constant.userAccountId, 1000, 0);
    private BankAccount systemAccount = new BankAccount(Constant.systemAccountId, 1000, 0);
    private BankAccount hotelAccount = new BankAccount(Constant.hotelAccountId, 1000, 0);

    private Map<String, BankAccount> accountMap = new HashMap<>();

    {
        accountMap.put(userAccount.getAccountId(), userAccount);
        accountMap.put(systemAccount.getAccountId(), systemAccount);
        accountMap.put(hotelAccount.getAccountId(), hotelAccount);
    }

    @Resource
    private MQMockService mqMockService;

    /**
     * 支付转帐
     */
    public String mockTransfer(String fromAccountId, String toAccountId, int price) {
        String transactionId = generateTransactionId();
        BankAccount fromAccount = accountMap.get(fromAccountId);
        BankAccount toAccount = accountMap.get(toAccountId);
        if (fromAccount == null || toAccount == null) {
            logger.error("账户不存在, fromAccountId: {}, toAccountId: {}", fromAccountId, toAccountId);
            return "";
        }
        if (fromAccount.getRemainNum() < price) {
            logger.error("账户金额不足, 转出金额: {}, 剩余金额: {}", price, fromAccount.getRemainNum());
            return "";
        }
        BankPayRecordModel bankPayRecordModel = buildBankPayRecord(transactionId, fromAccountId, toAccountId, price);
        synchronized (this) {
            fromAccount.setRemainNum(fromAccount.getRemainNum() - price);
            fromAccount.addPayRecordBean(bankPayRecordModel);
            toAccount.setRemainNum(toAccount.getRemainNum() + price);
            toAccount.addPayRecordBean(bankPayRecordModel);
        }
        logger.info("转帐成功, 订单号: {}, 转出账户: {}, 转入账户: {}, 金额: {}", transactionId, fromAccountId, toAccountId, price);
        mqMockService.mockBankMessage(transactionId, PayStatus.PAY_SUCCESS);
        return transactionId;
    }

    /**
     * 退款
     */
    public boolean mockRefund(String transactionId, String fromAccountId, String toAccountId, int price) {
        BankAccount fromAccount = accountMap.get(fromAccountId);
        BankAccount toAccount = accountMap.get(toAccountId);
        if (fromAccount == null || toAccount == null) {
            logger.error("账户不存在, fromAccountId: {}, toAccountId: {}", fromAccountId, toAccountId);
            return false;
        }
        if (fromAccount.getRemainNum() < price) {
            logger.error("账户金额不足, 转出金额: {}, 剩余金额: {}", price, fromAccount.getRemainNum());
            return false;
        }
        BankPayRecordModel payRecordBean = fromAccount.getPayRecordBean(transactionId);
        if (payRecordBean == null || payRecordBean.getPayPrice() < price) {
            logger.error("找不到支付记录, 或退款金额大于支付金额", price);
            return false;
        }
        payRecordBean.setRefundPrice(price);
        payRecordBean.setStatus(PayStatus.REFUND_SUCCESS);
        synchronized (this) {
            fromAccount.setRemainNum(fromAccount.getRemainNum() - price);
            fromAccount.updatePayRecordBean(payRecordBean);
            toAccount.setRemainNum(toAccount.getRemainNum() + price);
            toAccount.updatePayRecordBean(payRecordBean);
        }
        logger.info("退款成功, 订单号: {}, 转出账户: {}, 转入账户: {}, 金额: {}", transactionId, fromAccountId, toAccountId, price);
        mqMockService.mockBankMessage(transactionId, PayStatus.PAY_SUCCESS);
        return true;
    }

    /**
     * 担保
     */
    public String mockGuarantee(String fromAccountId, String toAccountId, int guaranteePrice) {
        String transactionId = generateTransactionId();
        BankAccount fromAccount = accountMap.get(fromAccountId);
        BankAccount toAccount = accountMap.get(toAccountId);
        if (fromAccount == null || toAccount == null || guaranteePrice == 0) {
            logger.error("账户不存在, fromAccountId: {}, toAccountId: {}, guaranteePrice : {}", fromAccountId, toAccountId);
            return "";
        }
        if (fromAccount.getRemainNum() < guaranteePrice) {
            logger.error("账户金额不足, 担保金额: {}, 剩余金额: {}", guaranteePrice, fromAccount.getRemainNum());
            return "";
        }
        BankPayRecordModel bankPayRecordModel = buildBankPayRecord(transactionId, fromAccountId, toAccountId, 0);
        bankPayRecordModel.setGuaranteePrice(guaranteePrice);
        bankPayRecordModel.setStatus(PayStatus.GUARANTEE_SUCCESS);
        synchronized (this) {
            fromAccount.setRemainNum(fromAccount.getRemainNum() - guaranteePrice);
            fromAccount.setFrezzeNum(fromAccount.getFrezzeNum() + guaranteePrice);
            fromAccount.addPayRecordBean(bankPayRecordModel);
        }
        logger.info("担保成功, 担保账户: {}, 担保金额: {}", fromAccountId, guaranteePrice);
        mqMockService.mockBankMessage(transactionId, PayStatus.GUARANTEE_SUCCESS);
        return transactionId;
    }

    /**
     * 担保扣款
     */
    public boolean mockGuaranteeConfrim(String transactionId, String fromAccountId) {
        BankAccount fromAccount = accountMap.get(fromAccountId);
        if (fromAccount == null || transactionId == null) {
            logger.error("账户不存在, fromAccountId: {}, transactionId: {}", fromAccountId, transactionId);
            return false;
        }
        BankPayRecordModel bankPayRecordModel = fromAccount.getPayRecordBean(transactionId);
        if (bankPayRecordModel == null) {
            logger.error("找不到对应的支付记录, fromAccountId: {}, transactionId: {}", fromAccountId, transactionId);
            return false;
        }
        if (fromAccount.getFrezzeNum() < bankPayRecordModel.getGuaranteePrice()) {
            logger.error("账户金额不足, 担保金额: {}, 剩余金额: {}", bankPayRecordModel.getGuaranteePrice(), fromAccount.getFrezzeNum());
            return false;
        }
        BankAccount toAccount = accountMap.get(bankPayRecordModel.getOuterBankCode());
        if (toAccount == null) {
            logger.error("找不到对应的收款账户, fromAccountId: {}, transactionId: {}", fromAccountId, transactionId);
            return false;
        }
        bankPayRecordModel.setStatus(PayStatus.GUARANTEE_CONFIRM_SUCCESS);
        synchronized (this) {
            fromAccount.setFrezzeNum(fromAccount.getFrezzeNum() - bankPayRecordModel.getGuaranteePrice());
            fromAccount.addPayRecordBean(bankPayRecordModel);
            toAccount.setRemainNum(toAccount.getRemainNum() + bankPayRecordModel.getGuaranteePrice());
            toAccount.addPayRecordBean(bankPayRecordModel);
        }
        logger.info("担保扣款成功, 转出账户: {}, 转入账户: {}, 担保扣款金额: {}", fromAccountId, toAccount.getAccountId(), bankPayRecordModel.getGuaranteePrice());
        mqMockService.mockBankMessage(transactionId, PayStatus.GUARANTEE_CONFIRM_SUCCESS);
        return true;
    }

    /**
     * 解冻or担保撤销
     */
    public boolean mockUnfreeze(String transactionId, String accountId, int unfreezePrice) {
        BankAccount account = accountMap.get(accountId);
        if (account == null) {
            logger.error("账户不存在, fromAccountId: {}", accountId);
            return false;
        }
        if (account.getRemainNum() < unfreezePrice) {
            logger.error("账户担保余额不足, 解冻金额: {}, 担保余额: {}", unfreezePrice, account.getFrezzeNum());
            return false;
        }
        BankPayRecordModel payRecordBean = account.getPayRecordBean(transactionId);
        if (payRecordBean == null || payRecordBean.getGuaranteePrice() < unfreezePrice) {
            logger.error("找不到支付记录, 或解冻金额大于担保金额", unfreezePrice);
            return false;
        }
        payRecordBean.setGuaranteeRevokePrice(unfreezePrice);
        payRecordBean.setStatus(PayStatus.GUARANTEE_REVOKE_SUCCESS);
        synchronized (this) {
            account.setRemainNum(account.getRemainNum() + unfreezePrice);
            account.setFrezzeNum(account.getFrezzeNum() - unfreezePrice);
            account.updatePayRecordBean(payRecordBean);
        }
        logger.info("解冻成功, 担保账户: {}, 解冻金额: {}", accountId, unfreezePrice);
        mqMockService.mockBankMessage(transactionId, PayStatus.GUARANTEE_REVOKE_SUCCESS);
        return true;
    }

    private BankPayRecordModel buildBankPayRecord(String transactionId, String fromAccountId, String toAccountId, int price) {
        Date now = new Date();
        BankPayRecordModel bankPayRecordModel = new BankPayRecordModel();
        bankPayRecordModel.setId(BankPayRecordModel.idCount.incrementAndGet());
        bankPayRecordModel.setTransactionId(transactionId);
        bankPayRecordModel.setBankCode(fromAccountId);
        bankPayRecordModel.setOuterBankCode(toAccountId);
        bankPayRecordModel.setPayPrice(price);
        bankPayRecordModel.setGuaranteePrice(0);
        bankPayRecordModel.setGuaranteeRevokePrice(0);
        bankPayRecordModel.setRefundPrice(0);
        bankPayRecordModel.setStatus(PayStatus.PAY_SUCCESS);

        bankPayRecordModel.setCreateTime(now);
        bankPayRecordModel.setLastUpdateTime(now);
        bankPayRecordModel.setPaidTime(now);
        return bankPayRecordModel;
    }

    private String generateTransactionId() {
        return "bk." + OrderGenerator.generatOrderNo();
    }

    public BankAccount getUserAccount() {
        return userAccount;
    }

    public BankAccount getSystemAccount() {
        return systemAccount;
    }

    public BankAccount getHotelAccount() {
        return hotelAccount;
    }
}
