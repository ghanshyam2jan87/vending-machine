package deloitee.com.vending;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import deloitee.com.vending.api.VendingMachineImpl;
import deloitee.com.vending.enums.Coin;
import deloitee.com.vending.enums.Product;
import deloitee.com.vending.exception.CoinDescIsNotValidException;
import deloitee.com.vending.exception.InsufficientMoneyException;
import deloitee.com.vending.exception.ProductIsNotValidException;
import deloitee.com.vending.exception.ProductNotAvailableException;
import deloitee.com.vending.model.Order;
import deloitee.com.vending.model.StockItems;

public class VendingMachineTest {

	private static VendingMachineImpl vendingMachine;
	private static StockItems stockItems = new StockItems();

	@BeforeAll
	public static void setUp() {
		vendingMachine = new VendingMachineImpl();
		VendingMachineImpl.initializeCoinHandler();
		for (Coin c : Coin.values()) {
			stockItems.addCoinInVending(c, 3);
		}
		stockItems.addProductInVending(Product.CANDY, 2);
		stockItems.addProductInVending(Product.COLDDRINK, 1);
		// }
	}

	@AfterAll
	public static void tearDown() {
		vendingMachine = null;
	}

	// @Test
	public void testBuyProductWithNoMoneyToChange()
			throws CoinDescIsNotValidException, ProductIsNotValidException, ProductNotAvailableException {
		vendingMachine.insertCoin("QUARTER");
		Order order = vendingMachine.getProductAndChange(Product.COLDDRINK);
		assertEquals(Product.COLDDRINK, order.getProduct());
		assertEquals(order.getProduct().getPrice(), Product.COLDDRINK.getPrice());
		assertTrue(order.getBalanceAmount() == 0);
	}

	// @Test
	public void testBuyProductWithSomeChange()
			throws CoinDescIsNotValidException, ProductIsNotValidException, ProductNotAvailableException {
		vendingMachine.insertCoin("QUARTER");
		Order order = vendingMachine.getProductAndChange(Product.CANDY);
		assertEquals(Product.CANDY, order.getProduct());
		assertEquals(order.getProduct().getPrice(), Product.CANDY.getPrice());
		assertTrue(order.getBalanceAmount() > 0);
	}

	@Test
	public void testChange()
			throws CoinDescIsNotValidException, ProductIsNotValidException, ProductNotAvailableException {
		vendingMachine.insertCoin("QUARTER");
		vendingMachine.insertCoin("DIME");
		Order order = vendingMachine.getProductAndChange(Product.valueOf(2));
		assertEquals(Product.CANDY, order.getProduct());
		assertEquals(order.getProduct().getPrice(), Product.CANDY.getPrice());
		assertTrue(order.getBalanceAmount() > 0);
		assertEquals(30, order.getBalanceAmount());
	}

	// @Test()
	public void testProductOutofStock() {
		Assertions.assertThrows(ProductNotAvailableException.class, () -> {
			vendingMachine.insertCoin("QUARTER");
			vendingMachine.chooseProduct(3);
		});
	}

	// @Test()
	public void testProductIsNotValid() {
		Assertions.assertThrows(ProductIsNotValidException.class, () -> {
			vendingMachine.insertCoin("DIME");
			vendingMachine.chooseProduct(10);
		});
	}

	// @Test()
	public void testInsufficientMoney() {
		Assertions.assertThrows(InsufficientMoneyException.class, () -> {
			vendingMachine.insertCoin("CENT");
			vendingMachine.chooseProduct(1);
		});
	}

}
