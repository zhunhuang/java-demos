package definition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhun.huang
 * @create: 2017-12-30 下午3:52
 * @email: nolan.zhun@gmail.com
 * @description: bean定义
 */
public class BeanDefinition {

    private String beanId;

    private String className;

    private List<PropertyDefinition> propertyDefinitionList = new ArrayList<>();

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<PropertyDefinition> getPropertyDefinitionList() {
        return propertyDefinitionList;
    }

    public void setPropertyDefinitionList(List<PropertyDefinition> propertyDefinitionList) {
        this.propertyDefinitionList = propertyDefinitionList;
    }
}
