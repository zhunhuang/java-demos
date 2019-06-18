package http;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacksonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

    private static final ObjectMapper mapper = getInstance();

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static ObjectMapper getInstance(){
        ObjectMapper mapper = new ObjectMapper();
        // 序列化时不进行格式化
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        // 反序列化时, 遇到不存在的属性不报错
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 允许json标准规范以外的字符
        mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        // 允许注释
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 允许NAN等
        mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
        mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        // 允许单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 允许未用引号括起来的控制自负
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 允许字段名 不用引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        return mapper;
    }

    public static String serialize(Object data) {
        try {
            return mapper.writeValueAsString(data);
        } catch (IOException e) {
            logger.error("serialize data error", e);
            return null;
        }
    }

    public static <T> T deSerialize(String content, Class<T> clazz) {
        try {
            return mapper.readValue(content, clazz);
        } catch (IOException e) {
            logger.error("deserialize object error: {}", content, e);
            return null;
        }
    }

    public static <T> List<T> deSerializeList(String content, Class<T> clazz) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
            return mapper.readValue(content, javaType);
        } catch (Exception e) {
            logger.error("deserialize list error", content, e);
            return ListUtils.EMPTY_LIST;
        }
    }

    public static <K, V> Map<K, V> deSerializeMap(String content, Class<K> keyClazz, Class<V> valueClazz) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, keyClazz, valueClazz);
            return mapper.readValue(content, javaType);
        } catch (Exception e) {
            logger.error("deserialize_map_error", content, e);
            return null;
        }
    }

    public static <T> T deSerialize(String content, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(content, typeReference);
        } catch (Exception e) {
            logger.error("deserialize typeReference error", content, e);
            return null;
        }
    }

    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        try {
            return mapper.convertValue(fromValue, toValueType);
        } catch (IllegalArgumentException e) {
            logger.error("convert object error: {}", fromValue.toString(), e);
            return null;
        }
    }

    public static Map<?, ?> jsonToMap(String json) throws IOException {
        return mapper.readValue(json, HashMap.class);
    }
}
