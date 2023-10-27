package jop_shop.dao;

import jop_shop.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao extends BaseDao {
//	public Transaction getTransactionByTranxNumber(int transactionNumber) {
//		Connection connection = null;
//		PreparedStatement ps = null;
//		try {
//			connection = getConnection();
//			StringBuilder sql = new StringBuilder("SELECT a.supplied_cost, a.account_number FROM transaction a " +
//					"JOIN Account b ON a.account_number = b.account_number " +
//					"WHERE transaction_number = ?");
//			ps = connection.prepareStatement(sql.toString());
//			ps.setInt(1, transactionNumber);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				double suppliedCost = rs.getDouble("supplied_cost");
//				String accountNumber = rs.getString("account_number");
//				Transaction transaction = new Transaction(transactionNumber, suppliedCost, accountNumber);
//				return transaction;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeConnection(connection, ps, null);
//		}
//		return null;
//	}

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
				Transaction transaction = new Transaction(transactionNumber, suppliedCost, accountNumber);
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