package org.ms.common.util;

import org.ms.common.db.DaoException;

import java.sql.*;

/**
 *
 * @author wsy
 * @date 2018-02-21
 */
public final class JdbcUtil {

    public JdbcUtil() {
    }

    // 注册驱动,这里应用的是static代码块只执行一次的特点
    static {
        try {
            Class.forName(Global.getPropVal(Global.DB_ORACLE_PROP_KEY_DRIVER_CLASS));
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 获取与指定数据库的链接
     */
    public static Connection getConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 释放三种资源ResultSet PreparedStatement Connection
     */
    public static void free(ResultSet rs, PreparedStatement ps, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                throw new DaoException(e.getMessage(), e);
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    throw new DaoException(e.getMessage(), e);
                }
            }
        }
    }
}
