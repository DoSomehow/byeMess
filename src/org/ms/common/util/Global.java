package org.ms.common.util;

import java.util.Map;

public class Global {

    /**
     * 数据库属性对象工具类
     */
    private static PropertiesUtil dbLoader = new PropertiesUtil("db.properties");

    /**
     * 数据库
     */
    public static final String DB_ORACLE_PROP_KEY_DRIVER_CLASS = "db.oracle.driverClassName";
    public static final String DB_ORACLE_PROP_KEY_URL = "db.oracle.url";
    public static final String DB_ORACLE_PROP_KEY_USERNAME = "db.oracle.username";
    public static final String DB_ORACLE_PROP_KEY_PASSWORD = "db.oracle.password";

    public static final String DB_ORACLE_URL_PREFIX = "jdbc:oracle:thin:@";

    /**
     * 标点符号
     */
    public static final String SYMBOL_COLON = ":";


    /**
     * 根据key获取配置文件的值
     * @param key
     * @return
     */
    public static String getPropVal(String key){
        return dbLoader.get(key);
    }

    /**
     * 向数据库属性对象中存放键值对
     * @param key
     * @param value
     */
    public static void setDbProp(String key, String value){
        dbLoader.setProp(key, value);
    }

    /**
     * 向数据库属性对象中存放键值对
     * 键值对来源于入参map中的键值对
     * @param map
     */
    public static void setDbProps(Map<String, String> map){
        dbLoader.setProps(map);
    }

    /**
     * 向数据库配置文件中写入信息
     */
    public static void writeDbProperties() throws Exception{
        writeDbProperties(null);
    }

    /**
     * 向数据库配置文件中写入信息
     * @param comments
     */
    public static void writeDbProperties(String comments) throws Exception{
        dbLoader.writeProperties("db.properties", comments);
    }


}
