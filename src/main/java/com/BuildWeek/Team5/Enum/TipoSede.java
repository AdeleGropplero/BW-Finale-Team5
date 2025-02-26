package com.BuildWeek.Team5.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoSede {
    OPERATIVA,
    LEGALE;

    @JsonCreator
    public static TipoSede fromString(String value) {
        for (TipoSede tipo : TipoSede.values()) {
            if (tipo.name().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }

    @JsonValue
    public String getValue() {
        return name(); // Restituisce il nome dell'enum
    }
}
