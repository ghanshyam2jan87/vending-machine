package deloitee.com.vending.enums;

import deloitee.com.vending.exception.ProductIsNotValidException;

public enum Product {

	COLDDRINK(1, "ColdDrink", 25), CANDY(2, "Candy", 5), CHOCLATES(3, "Choclates", 50);

	private int productCode;
	private int price;
	private String product;

	private Product(int productCode, String product, int price) {
		this.productCode = productCode;
		this.price = price;
		this.product = product;
	}

	public int getProductCode() {
		return productCode;
	}

	public int getPrice() {
		return price;
	}

	public String getProduct() {
		return product;
	}

	public static Product valueOf(int productCode) throws ProductIsNotValidException {
		for (Product product : values()) {
			if (product.productCode == productCode) {
				return product;
			}
		}
		throw new ProductIsNotValidException("Product with give product code " + productCode
				+ " is not valid, please choose availabe product in vending machine!");
	}
}
