package jop_shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DepartmentDao extends BaseDao {
	public boolean add(String departmentData) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder("INSERT INTO department(department_data) VALUES (?)");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, departmentData);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return false;
	}
}
