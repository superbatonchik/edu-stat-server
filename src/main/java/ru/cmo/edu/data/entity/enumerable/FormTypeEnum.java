package ru.cmo.edu.data.entity.enumerable;

public class FormTypeEnum {
    public static final int EDU = 1;
    public static final int ADD_EDU = 2;
    public static final int MUNICIPALITY = 3;
    public static final int ADD_MUNICIPALITY = 4;
    public static final int REGION = 5;
    public static final int ADD_REGION = 6;

    public static Integer[] ALL = new Integer[] {
            EDU, ADD_EDU, MUNICIPALITY, ADD_MUNICIPALITY, REGION, ADD_REGION
    };
}
