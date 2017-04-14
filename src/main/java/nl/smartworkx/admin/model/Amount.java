package nl.smartworkx.admin.model;

import java.math.BigDecimal;
import javax.money.NumberValue;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.javamoney.moneta.Money;
import org.javamoney.moneta.function.MonetaryUtil;

/**
 * Holds data about a (monetary) value and its currency.
 *
 * This class exists because when we serialize a monetary amount we cannot run hql queries on the value or the currency.
 *
 */
@Embeddable
public class Amount {

    public static final String DEFAULT_CURRENCY_CODE = "EUR";

    @Column(name = "amount")
    private BigDecimal value;

    private String currency;

    private Amount() {
    }

    public Amount(BigDecimal value, String currency) {
        this(Money.of(value, currency));
    }

    public Amount(String value) {
        this(new BigDecimal(value));
    }

    public Amount(Money money) {
        this.value = money.getNumberStripped();
        this.currency = money.getCurrency().getCurrencyCode();
    }

    public Amount(BigDecimal amount) {
        this(amount, DEFAULT_CURRENCY_CODE);
    }

    public NumberValue getValue() {
        return getMoney().getNumber();
    }

    public String getCurrency() {
        return getMoney().getCurrency().getCurrencyCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Amount amount = (Amount) o;
        return value.compareTo(amount.value) == 0 && currency.equals(amount.currency);
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }

    private Money getMoney() {
        return Money.of(value, currency);
    }

    public Amount add(Amount amount) {
        return new Amount(this.getMoney().add(amount.getMoney()));
    }

    public Amount calculateVat(double vatPercentage) {
        return new Amount((Money) MonetaryUtil.percent(vatPercentage).apply(this.getMoney()));
    }
}
