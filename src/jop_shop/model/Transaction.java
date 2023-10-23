package jop_shop.model;

public class Transaction {
	private int transactionNumber;
	private double suppliedCost;
	private String accountNumber;

	public Transaction() {
	}

	public Transaction(int transactionNumber, double suppliedCost, String accountNumber) {
		this.transactionNumber = transactionNumber;
		this.suppliedCost = suppliedCost;
		this.accountNumber = accountNumber;
	}

	public int getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(int transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public double getSuppliedCost() {
		return suppliedCost;
	}

	public void setSuppliedCost(double suppliedCost) {
		this.suppliedCost = suppliedCost;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
