package com.example.carcatalog.entity.converter.enumer;

import com.example.carcatalog.entity.OrdinalEnum;
import jakarta.persistence.AttributeConverter;

/**
 * Converts an enum to its ordinal value and vice versa. Used for the enums that implement {@link OrdinalEnum}.
 * @param <T> the enum type that implements {@link OrdinalEnum}
 */
public abstract class OrdinalEnumConverter<T extends Enum<T> & OrdinalEnum> implements AttributeConverter<T, Integer> {
    @Override
    public Integer convertToDatabaseColumn(T ordinalEnum) {
        if (ordinalEnum == null) {
            return null;
        }

        return ordinalEnum.getOrdinal();
    }

    @Override
    public T convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }

        Class<T> enumClass = getEnumClass();

        T[] enumConstants = enumClass.getEnumConstants();
        for (T enumer: enumConstants) {
            if (enumer.getOrdinal().equals(integer)) {
                return enumer;
            }
        }

        return null;
    }

    abstract Class<T> getEnumClass();
}
