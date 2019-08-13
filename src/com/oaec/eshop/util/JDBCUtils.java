package com.oaec.eshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {



	public static Connection getConnection() {
		try {
			return  JdbcDataSource.getDataSource().getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(ResultSet rs, Statement stm, Connection conn) {
		try {
			// 关闭结果集
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭语句对象
				if (stm != null && !stm.isClosed()) {
					stm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// 关闭连接
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void close(Statement stm, Connection conn) {
		close(null, stm, conn);
	}

}
