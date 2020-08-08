package deloitee.com.vending.handler;

public class DimeHandler implements CoinHandler {

	private CoinHandler coinHandler;

	@Override
	public void setNextCoinHandler(CoinHandler coinHandler) {
		this.coinHandler = coinHandler;
	}

	@Override
	public void changeCoin(long givenAmount) {
		System.out.println(" Dime Handler " + givenAmount);
		if (givenAmount >= 5) {
			int num = (int) (givenAmount / 5);
			System.out.println(" num " + num);
			int remainder = (int) (givenAmount % 5);
			System.out.println("Dispensing " + num + " dime");
			if (remainder != 0)
				this.coinHandler.changeCoin(remainder);
		}else if(givenAmount == 0 ) {
			System.out.println("There is no remaining balance, No Change!");
		} else {
			this.coinHandler.changeCoin(givenAmount);
		}

	}

}
