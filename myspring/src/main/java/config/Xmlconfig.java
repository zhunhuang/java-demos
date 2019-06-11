package config;

import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import definition.BeanDefinition;
import definition.PropertyDefinition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import util.ResourceUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhun.huang
 * @create: 2017-12-30 下午4:29
 * @email: nolan.zhun@gmail.com
 * @description:
 */
public class Xmlconfig {

    private final static String BEAN_ELEMENT_NAME = "bean";
    private final static String ID_ELEMENT_NAME = "id";
    private final static String CLASS_ELEMENT_NAME = "class";
    private final static String PROPERTY_ELEMENT_NAME = "property";
    private final static String NAME_ELEMENT_NAME = "name";
    private final static String VALUE_ELEMENT_NAME = "value";
    private final static String REF_ELEMENT_NAME = "ref";

    public static Map<String, BeanDefinition> getConfig(String path) throws Exception {

        Map<String, BeanDefinition> config = new HashMap<>();

        DocumentBuilderFactory builderFactory = new DocumentBuilderFactoryImpl();
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(ResourceUtils.getFile(path));

        Element element = document.getDocumentElement();

        NodeList beanList = element.getElementsByTagName(BEAN_ELEMENT_NAME);

        for (int i = 0; i < beanList.getLength(); i++) {
            Element item = (Element)beanList.item(i);
            BeanDefinition beanDefinition = parseBeanDefinition(item);
            config.put(beanDefinition.getBeanId(),beanDefinition);
        }
        return config;
    }

    private static BeanDefinition parseBeanDefinition(Element element) {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanId(element.getAttribute(ID_ELEMENT_NAME));
        beanDefinition.setClassName(element.getAttribute(CLASS_ELEMENT_NAME));
        NodeList propertyList = element.getElementsByTagName(PROPERTY_ELEMENT_NAME);
        for (int i = 0; i < propertyList.getLength(); i++) {
            Element item = (Element)propertyList.item(i);
            PropertyDefinition propertyDefinition = parsePropertyDefinition(item);
            beanDefinition.getPropertyDefinitionList().add(propertyDefinition);
        }
        return beanDefinition;
    }

    private static PropertyDefinition parsePropertyDefinition(Element element) {
        PropertyDefinition propertyDefinition = new PropertyDefinition();
        propertyDefinition.setName(element.getAttribute(NAME_ELEMENT_NAME));
        propertyDefinition.setValue(element.getAttribute(VALUE_ELEMENT_NAME));
        propertyDefinition.setRef(element.getAttribute(REF_ELEMENT_NAME));
        return propertyDefinition;
    }
}
