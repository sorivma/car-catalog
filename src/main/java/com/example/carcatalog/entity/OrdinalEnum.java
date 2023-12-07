package com.example.carcatalog.entity;

import com.example.carcatalog.entity.converter.enumer.OrdinalEnumConverter;

/**
 * Base interface for all enums that have ordinal values.
 * @see OrdinalEnumConverter
 */
public interface OrdinalEnum {
    /**
     * @return the ordinal value of the enum
     */
    Integer getOrdinal();
}
