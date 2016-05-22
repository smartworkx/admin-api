package nl.smartworkx.admin.infrastructure.jpa;

import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.internal.FastMoneyAmountBuilder;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryQuery;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Converter
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, String> {

	@Override
	public String convertToDatabaseColumn(MonetaryAmount attribute) {

		if (Objects.isNull(attribute)) {
			return null;
		}
		return Money.from(attribute).toString();
	}

	@Override
	public MonetaryAmount convertToEntityAttribute(String dbData){
		if (Objects.isNull(dbData)) {
			return null;
		}
		return Money.parse(dbData);
	}

}
