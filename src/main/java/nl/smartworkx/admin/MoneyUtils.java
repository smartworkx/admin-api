package nl.smartworkx.admin;

import org.javamoney.moneta.Money;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class MoneyUtils {
	public static Money toMoney(final Double amount) {

		return Money.of(amount, "EUR");
	}
}
