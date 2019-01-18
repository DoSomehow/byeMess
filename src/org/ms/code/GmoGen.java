package org.ms.code;

import org.ms.common.db.DataSource;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 *
 * @author wsy
 * @date 2018-02-17
 */
public class GmoGen extends AbstractGen {

    private Map<String, String> root = new HashMap<>();  //数据模型

    @Override
    protected void execute() {

        //1. data-model相关数据
        setDataModel();

        //2. 融合模板和数据，创建java类和一个XML文件。


        resultVO.setSuccess(Boolean.TRUE);
        resultVO.setMessage("生成成功！");  //这个应该可以去掉吧？
    }

    /**
     * 设置模板对应的数据模型
     */
    private void setDataModel(){
        List<Map<String, String>> fieldList = queryFieldsInfo();  //获取表中所有字段及其对应java类型和注释

        root.put("", "");

    }

    /**
     * 根据数据库表名获得表中字段及其类型和注释
     * @return List<Map<String, String>>
     */
    private List<Map<String, String>> queryFieldsInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("select M.COLUMN_NAME, M.COMMENTS, M.DATA_TYPE, M.DATA_SCALE, P.IS_PRIMARY ");
        sb.append("  from (select C.COLUMN_NAME, C.COMMENTS, T.DATA_TYPE, T.DATA_SCALE ");
        sb.append("          from USER_COL_COMMENTS C, USER_TAB_COLUMNS T ");
        sb.append("         where C.COLUMN_NAME = T.COLUMN_NAME(+) ");
        sb.append("           and C.TABLE_NAME = T.TABLE_NAME(+) ");
        sb.append("           and C.TABLE_NAME = ? ) M ");
        sb.append("  left join (select x.COLUMN_NAME, '1' is_primary ");
        sb.append("               from user_cons_columns x, user_constraints y ");
        sb.append("              where x.CONSTRAINT_NAME = y.CONSTRAINT_NAME ");
        sb.append("                and y.CONSTRAINT_TYPE = 'P' ");
        sb.append("                and x.TABLE_NAME = ? ) P ");
        sb.append("    on M.COLUMN_NAME = P.column_name ");

        Object[] params = new Object[]{
                tableName, tableName
        };
        DataSource ds = new DataSource();
        List<Map<String, Object>> dataList = ds.query(sb.toString(), params);

        List<Map<String, String>> rsltList = new ArrayList<>();
        for(Map<String, Object> dataMap : dataList){
            Map<String, String> rsltMap = new HashMap<>();
            String columnName = dataMap.get("COLUMN_NAME").toString();
            rsltMap.put("name", lowerCamelCase(columnName));  //bo类中对应的字段名
            rsltMap.put("dbName", columnName);  //数据库表中的字段名
            rsltMap.put("type", oracleTypeToJavaType(dataMap));  //对应的java数据类型
            rsltMap.put("comment", handleComment(dataMap.get("COMMENTS"), 50));  //字段注释
            rsltMap.put("isPrimary", dataMap.get("IS_PRIMARY") == null ? "" : dataMap.get("IS_PRIMARY").toString());  //是否主键
            rsltList.add(rsltMap);
        }

        return rsltList;
    }

    /**
     * 按驼峰命名规则转换数据库表中列名
     * @param name
     * @return String
     */
    private String lowerCamelCase(String name){
        name = name.toLowerCase();
        Matcher matcher = linePattern.matcher(name);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 将数据库表中字段类型转换为对应的Java数据类型
     * @param map
     * @return String
     */
    private String oracleTypeToJavaType(Map<String, Object> map){
        String dbType = map.get("DATA_TYPE").toString();
        Integer dataScale = 0;
        if(map.get("DATA_SCALE") != null){
            dataScale = Integer.valueOf(map.get("DATA_SCALE").toString()) ;
        }

        if("varchar2".equalsIgnoreCase(dbType)){
            return "String";
        }else if("number".equalsIgnoreCase(dbType)){
            if(dataScale.intValue() > 0){
                return "Double";
            }
            return "Long";
        }else if("date".equalsIgnoreCase(dbType)){
            return "Date";
        }else if("blob".equalsIgnoreCase(dbType)){
            return "byte[]";
        }else{
            return "String";
        }
    }

    /**
     * 处理字段注释
     * @param comment
     * @return String
     */
    private String handleComment(Object comment, int len){
        if(comment == null)
            return "";
        String str = comment.toString();
        if(str.length() > len){
            str = str.substring(0, len) + " ...";
        }
        return str.replaceAll("\r|\n", " ");
    }

    public static void main(String[] args) {
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();  //操作系统的桌面路径
        System.out.println(desktopPath);
        String user = System.getProperty("user.dir");  //文件所在路径的父路径
        System.out.println(user);
    }
}
