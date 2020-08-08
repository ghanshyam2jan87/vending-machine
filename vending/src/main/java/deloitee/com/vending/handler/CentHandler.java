package deloitee.com.vending.handler;

public class CentHandler implements CoinHandler {

	private CoinHandler coinHandler;

	@Override
	public void setNextCoinHandler(CoinHandler coinHandler) {
		this.coinHandler = coinHandler;
	}

	@Override
	public void changeCoin(long givenAmount) {
		System.out.println(" Cent Handler");
		if (givenAmount >= 1) {
			int num = (int) (givenAmount / 1);
			int remainder = (int) (givenAmount % 1);
			System.out.println("Dispensing " + num + " cent");
			if (remainder != 0)
				this.coinHandler.changeCoin(remainder);
		} else {
			System.out.println("There is no remaining balance, No Change!");
		}

	}

}
