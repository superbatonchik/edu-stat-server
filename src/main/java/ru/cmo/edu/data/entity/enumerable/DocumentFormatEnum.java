package ru.cmo.edu.data.entity.enumerable;

import java.util.HashMap;
import java.util.Map;

public enum DocumentFormatEnum {

    XLS ("xls", 1),
    XLSX ("xlsx", 2),
    ODT ("odt", 3);

    static Map<String, Enum> strMap = new HashMap<>();
    static Map<Integer, Enum> intMap = new HashMap<>();

    private final String strValue;
    private final int intValue;

    DocumentFormatEnum(String value, int i) {
        this.strValue = value;
        this.intValue = i;
    }

    static {
        for (DocumentFormatEnum enumValue : DocumentFormatEnum.values()) {
            intMap.put(enumValue.intValue, enumValue);
            strMap.put(enumValue.strValue, enumValue);
        }
    }

    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return strValue;
    }

    public static DocumentFormatEnum value(int value) {
        return (DocumentFormatEnum) intMap.get(value);
    }

    public static DocumentFormatEnum value(String value) {
        return (DocumentFormatEnum) strMap.get(value);
    }
}
