package base;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Set;

/**
 * description:
 *
 * @author zhunhuang, 2020/3/3
 */
public class ValidateVO {

    /**
     * 校验是否通过
     */
    private boolean success = true;

    /**
     * 参数校验错误信息
     */
    private String message = StringUtils.EMPTY;

    private String shortDesc = StringUtils.EMPTY;

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

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    /**
     * 参数校验非法属性
     * key - 非法属性名称
     * value - 非法属性错误信息
     */
    private HashMap<String, String> invalidProperties = new HashMap<>();


    /**
     * 添加非法属性信息
     *
     * @param property
     * @param message
     */
    public void addProperty(String property, String message) {
        invalidProperties.put(property, message);
    }


    /**
     * 列取非法属性集合
     *
     * @return 若无非法属性则返回空集合
     */
    public Set<String> propertySet() {
        return invalidProperties.keySet();
    }

    @Override
    public String toString() {
        return "{\"ValidateVO\":{"
                + "\"success\":\"" + success + "\""
                + ", \"message\":\"" + message + "\""
                + ", \"shortDesc\":\"" + shortDesc + "\""
                + ", \"invalidProperties\":" + invalidProperties
                + "}}";
    }
}
