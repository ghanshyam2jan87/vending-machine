package deloitee.com.vending.api;

import deloitee.com.vending.enums.Product;
import deloitee.com.vending.exception.CoinDescIsNotValidException;
import deloitee.com.vending.exception.ProductIsNotValidException;
import deloitee.com.vending.exception.ProductNotAvailableException;
import deloitee.com.vending.model.Order;

public interface VendingMachine {

	void resetOrder();

	Order getProductAndChange(Product product) throws ProductIsNotValidException, ProductNotAvailableException;

	void insertCoin(String coinDesc) throws CoinDescIsNotValidException;

}
