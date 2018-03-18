package edu.noia.myoffice.customer.data.jpa.hibernate.converter;

import edu.noia.myoffice.customer.domain.vo.CustomerId;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

@Converter(autoApply = true)
public class CustomerIdConverter implements AttributeConverter<CustomerId, String> {
    @Override
    public String convertToDatabaseColumn(CustomerId attribute) {
        return attribute != null ? attribute.getId().toString() : null;
    }

    @Override
    public CustomerId convertToEntityAttribute(String dbData) {
        return StringUtils.hasText(dbData) ? CustomerId.of(UUID.fromString(dbData)) : null;
    }
}
