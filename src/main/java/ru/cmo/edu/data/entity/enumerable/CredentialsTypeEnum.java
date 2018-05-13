package ru.cmo.edu.data.entity.enumerable;

import java.util.HashMap;
import java.util.Map;

public enum CredentialsTypeEnum {
    REGION (1),
    MINISTRY (2),
    MUNICIPALITY (3),
    EDU (4);

    private int value;

    CredentialsTypeEnum(int i) {
        value = i;
    }

    private static final Map<Integer, Enum> map = new HashMap<>();

    static {
        for (CredentialsTypeEnum enumValue : CredentialsTypeEnum.values()) {
            map.put(enumValue.value, enumValue);
        }
    }

    public int getValue() {
        return value;
    }

    public static CredentialsTypeEnum valueOf(int value) {
        return (CredentialsTypeEnum) map.get(value);
    }
}
