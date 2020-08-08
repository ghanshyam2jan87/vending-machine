package deloitee.com.vending.handler;

public class QuarterHandler implements CoinHandler {

	private CoinHandler coinHandler;

	@Override
	public void setNextCoinHandler(CoinHandler coinHandler) {
		this.coinHandler = coinHandler;
	}

	@Override
	public void changeCoin(long givenAmount) {
		System.out.println(" Nickle Handler");
		if (givenAmount >= 25) {
			int num = (int) (givenAmount / 25);
			int remainder = (int) (givenAmount % 25);
			System.out.println("Dispensing " + num + " quarter");
			if (remainder != 0)
				 this.coinHandler.changeCoin(remainder);
		}else if(givenAmount == 0 ) {
			System.out.println("There is no remaining balance, No Change!");
		}else {
			 this.coinHandler.changeCoin(givenAmount);
		}
	}

}
