package spi;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import spi.model.ClearDetail;
import spi.model.VoucherModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author zhunhuang, 2020/10/30
 */
public class ClearService {

    static Map<Integer, BizActorSupplier> configs = new HashMap<>();

    static {
        try {
            String fileRead = FileUtils.fileRead("./clear-biz-actor-config");
            String[] split = fileRead.split("-");
            for (String s : split) {
                configs.put(0,(BizActorSupplier)Class.forName(s).newInstance());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public List<ClearDetail> doClear(VoucherModel voucher) {
        List<String> tradeActor = supplyActor(voucher);
        for (String s : tradeActor) {
            ClearDetail clearDetail = new ClearDetail();
            clearDetail.settleId = String.valueOf(tradeActor);
        }
        return Lists.newArrayList();
    }

    public List<String> supplyActor(VoucherModel voucher) {
        BizActorSupplier bizActorSupplier = configs.get(voucher.bizCode);
        return bizActorSupplier.supplyActor(voucher);
    }

}
