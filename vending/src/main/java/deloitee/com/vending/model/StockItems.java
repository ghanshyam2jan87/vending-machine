package deloitee.com.vending.model;

import java.util.EnumMap;

import deloitee.com.vending.enums.Coin;
import deloitee.com.vending.enums.Product;

public class StockItems {

	private static EnumMap<Product, Integer> productMap = new EnumMap<>(Product.class);
	private static EnumMap<Coin, Integer> coinMap = new EnumMap<>(Coin.class);

	public void putProduct(Product product, int number) {
		productMap.put(product, number);
	}

	public void putCoin(Coin coin, int number) {
		coinMap.put(coin, number);
	}

	public void reset() {
		productMap.clear();
		coinMap.clear();
	}

	public void addProductInVending(Product product, int count) {
		Integer productCount = productCount(product);
		productMap.put(product, productCount + count);

	}

	public void addCoinInVending(Coin coin, int count) {

		Integer coinCount = coinCount(coin);
		coinMap.put(coin, coinCount + count);
	}

	public void removeProductFromVending(Product product) {
		if (isProductInVending(product)) {
			Integer productCount = productCount(product);
			productMap.put(product, productCount - 1);
		}

	}

	public void removeCoinFromVending(Coin coin) {

		if (isCoinInVending(coin)) {
			Integer coinCount = coinCount(coin);
			coinMap.put(coin, coinCount - 1);
		}
	}

	public boolean isProductInVending(Product product) {
		Integer count = productCount(product);
		return count == 0 ? false : true;
	}

	private Integer productCount(Product product) {
		Integer count = productMap.get(product);
		return count == null ? 0 : count;
	}

	public boolean isCoinInVending(Coin coin) {
		Integer count = coinCount(coin);
		return count == 0 ? false : true;
	}

	private Integer coinCount(Coin coin) {
		Integer count = coinMap.get(coin);
		return count == null ? 0 : count;
	}

	@Override
	public String toString() {
		return "StockItems [productMap=" + productMap + ", coinMap=" + coinMap + "]";
	}

}
