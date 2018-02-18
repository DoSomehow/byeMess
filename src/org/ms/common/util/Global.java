package org.ms.common.util;

public class Global {

    private static PropertiesUtil dbLoader = new PropertiesUtil("db.properties");

    //数据库
    public static final String DB_ORACLE_PROP_KEY_DRIVER_CLASS = "db.oracle.driverClassName";
    public static final String DB_ORACLE_PROP_KEY_URL = "db.oracle.url";
    public static final String DB_ORACLE_PROP_KEY_USERNAME = "db.oracle.username";
    public static final String DB_ORACLE_PROP_KEY_PASSWORD = "db.oracle.password";

    public static final String DB_ORACLE_URL_PREFIX = "jdbc:oracle:thin:@";

    //标点符号
    public static final String SYMBOL_COLON = ":";

    //根据key获取配置文件的值
    public static String getPropVal(String key){
        return dbLoader.get(key);
    }

}
