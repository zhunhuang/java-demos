package com.test.nolan.statemachine.order.bean;

import com.test.nolan.statemachine.order.enums.OrderStatus;
import com.test.statemachine.api.ActionRecorderStoreData;
import com.test.statemachine.api.FlowTrait;

/**
 * @author: zhun.huang
 * @create: 2017-12-07 下午7:26
 * @email: nolan.zhun@gmail.com
 * @description: 该类将在所有状态机的跳转过程的代码中传递信息，例如用户名，订单号，订单状态等等,还有执行记录
 */
public class OrderFlowTrait implements FlowTrait{

    /**
     * 订单信息
     */
    private HotelOrderBean hotelOrderBean;

    private ActionRecorderStoreData actionRecorderStoreData = new ActionRecorderStoreData();

    public OrderFlowTrait(HotelOrderBean hotelOrderBean) {
        this.hotelOrderBean = hotelOrderBean;
    }

    @Override
    public String flowId() {
        return hotelOrderBean.getOrderNo();
    }

    @Override
    public String currentState() {
        return hotelOrderBean.getStatus().name();
    }

    @Override
    public void changeState(String s) {
        hotelOrderBean.setStatus(OrderStatus.valueOf(s));
    }

    @Override
    public int flowVersion() {
        return 0;
    }

    @Override
    public ActionRecorderStoreData getActionRecorderStoreData() {
        return actionRecorderStoreData;
    }

    @Override
    public void setActionRecorderStoreData(ActionRecorderStoreData actionRecorderStoreData) {
        this.actionRecorderStoreData = actionRecorderStoreData;
    }

    public HotelOrderBean getHotelOrderBean() {
        return hotelOrderBean;
    }

    public void setHotelOrderBean(HotelOrderBean hotelOrderBean) {
        this.hotelOrderBean = hotelOrderBean;
    }


    @Override
    public String toString() {
        return "OrderFlowTrait{" +
                "hotelOrderBean=" + hotelOrderBean +
                ", actionRecorderStoreData=" + actionRecorderStoreData +
                '}';
    }
}
