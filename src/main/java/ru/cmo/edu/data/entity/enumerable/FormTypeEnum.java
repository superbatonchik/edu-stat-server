package ru.cmo.edu.data.entity.enumerable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum FormTypeEnum {
    EDU (1),
    ADD_EDU (2),
    MUNICIPALITY (3),
    ADD_MUNICIPALITY (4),
    REGION (5),
    ADD_REGION (6);

    private final int value;

    FormTypeEnum(int i) {
        value = i;
    }

    private static final Map<Integer, Enum> map = new HashMap<>();

    static {
        for (FormTypeEnum enumValue : FormTypeEnum.values()) {
            map.put(enumValue.value, enumValue);
        }
    }

    public static FormTypeEnum[] ALL = new FormTypeEnum[] {
            EDU, ADD_EDU, MUNICIPALITY, ADD_MUNICIPALITY, REGION, ADD_REGION
    };


    public static boolean isEduType(int id) {
        return valueOf(id) == EDU || valueOf(id) == ADD_EDU;
    }

    public static boolean isMunicipalityType(int id) {
        return valueOf(id) == MUNICIPALITY || valueOf(id) == ADD_MUNICIPALITY;
    }

    public static boolean isRegionType(int id) {
        return valueOf(id) == REGION || valueOf(id) == ADD_REGION;
    }

    public static boolean isEduType(FormTypeEnum value) {
        return value == EDU || value == ADD_EDU;
    }

    public static boolean isMunicipalityType(FormTypeEnum value) {
        return value == MUNICIPALITY || value == ADD_MUNICIPALITY;
    }

    public static boolean isRegionType(FormTypeEnum value) {
        return value == REGION || value == ADD_REGION;
    }

    public int getValue() {
        return value;
    }

    public static FormTypeEnum valueOf(int value) {
        return (FormTypeEnum) map.get(value);
    }
}
