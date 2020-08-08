package deloitee.com.vending.api;

import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import deloitee.com.vending.enums.Coin;
import deloitee.com.vending.enums.Product;
import deloitee.com.vending.exception.CoinDescIsNotValidException;
import deloitee.com.vending.exception.InsufficientMoneyException;
import deloitee.com.vending.exception.ProductIsNotValidException;
import deloitee.com.vending.exception.ProductNotAvailableException;
import deloitee.com.vending.handler.CentHandler;
import deloitee.com.vending.handler.DimeHandler;
import deloitee.com.vending.handler.NickleHandler;
import deloitee.com.vending.handler.QuarterHandler;
import deloitee.com.vending.model.Order;
import deloitee.com.vending.model.StockItems;
import deloitee.com.vending.model.TopProdcut;

public class VendingMachineImpl implements VendingMachine {

	private static QuarterHandler quarterHandler = new QuarterHandler();
	private static NickleHandler nickleHandler = new NickleHandler();
	private static DimeHandler dimeHandler = new DimeHandler();
	private static CentHandler centHandler = new CentHandler();

	private static StockItems stockItems = new StockItems();
	private static volatile boolean transaction;
	private static int orderId = 0;
	private long orderMoney;
	private static long totalMoney;
	private long balanceMoney;
	private static Map<String, Integer> totalProductSoldMap = new ConcurrentHashMap<>();
	private static Map<Coin, Integer> coinMap = new ConcurrentHashMap<>();

	public static void main(String[] args) throws ProductNotAvailableException, CoinDescIsNotValidException,
			InsufficientMoneyException, ProductIsNotValidException {
		initializeCoinHandler();
		initializeVendingMachine();
		new VendingMachineImpl().order();
		if (totalMoney > 0) {
			new VendingMachineImpl().getTotalEarnedMoney();

		}
		if (totalProductSoldMap.size() >= 1) {
			new VendingMachineImpl().getTopOfTheProduct();
		}

	}

	public static void initializeCoinHandler() {

		quarterHandler.setNextCoinHandler(nickleHandler);
		nickleHandler.setNextCoinHandler(dimeHandler);
		dimeHandler.setNextCoinHandler(centHandler);
	}

	public static void initializeVendingMachine() {

		for (Coin c : Coin.values()) {
			stockItems.addCoinInVending(c, 3);
		}
		for (Product product : Product.values()) {
			stockItems.addProductInVending(Product.CANDY, 2);
			stockItems.addProductInVending(Product.COLDDRINK, 1);
		}
		System.out.println(" stockItems " + stockItems);

	}

	@Override
	public void insertCoin(String coinDesc) throws CoinDescIsNotValidException {
		orderMoney = orderMoney + Coin.coinValueOf(coinDesc).getValue();
		System.out.println("insertCoin called!");
		/*
		 * Scanner sc = new Scanner(System.in);
		 * System.out.println("do you want to insert more Coin if yes press YES or NO!"
		 * ); String moreMoney = sc.nextLine(); if (moreMoney.equalsIgnoreCase("YES")) {
		 * System.out.println("insert More coin!"); Scanner sc2 = new
		 * Scanner(System.in); insertCoin(sc2.nextLine()); }
		 */
		System.out.println("Starting Transaction!!");

		if (coinMap.containsKey(Coin.coinValueOf(coinDesc))) {
			coinMap.put(Coin.coinValueOf(coinDesc), coinMap.get(Coin.coinValueOf(coinDesc)) + 1);
		} else {
			coinMap.put(Coin.coinValueOf(coinDesc), 1);
		}
		coinMap.entrySet().forEach(c -> {
			stockItems.addCoinInVending(c.getKey(), c.getValue());
		});

	}

	public void order() throws CoinDescIsNotValidException, InsufficientMoneyException, ProductIsNotValidException,
			ProductNotAvailableException {
		System.out.println("insert coin into Vending Machine!");
		Scanner sc = new Scanner(System.in);
		System.out.println("type coin desc you want to enter into Vending Machine!");
		insertCoin(sc.nextLine());
		// insertCoin("QUARTER");
		System.out.println("chooseProduct called!");
		transaction = true;
		Scanner sc2 = new Scanner(System.in);
		System.out.println("choose product desc you want to get from Vending Machine!");
		int productCode = Integer.parseInt(sc2.nextLine());
		chooseProduct(productCode);
		sc.close();
		sc2.close();

	}

	public void chooseProduct(int productCode)
			throws InsufficientMoneyException, ProductIsNotValidException, ProductNotAvailableException {

		try {
			if (isValidProduct(productCode) && isProductAvailable(productCode)) {
				try {
					Order order = null;
					if (isSufficientMoneyToBuyProduct(orderMoney, productCode)) {
						System.out.println("Coolect your item and change!");
						order = getProductAndChange(getProduct(productCode));
						System.out.println("Order is completed with Order Details as " + order);

					}
				} catch (InsufficientMoneyException e) {
					System.out.println("Error Message is : " + e.getMessage());
					System.out.println("Enter more money to buy this Product ! ");
					throw e;
				}
			}
		} catch (ProductNotAvailableException | ProductIsNotValidException e) {
			System.out.println("Error message is : " + e.getMessage());
			System.out.println("Choose other available products in vending machine");
			throw e;
		}

	}

	private boolean isProductAvailable(int productCode)
			throws ProductNotAvailableException, ProductIsNotValidException {
		if (stockItems.isProductInVending(getProduct(productCode))) {
			return true;
		}
		throw new ProductNotAvailableException("Product is not avilable in vending machine");

	}

	private boolean isValidProduct(int productCode) throws ProductIsNotValidException {
		Product product = null;
		try {
			product = getProduct(productCode);
		} catch (ProductIsNotValidException e) {
			System.out.println("Error Message is : " + e.getMessage());
			throw e;
		}
		return product == null ? false : true;
	}

	public Product getProduct(int productCode) throws ProductIsNotValidException {
		Product product = Product.valueOf(productCode);
		return product;
	}

	private boolean isSufficientMoneyToBuyProduct(long orderMoney2, int productCode)
			throws ProductNotAvailableException, InsufficientMoneyException, ProductIsNotValidException {
		System.out.println("Entered Money " + orderMoney2);
		Product product = Product.valueOf(productCode);
		// System.out.println("Money needed to buy this product " + product.getPrice());
		if (orderMoney2 >= product.getPrice()) {
			return orderMoney2 >= product.getPrice();
		}
		throw new InsufficientMoneyException("Entered Money is not sufficient!");
	}

	@Override
	public Order getProductAndChange(Product product) throws ProductIsNotValidException, ProductNotAvailableException {
		System.out.println("getProductAndChange called!");
		returnProduct(product);
		quarterHandler.changeCoin(getBalanceAmount(product.getPrice()));
		Order order = new Order(product, product.getPrice(), ++orderId, balanceMoney);
		// System.out.println("Order is completed with Order Details as " + order);
		transaction = false;
		resetOrder();
		return order;
	}

	private void returnProduct(Product product) {
		stockItems.removeProductFromVending(product);
		if (totalProductSoldMap.containsKey(product.getProduct())) {
			totalProductSoldMap.put(product.getProduct(), totalProductSoldMap.get(product.getProduct() + 1));
		} else {
			totalProductSoldMap.put(product.getProduct(), 1);
		}
		totalMoney = totalMoney + product.getPrice();
		printInfo();
		System.out.println(" Collect your product " + product.getProduct());
	}

	private void printInfo() {
		System.out.println(" totalProductSoldMap " + totalProductSoldMap);
		System.out.println(" coinMap " + coinMap);
		System.out.println(" totalMoney " + totalMoney);
		System.out.println(" orderMoney " + orderMoney);
		System.out.println(" balanceMoney " + balanceMoney);
	}

	private long getBalanceAmount(long productPrice) {
		System.out.println("getBalanceAmount called!");
		balanceMoney = orderMoney - productPrice;
		System.out.println("balanceMoney is : " + balanceMoney);
		return balanceMoney;
	}

	public long getTotalEarnedMoney() {
		System.out.println("Total EarnedMoney till now " + totalMoney);
		return totalMoney;
	}

	public TopProdcut getTopOfTheProduct() {
		String product = Collections.max(totalProductSoldMap.entrySet(), Map.Entry.comparingByValue()).getKey();
		TopProdcut topProdcut = new TopProdcut(product, totalProductSoldMap.get(product));
		System.out.println("TopProduct " + topProdcut);
		return topProdcut;
	}

	@Override
	public void resetOrder() {
		if (!transaction) {
			orderMoney = 0;
			balanceMoney = 0;
			coinMap.clear();
			printInfo();
		} else {
			System.out.println("Transaction is in progress so cant not reset now !");
		}

	}

}
