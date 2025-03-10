package az.idrak.appv1.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConvertor implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return status != null ? status.getCode() : null;
    }

    @Override
    public Status convertToEntityAttribute(Integer code) {
        return code != null ? Status.of(code) : null;
    }
}
