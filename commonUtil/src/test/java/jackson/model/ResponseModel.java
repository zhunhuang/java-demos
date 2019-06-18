package jackson.model;

import java.io.Serializable;

/**
 * description:
 * create: 2019-05-10
 *
 * @author zhun.huang
 */
public class ResponseModel<T> implements Serializable {
	private static final long serialVersionUID = 1105675741448260667L;

	private boolean success;

	private int errorCode;

	private String errorMsg;

	private T data;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResponseModel<T> success() {
		this.success = true;
		return this;
	}

	public ResponseModel<T> success(T data) {
		this.success = true;
		this.errorCode = 0;
		this.data = data;
		return this;
	}

	public ResponseModel<T> fail(int errCode, String errMsg) {
		this.success = false;
		this.errorCode = errCode;
		this.errorMsg = errMsg;
		return this;
	}

	@Override
	public String toString() {
		return "{\"ResponseModel\":{"
				+ "\"success\":\"" + success + "\""
				+ ", \"errorCode\":\"" + errorCode + "\""
				+ ", \"errorMsg\":\"" + errorMsg + "\""
				+ ", \"data\":" + data
				+ "}}";
	}
}
