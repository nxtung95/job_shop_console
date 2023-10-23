package jop_shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class BaseDao {
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyBanHang";
	private static final String USER = "sa";
	private static final String PASS = "firstTime95";

	public Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			return DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			System.out.println("Lỗi khởi tạo connection " + e);
		}
		return null;
	}

	public void closeConnection(Connection connection, PreparedStatement ps, Statement s) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (s != null) {
				s.close();
			}
		} catch (Exception e) {
			System.out.println("Lỗi close connection " + e);
		}
	}
}
