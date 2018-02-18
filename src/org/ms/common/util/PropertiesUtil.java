package org.ms.common.util;

import java.io.InputStreamReader;
import java.util.*;

/**
 * 配置文件工具类
 * @author Ryan
 * @date 2018-01-21 01:04:00
 */
public class PropertiesUtil {

    private static final String baseUrl = "resources/properties/";
    private Properties props;

    public PropertiesUtil(String fileName) {
        readProperties(fileName);
    }

    /**
     * 加载配置文件
     * @param fileName
     */
    private void readProperties(String fileName) {
        try {
            props = new Properties();
            InputStreamReader inputStream = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(baseUrl + fileName), "UTF-8");
            props.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key读取对应的value
     * @param key
     * @return
     */
    public String get(String key) {
        if(props.containsKey(key)){
            return props.getProperty(key);
        }
        return null;
    }

    public String get(String key, String defaultValue) {
        String value = get(key);
        if(value == null || "".equals(value)){
            return defaultValue;
        }
        return value;
    }

    /**
     * 得到所有的配置信息
     * @return
     */
    public Map<?, ?> getAll() {
        Map<String, String> map = new HashMap<>();
        Enumeration<?> enu = props.propertyNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = props.getProperty(key);
            map.put(key, value);
        }
        return map;
    }

    public Integer getInteger(String key) {
        String value = get(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Integer.valueOf(value);
    }

    public static void main(String[] args) {
        PropertiesUtil pu = new PropertiesUtil("db.properties");
        System.out.println(pu.getClass().getClassLoader().getResource("resources/properties/db.properties"));
    }

}
