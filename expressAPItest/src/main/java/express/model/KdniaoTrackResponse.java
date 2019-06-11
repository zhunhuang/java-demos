package express.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * description:
 *
 * @author zhun.huang 2019-03-18
 */
public class KdniaoTrackResponse {

    @JsonProperty(value = "ResponseData")
    private String responseData;

    @JsonProperty(value = "Success")
    private boolean success;

    @JsonProperty(value = "Message")
    private String message;

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{\"KdniaoTrackResponse\":{"
                + "\"responseData\":\"" + responseData + "\""
                + ", \"success\":\"" + success + "\""
                + ", \"message\":\"" + message + "\""
                + "}}";
    }
}
