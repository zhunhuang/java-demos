package com.test.nolan.statemachine.pay.core;

import com.test.nolan.statemachine.pay.bean.PayFlowContext;
import com.test.statemachine.api.ExecutorInfo;
import com.test.statemachine.api.FlowTrait;
import com.test.statemachine.api.recorder.ActionExecutedRecord;
import com.test.statemachine.core.AbstractFlowStateMachineManager;
import com.test.statemachine.core.FlowStateMachineConfig;
import com.test.statemachine.core.defination.FlowPath;
import com.test.statemachine.core.defination.FlowPathFinder;
import com.test.statemachine.core.service.FlowActionExecuteService;
import com.test.statemachine.core.service.UnreliableFlowActionExecuteServiceImpl;
import com.test.statemachine.spi.context.BaseContext;
import com.test.nolan.statemachine.pay.bean.PayFlowTrait;
import com.test.nolan.statemachine.pay.condition.InitPayCondition;
import com.test.nolan.statemachine.pay.service.OrderPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zhun.huang
 * @create: 2017-12-13 下午10:09
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class UnreliableFlowStateMachineManagerImpl extends AbstractFlowStateMachineManager<PayFlowContext, PayFlowTrait> {

    @Resource
    private InitPayCondition initPayCondition;
    @Resource
    private OrderPayService orderPayService;

    public UnreliableFlowStateMachineManagerImpl(FlowStateMachineConfig<PayFlowContext, PayFlowTrait> config) {
        super(config);
    }

    private static final Logger logger = LoggerFactory.getLogger(com.test.statemachine.core.UnreliableFlowStateMachineManagerImpl.class);

    @Override
    protected FlowActionExecuteService getExecuteService(FlowPathFinder flowPathFinder, FlowStateMachineConfig config) {
        return new UnreliableFlowActionExecuteServiceImpl(flowPathFinder, config);
    }

    /**
     * 修改负载Id的目标流程为目标状态.
     */
    @Override
    public PayFlowTrait changeState(String flowId, String toState, int currentVersion, ExecutorInfo executorInfo) throws Exception {
        return changeState(flowId, toState, currentVersion, executorInfo, null);
    }

    @Override
    public PayFlowTrait changeState(String flowId, String toState, int currentVersion, ExecutorInfo executorInfo, String changeReason) throws Exception {
        PayFlowContext context = (PayFlowContext) config.getFlowContextFactory().createContext(flowId, executorInfo, config.getMachineKey());
        invokeTransition(context, toState, changeReason);
        return context.getFlow();
    }

    /**
     * 修改负载对象的目标流程为目标状态.
     */
    @Override
    public PayFlowTrait changeState(FlowTrait flow, String toState, int currentVersion, ExecutorInfo executorInfo) throws Exception {
        return changeState(flow, toState, currentVersion, executorInfo, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public PayFlowTrait changeState(FlowTrait flow, String toState, int currentVersion, ExecutorInfo executorInfo, String changeReason) throws Exception {
        logger.debug("{} 状态机执行变更状态", flow.flowId());
        PayFlowContext context = (PayFlowContext) config.getFlowContextFactory().createContext(flow, executorInfo, config.getMachineKey());
        invokeTransition(context, toState, changeReason);
        return context.getFlow();
    }

    @Override
    @SuppressWarnings("unchecked")
    public FlowTrait generateFlow(FlowTrait flow, ExecutorInfo executorInfo) throws Exception {
        PayFlowContext context = (PayFlowContext) config.getFlowContextFactory().createContext(flow, executorInfo, config.getMachineKey());
        logger.info("校验 {} 是否满足初始化条件", flow.flowId());
        initPayCondition.check(context);
        logger.info("{} 校验条件[{}]成功", flow.flowId(),initPayCondition.getLabel());
        List<ActionExecutedRecord> actionExecutedRecords = executeService.generateAndSaveActionRecord(context);
        executeService.executeActions(context.getFlow(), context, actionExecutedRecords);
        return context.getFlow();
    }

    @Override
    protected <T extends BaseContext> void invokeTransition(T context, String targetState, String changeReason) throws Exception {
        logger.debug("{} 执行状态跃迁", context.getFlowId());
        FlowPath flowPath = executeService.changeStateAndSaveActionRecord(context, targetState, changeReason);
        // 执行所有plugin的before
        executeService.executeBeforePlugin(context.getFlow(), context, flowPath.getPlugins());
        try {
            executeService.checkMeetAllConditions(context.getFlow(), context, flowPath.getConditions());
            // 执行此次跃迁的所有action
            executeService.executeActions(context.getFlow(), context, flowPath.getExecutedRecords());
            // 执行完毕，变更目标状态
            context.getFlow().changeState(targetState);
            // 更新订单
            orderPayService.updateFlow((PayFlowTrait) context.getFlow(), orderPayService.buildOperationLog((PayFlowContext) context));
        } catch (Throwable t) {
            throw new Exception(t);
        } finally {
            // 执行所有plugin的after
            executeService.executeAfterPlugin(context.getFlow(), context, flowPath.getPlugins());
        }
    }
}
