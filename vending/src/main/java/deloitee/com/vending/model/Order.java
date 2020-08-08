package deloitee.com.vending.model;

import deloitee.com.vending.enums.Product;

public class Order {

	private int orderId;
	private Product product;
	private long orderAmount;
	private long balanceAmount;

	public Order() {
		super();
	}

	public Order(Product product, long orderAmount, int orderId, long balanceAmount) {
		super();
		this.orderId = orderId;
		this.product = product;
		this.orderAmount = orderAmount;
		this.balanceAmount = balanceAmount;
	}

	public Product getProductList() {
		return product;
	}

	public void setProductList(Product product) {
		this.product = product;
	}

	public long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(long orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(long balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", product=" + product + ", orderAmount=" + orderAmount
				+ ", balanceAmount=" + balanceAmount + "]";
	}

}
