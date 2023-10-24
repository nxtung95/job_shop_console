package jop_shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerDao extends BaseDao {
	public boolean add(String name, String address, String category) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder("INSERT INTO customer(name, address, category) VALUES (?, ?, ?)");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, category);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return false;
	}
}
