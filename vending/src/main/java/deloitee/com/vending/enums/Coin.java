package deloitee.com.vending.enums;

import deloitee.com.vending.exception.CoinDescIsNotValidException;

public enum Coin {

	CENT(1, "CENT"), NICKLE(5, "NICKLE"), DIME(10, "DIME"), QUARTER(25, "QUARTER");

	private int value;
	private String coinDesc;

	private Coin(int value, String coinDesc) {
		this.value = value;
		this.coinDesc = coinDesc;
	}

	public int getValue() {
		return value;
	}

	public String getCoinDesc() {
		return coinDesc;
	}

	public static Coin coinValueOf(String coinDesc) throws CoinDescIsNotValidException {
		for (Coin coin : values()) {
			if (coin.getCoinDesc().equalsIgnoreCase(coinDesc)) {
				return coin;
			}
		}
		throw new CoinDescIsNotValidException("Coin not found with description : " + coinDesc);
	}

}
