package com.wego.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Json工具类
 * 亮点：模拟构造方法设计模式提供类似于阿里巴巴FastJSON的put方式构造JSON字符串的功能
 * @author hc
 */
public class JsonUtil {

    /**
     * 默认日期时间格式
     */
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Json序列化和反序列化转换器
     */
    static {
        //java8日期 Local系列序列化和反序列化模块
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //序列化
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        //反序列化
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));

        objectMapper.registerModule(javaTimeModule);

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        // 忽略json字符串中不识别的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 忽略无法转换的对象
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // PrettyPrinter 格式化输出
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        // NULL不参与序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 指定时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        /**
         * Date日期类型字符串全局处理, 默认格式为：yyyy-MM-dd HH:mm:ss
         * 局部处理某个Date属性字段接收或返回日期格式yyyy-MM-dd, 可采用@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")注解标注该属性
         */
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
    }

    /**
     * 将对象转换成字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        String s = null;
        try {
            s = obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 将对象转换成格式化后的字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * 字符串转对象
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (str == null || str.length() == 0 || clazz == null) {
            return null;
        }
        T t = null;
        try {
            t = clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 在字符串与集合对象转换时使用
     * @param str
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (str == null || str.length() == 0 || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 在字符串与集合对象转换时使用
     * @param str
     * @param collectionClazz
     * @param elementClazzes
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, Class<?> collectionClazz, Class<?>... elementClazzes) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, elementClazzes);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            return null;
        }
    }


    public static JsonBuilder builder() {
        return new JsonBuilder();
    }

    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\"主题\": {\"颜色\": \"黑色\", \"屏幕尺寸\": \"23.8英寸\"}, \"接口\": {\"Type-C\": \"不支持Type-C接口\", \"DP接口\": \"不支持DP接口\", \"DVI接口\": \"不支持DVI接口\", \"VGA接口\": \"支持VGA接口\", \"HDMI接口\": \"支持HDMI接口\", \"USB扩展/充电\": \"不支持USB扩展/充电\"}, \"显示\": {\"亮度\": \"280cd/㎡\", \"色数\": \"16.7M\"}, \"规格\": {\"产品尺寸\": \"长539.8mm；宽181.8mm；高399.9mm\", \"壁挂规格\": \"100x100mm\", \"支架底座\": \"普通\", \"电源类型\": \"外接电源适配器\", \"是否内置音箱\": \"无内置音箱\", \"是否支持壁挂\": \"支持壁挂\", \"产品净重（kg）\": \"3.2Kg\"}}";

        final Object obj = objectMapper.readValue(json, Object.class);
        String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        System.out.println(s);
    }

    public static class JsonBuilder {
        private Map<String, Object> map = new HashMap<>();

        private JsonBuilder() {
        }

        public JsonBuilder put(String key, Object value) {
            map.put(key, value);
            return this;
        }

        public String build() {
            try {
                return objectMapper.writeValueAsString(this.map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return "{}";
        }
    }
}