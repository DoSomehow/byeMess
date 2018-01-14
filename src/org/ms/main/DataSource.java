package org.ms.main;

import java.sql.*;

public class DataSource {

    private String url;
    private String user;
    private String pwd;

    private Connection con = null; // 创建一个数据库连接
    private PreparedStatement pre = null; // 创建预编译语句对象，一般都是用这个而不用Statement
    private ResultSet result = null; // 创建一个结果集对象

    public static void main(String[] args) {
        String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:orcl"; // 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
        String user = "stone"; // 用户名,系统默认的账户名
        String password = "shitou"; // 你安装时选设置的密码

        String sql = "select * from persons where psex = ?"; // 预编译语句，“？”代表参数
        Object[] params = new Object[]{
                "男"
        };

        DataSource ds = new DataSource(url, user, password);
        ds.query(sql, params);

    }

    public DataSource(String url, String user, String pwd){
        this.url = url;
        this.user = user;
        this.pwd = pwd;

        initConn();
    }

    private void initConn(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // 加载Oracle驱动程序
            System.out.println("开始尝试连接数据库！");

            con = DriverManager.getConnection(url, user, pwd); // 获取连接
            System.out.println("连接成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void query(String sql, Object[] params){
        try {
            pre = con.prepareStatement(sql); // 实例化预编译语句

            if(params != null && params.length != 0){
                for (int i = 0; i < params.length; i++) {
                    pre.setObject(i+1, params[i]); // 设置参数，前面的1表示参数的索引，而不是表中列名的索引
                }
            }

            result = pre.executeQuery(); // 执行查询，注意括号中不需要再加参数

            while (result.next()){
                // 当结果集不为空时
                StringBuffer sb = new StringBuffer();
                sb.append("姓名:").append(result.getString("pname"));
                System.out.println(sb.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
                // 注意关闭的顺序，最后使用的最先关闭
                if (result != null)
                    result.close();
                if (pre != null)
                    pre.close();
                if (con != null)
                    con.close();
                System.out.println("数据库连接已关闭！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
