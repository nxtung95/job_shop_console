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

	public boolean updateSuppliedCost(Transaction transaction, double suppliedCost) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder("SELECT * FROM account WHERE account_number = ?");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, transaction.getAccountNumber());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				double currentSupCost = rs.getDouble("supplied_cost");
				double updateSupCost = currentSupCost + suppliedCost;

				sql = new StringBuilder("UPDATE account SET supplied_cost = ? WHERE account_number = ?");
				ps = connection.prepareStatement(sql.toString());
				ps.setDouble(1, updateSupCost);
				ps.setString(2, transaction.getAccountNumber());
				return ps.executeUpdate() > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return false;
	}

	public double getTotalCostByAssemblyId(int assemblyId) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder("SELECT c.assembly_Id, SUM(a.Cost) as totalCost FROM account a ");
			sql.append("JOIN assembly_account b ON a.account_number = b.account_number ");
			sql.append("JOIN assembly c ON c.assembly_id = b.assembly_id ");
			sql.append("WHERE c.assembly_id = ? ");
			sql.append("GROUP BY c.assembly_id ");
			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, assemblyId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				double totalCost = rs.getDouble("totalCost");
				return totalCost;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return 0;
	}
}