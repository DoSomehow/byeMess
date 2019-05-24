package org.ms.common.util;

import java.io.*;
import java.util.*;

/**
 * 配置文件工具类
 * @author Ryan
 * @date 2018-01-21 01:04:00
 */
public class PropertiesUtil {

    //注意，不能使用File.separator，否则会无法读取到文件
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
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(baseUrl + fileName);
            InputStreamReader inputStream = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(inputStream);
            props.load(br);

            //关闭输入流
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入配置文件
     * @param fileName
     * @param comments
     */
    public void writeProperties(String fileName, String comments) throws Exception{
        String url = this.getClass().getResource("/").getPath() + "/" + baseUrl + fileName;
        url = url.substring(1);  //去除开头的斜杠
        File file = new File(url);
        // System.out.println(file.exists());
        OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        BufferedWriter bw = new BufferedWriter(outputStream);
        props.store(bw, comments);

        //关闭输出流
        bw.close();
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

    /**
     * 根据key读取对应的value
     * 如果value为null或空，则返回指定的默认值
     * @param key
     * @param defaultValue
     * @return
     */
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

    /**
     * 根据key读取对应的value
     * 并将value转为java.lang.Integer类型
     * @param key
     * @return
     */
    public Integer getInteger(String key) {
        String value = get(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Integer.valueOf(value);
    }

    /**
     * 向属性对象中存放键值对
     * @param key
     * @param value
     */
    public void setProp(String key, String value){
        props.setProperty(key, value);
    }

    /**
     * 向属性对象中存放键值对
     * 键值对来源于入参map中的键值对
     * @param map
     */
    public void setProps(Map<String, String> map){
        for(Map.Entry<String, String> entry : map.entrySet()){
            setProp(entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        PropertiesUtil pu = new PropertiesUtil("db.properties");
        // // System.out.println(pu.getClass().getClassLoader().getResource("resources/properties/db.properties"));
        // System.out.println(pu.getClass().getResource(""));
        // System.out.println(pu.getClass().getResource("/"));

        // String url = "D:\\workspace\\myIdea\\byeMess\\out\\production\\byeMess\\resources\\properties";
        // // String url = "abadac";
        // // url = url.replaceAll("a", "\\\\");
        // System.out.println(url);


    }

}
