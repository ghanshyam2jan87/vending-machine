package deloitee.com.vending.handler;

public interface CoinHandler {

	void setNextCoinHandler(CoinHandler coinHandler);

	void changeCoin(long givenAmount);

}
