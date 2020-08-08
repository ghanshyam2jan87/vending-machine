package deloitee.com.vending.model;

public class TopProdcut {

	private String product;
	private int buyTimes;

	public TopProdcut() {
		super();
	}

	public TopProdcut(String product, int buyTimes) {
		super();
		this.product = product;
		this.buyTimes = buyTimes;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getBuyTimes() {
		return buyTimes;
	}

	public void setBuyTimes(int buyTimes) {
		this.buyTimes = buyTimes;
	}

	@Override
	public String toString() {
		return "TopProdcut [product=" + product + ", buyTimes=" + buyTimes + "]";
	}

}
