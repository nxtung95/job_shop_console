package jop_shop.dao;

import jop_shop.model.Assembly;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AssemblyDao extends BaseDao {
	public List<Assembly> findAll() {
		Connection connection = null;
		PreparedStatement ps = null;
		List<Assembly> assemblyList = new ArrayList<>();
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder("SELECT * FROM assembly");
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int assemblyId = rs.getInt("assembly_id");
				Date dateOrdered = rs.getDate("date_ordered");
				String assemblyDetail = rs.getString("assembly_detail");
				int customerId = rs.getInt("customer_id");
				Assembly assembly = new Assembly(assemblyId, dateOrdered, assemblyDetail, customerId);
				assemblyList.add(assembly);
			}
			return assemblyList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return assemblyList;
	}
}
