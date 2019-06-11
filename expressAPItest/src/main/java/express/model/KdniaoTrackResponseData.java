package express.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * description:
 *
 * @author zhun.huang 2019-03-18
 */
public class KdniaoTrackResponseData {

    @JsonProperty(value = "EBusinessID")
    private String eBusinessID;

    @JsonProperty(value = "OrderCode")
    private String orderCode;

    @JsonProperty(value = "ShipperCode")
    private String shipperCode;

    /**
     * 物流单号
     */
    @JsonProperty(value = "LogisticCode")
    private String logisticCode;

    @JsonProperty(value = "Success")
    private boolean success;

    @JsonProperty(value = "state")
    private String state;

    @JsonProperty(value = "Reason")
    private String reason;

    /**
     * 物流信息
     */
    @JsonProperty(value = "Traces")
    private Trace[] traces;

    public String geteBusinessID() {
        return eBusinessID;
    }

    public void seteBusinessID(String eBusinessID) {
        this.eBusinessID = eBusinessID;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Trace[] getTraces() {
        return traces;
    }

    public void setTraces(Trace[] traces) {
        this.traces = traces;
    }

    @Override
    public String toString() {
        return "{\"KdniaoTrackResponseData\":{"
                + "\"eBusinessID\":\"" + eBusinessID + "\""
                + ", \"orderCode\":\"" + orderCode + "\""
                + ", \"shipperCode\":\"" + shipperCode + "\""
                + ", \"logisticCode\":\"" + logisticCode + "\""
                + ", \"success\":\"" + success + "\""
                + ", \"state\":\"" + state + "\""
                + ", \"reason\":\"" + reason + "\""
                + ", \"traces\":" + Arrays.toString(traces)
                + "}}";
    }

    public static class Trace {

        @JsonProperty("AcceptTime")
        private String acceptTime;

        @JsonProperty("AcceptStation")
        private String acceptStation;

        @JsonProperty("Remark")
        private String remark;

        public String getAcceptTime() {
            return acceptTime;
        }

        public void setAcceptTime(String acceptTime) {
            this.acceptTime = acceptTime;
        }

        public String getAcceptStation() {
            return acceptStation;
        }

        public void setAcceptStation(String acceptStation) {
            this.acceptStation = acceptStation;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        @Override
        public String toString() {
            return "{\"Trace\":{"
                    + "\"acceptTime\":\"" + acceptTime + "\""
                    + ", \"acceptStation\":\"" + acceptStation + "\""
                    + ", \"remark\":\"" + remark + "\""
                    + "}}";
        }
    }
}
