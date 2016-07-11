package nl.smartworkx.admin.infrastructure.jpa;

import java.util.Locale;
import java.util.Objects;
import javax.money.MonetaryAmount;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.stereotype.Component;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Converter
@Component
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, String> {
	MonetaryAmountFormat format = MonetaryFormats.getAmountFormat(Locale.GERMAN);
	@Override
	public String convertToDatabaseColumn(MonetaryAmount attribute) {

		if (Objects.isNull(attribute)) {
			return null;
		}
		return format.format(attribute).toString();
	}

	@Override
	public MonetaryAmount convertToEntityAttribute(String dbData){
		if (Objects.isNull(dbData)) {
			return null;
		}
		return format.parse(dbData);
	}

}
