package org.ms.common.db;

import org.ms.common.util.Global;
import org.ms.common.util.JdbcUtil;
import org.ms.common.util.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSource {

    private String url;
    private String user;
    private String pwd;

    private Connection connection = null; // 创建一个数据库连接
    private PreparedStatement pre = null; // 创建预编译语句对象，一般都是用这个而不用Statement
    private ResultSet resultSet = null; // 创建一个结果集对象

    public static void main(String[] args) {
        String sql = "select * from student where sex = ?"; // 预编译语句，“？”代表参数
        Object[] params = new Object[]{
                "01"
        };

        DataSource ds = new DataSource();
        ds.query(sql, params);

    }

    public DataSource(){
        initConnInfo();
        initConn();
    }

    private void initConnInfo(){
        this.url = Global.getPropVal(Global.DB_ORACLE_PROP_KEY_URL); // 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
        this.user = Global.getPropVal(Global.DB_ORACLE_PROP_KEY_USERNAME); // 用户名,系统默认的账户名
        this.pwd = Global.getPropVal(Global.DB_ORACLE_PROP_KEY_PASSWORD); // 你安装时选设置的密码
    }

    private void initConn(){
        try {
            // Class.forName(Global.getPropVal(Global.DB_ORACLE_PROP_KEY_DRIVER_CLASS)); // 加载Oracle驱动程序
            // System.out.println("开始尝试连接数据库！");
            //
            // connection = DriverManager.getConnection(url, user, pwd); // 获取连接
            // System.out.println("连接成功！");

            connection = JdbcUtil.getConnection(url, user, pwd);

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    public boolean isConnSuccess(){
        boolean isSuccess = false;
        try {
            if(this.connection != null){
                isSuccess = true;
                // this.connection.close();
                // System.out.println("数据库连接已关闭！");

                JdbcUtil.free(resultSet, pre, connection);
            }
        }catch (Exception e){
            throw new DaoException(e.getMessage(), e);
        }
        return isSuccess;
    }

    /**
     *
     * @param sql
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> query(String sql) {
        return query(sql, null);
    }

    /**
     *
     * @param sql
     * @param params
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> query(String sql, Object[] params){

        if(StringUtil.isEmpty(sql)){
            return null;
        }

        List<Map<String, Object>> dataList = null;

        try {
            pre = connection.prepareStatement(sql); // 实例化预编译语句

            if(params != null && params.length != 0){
                for (int i = 0; i < params.length; i++) {
                    pre.setObject(i+1, params[i]); // 设置参数，前面的1表示参数的索引，而不是表中列名的索引
                }
            }

            resultSet = pre.executeQuery(); // 执行查询，注意括号中不需要再加参数

            ResultSetMetaData rsmd =  resultSet.getMetaData();  //元数据信息
            int count = rsmd.getColumnCount();  //字段数量
            String[] colNameArr = new String[count];  //字段名数组

            for (int i = 0; i < count; i++) {
                colNameArr[i] = rsmd.getColumnName(i + 1);
            }

            dataList = new ArrayList<>();
            while (resultSet.next()){
                Map<String, Object> dataMap = new HashMap<>();
                for (int i = 0; i < count; i++) {
                    String key = colNameArr[i];
                    Object value = resultSet.getObject(key);
                    dataMap.put(key, value);
                }
                dataList.add(dataMap);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            JdbcUtil.free(resultSet, pre, connection);
        }

        return dataList;
    }

}
