package jop_shop.dao;

import jop_shop.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao extends BaseDao {
	public boolean checkExistAccountNo(String accountNo) {
		Connection connection = null;
		PreparedStatement ps = null;
		List<Customer> customerList = new ArrayList<>();
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder("SELECT * FROM account WHERE account_number = ?");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, accountNo);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return false;
	}

	public boolean add(Account account) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder("INSERT INTO account(account_number, established_date, cost) VALUES (?,?,?)");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, account.getAccountNumber());
			ps.setDate(2, account.getEstablishedDate());
			ps.setDouble(3, account.getCost());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				String accountNumber = rs.getString(1);
				if (account instanceof AssemblyAccount) {
					AssemblyAccount assemblyAccount = (AssemblyAccount) account;
					sql = new StringBuilder("INSERT INTO assembly_account(account_number, assembly_id) VALUES (?,?)");
					ps = connection.prepareStatement(sql.toString());
					ps.setString(1, accountNumber);
					ps.setInt(2, assemblyAccount.getAssemblyId());
					ps.executeUpdate();
				} else if (account instanceof DepartmentAccount) {
					DepartmentAccount departmentAccount = (DepartmentAccount) account;
					sql = new StringBuilder("INSERT INTO department_account(account_number, department_id) VALUES (?,?)");
					ps = connection.prepareStatement(sql.toString());
					ps.setString(1, accountNumber);
					ps.setInt(2, departmentAccount.getDepartmentId());
					ps.executeUpdate();
				} else {
					ProcessAccount processAccount = (ProcessAccount) account;
					sql = new StringBuilder("INSERT INTO process_account(account_number, process_id) VALUES (?,?)");
					ps = connection.prepareStatement(sql.toString());
					ps.setString(1, accountNumber);
					ps.setInt(2, processAccount.getProcessId());
					ps.executeUpdate();
				}
				connection.commit();
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {

			}
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return false;
	}
}