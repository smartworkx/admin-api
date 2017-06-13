package nl.smartworkx.admin.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicLong;
import javax.money.MonetaryAmount;
import javax.money.NumberValue;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.javamoney.moneta.Money;
import org.javamoney.moneta.RoundedMoney;
import org.javamoney.moneta.function.MonetaryUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.smartworkx.admin.model.financialfact.TaxRate;

/**
 * Holds data about a (monetary) value and its currency.
 *
 * This class exists because when we serialize a monetary amount we cannot run hql queries on the value or the currency.
 *
 */
@Embeddable
public class Amount {

    public static final String DEFAULT_CURRENCY_CODE = "EUR";
    public static final Amount ZERO = new Amount("0.00");

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

    public Amount(MonetaryAmount money) {
        this.value = getBigDecimal(money.getNumber());
        this.currency = money.getCurrency().getCurrencyCode();
    }

    public Amount(BigDecimal amount) {
        this(amount, DEFAULT_CURRENCY_CODE);
    }

    public Amount(NumberValue amount) {
        this(Money.of(amount, DEFAULT_CURRENCY_CODE));
    }

    public BigDecimal getValue() {
        return getBigDecimal(getMoney().getNumber());
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

    private MonetaryAmount getMoney() {
        return RoundedMoney.of(value, currency);
    }

    public Amount add(Amount amount) {
        return new Amount(this.getMoney().add(amount.getMoney()));
    }

    public Amount calculateExVat(double vatPercentage) {
        return new Amount(MonetaryUtil.percent(vatPercentage).apply(this.getMoney()));
    }

    public Amount calculateVatForAmountWithVatIncluded(TaxRate taxRate) {
        final double divisor = 1 / (1 + taxRate.getPercentage() / 100);
        return new Amount(RoundedMoney.of(this.getMoney().subtract(this.getMoney().multiply(divisor)).getNumber(), this.getMoney().getCurrency()));
    }

    public Amount subtract(Amount amount) {
        return new Amount(this.getMoney().subtract(amount.getMoney()));
    }

    public Amount abs(){
        return new Amount(this.getMoney().abs());
    }
    private static BigDecimal getBigDecimal(Number num) {
        if (num instanceof BigDecimal) {
            return (BigDecimal) num;
        }
        if (num instanceof Long || num instanceof Integer
                || num instanceof Byte || num instanceof AtomicLong) {
            return BigDecimal.valueOf(num.longValue());
        }
        if (num instanceof Float || num instanceof Double) {
            return new BigDecimal(num.toString());
        }
        try {
            // Avoid imprecise conversion to double value if at all possible
            return new BigDecimal(num.toString(), MathContext.DECIMAL64);
        } catch (NumberFormatException ignored) {
        }
        return BigDecimal.valueOf(num.doubleValue());
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value.doubleValue() +
                ", currency='" + currency + '\'' +
                '}';
    }

    @JsonIgnore
    public String getFormattedValue() {
        DecimalFormat df = new DecimalFormat("####0.00");
        return df.format(getValue());
    }
}
