package jop_shop.model;

public class Customer {
	private int customerId;
	private String name;
	private String address;
	private String category;

	public Customer() {

	}

	public Customer(int customerId, String name, String address, String category) {
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.category = category;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
