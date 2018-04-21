package ru.cmo.edu.data.entity.enumerable;

import java.util.HashMap;
import java.util.Map;

public class DocumentFormatEnum {
    private final static Map<String, Integer> values = new HashMap<String, Integer>() {
        { put("xls", 1); }
        { put("xlsx", 2); }
        { put("odt", 3); }
    };

    public static final int XLS = values.get("xls");
    public static final int XLSX = values.get("xlsx");
    public static final int ODT = values.get("odt");

    public static Integer getByName(String name) {
        return values.get(name);
    }
    public static String getById(int id) {
        for (Map.Entry<String, Integer> val: values.entrySet()) {
            if (val.getValue() == id) {
                return val.getKey();
            }
        }
        return null;
    }
}
