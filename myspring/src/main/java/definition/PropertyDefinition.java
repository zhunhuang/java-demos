package definition;

/**
 * @author: zhun.huang
 * @create: 2017-12-30 下午3:53
 * @email: nolan.zhun@gmail.com
 * @description: 属性定义
 */
public class PropertyDefinition {

    /**
     * 属性名
     */
    private String name;

    /**
     * 属性值
     */
    private String value;

    /**
     * 属性值引用
     */
    private String ref;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
