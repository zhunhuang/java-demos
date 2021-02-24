package spi;

import spi.model.VoucherModel;

import java.util.List;

/**
 * description:
 *
 * @author zhunhuang, 2020/10/30
 */
public interface BizActorSupplier {


    List<String> supplyActor(VoucherModel voucher);
}
