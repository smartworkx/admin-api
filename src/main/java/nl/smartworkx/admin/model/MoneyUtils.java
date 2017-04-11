package nl.smartworkx.admin.model;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import nl.smartworkx.admin.model.Amount;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class MoneyUtils {
	public static Amount toMoney(final Double amount) {

		return new Amount(amount, "EUR");
	}

	public static Amount toMoney(final int i) {

		return toMoney(new Double(i));
	}
}
