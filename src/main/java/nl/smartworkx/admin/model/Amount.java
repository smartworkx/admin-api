package nl.smartworkx.admin.model;

import java.math.BigDecimal;
import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.javamoney.moneta.Money;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Holds data about a (monetary) value and its currency.
 *
 * This class exists because when we serialize a monetary amount we cannot run hql queries on the value or the currency.
 *
 */
@Embeddable
public class Amount {

    @Column(name = "amount")
    private BigDecimal value;

    private String currency;

    private Amount() {
    }

    public Amount(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public Amount(Double amount, String currency) {
        this(new BigDecimal(amount), currency);
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
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

    @JsonIgnore
    public MonetaryAmount getMoney() {
        return Money.of(value,currency);
    }
}
