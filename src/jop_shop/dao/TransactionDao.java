package jop_shop.dao;

import jop_shop.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao extends BaseDao {

	public boolean add(Connection connection, Transaction transaction) {
		try {
			StringBuilder sql = new StringBuilder("INSERT INTO [transaction](supplied_cost, account_number, job_number) VALUES (?,?,?)");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setDouble(1, transaction.getSuppliedCost());
			ps.setString(2, transaction.getAccountNumber());
			ps.setInt(3, transaction.getJobNumber());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Transaction> findAll() {
		Connection connection = null;
		PreparedStatement ps = null;
		List<Transaction> transactionList = new ArrayList<>();
		try {
			connection = getConnection();
			StringBuilder sql = new StringBuilder("SELECT * FROM [transaction]");
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int transactionNumber = rs.getInt("transaction_number");
				double suppliedCost = rs.getDouble("supplied_cost");
				String accountNumber = rs.getString("account_number");
				int jobNumber = rs.getInt("job_number");
				Transaction transaction = new Transaction(transactionNumber, suppliedCost, accountNumber, jobNumber);
				transactionList.add(transaction);
			}
			return transactionList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, ps, null);
		}
		return transactionList;
	}
}