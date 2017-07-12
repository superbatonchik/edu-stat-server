package ru.cmo.edu.data.entity.enumerable;

/**
 * Created by to on 08.06.2017.
 */
public enum CredentialsTypeEnum {
    REGION (1),
    MINISTRY (2),
    MUNICIPALITY (3),
    EDU (4);

    private int val;
    CredentialsTypeEnum(int i) {
        val = i;
    }

    public static CredentialsTypeEnum get(int i) {
        return CredentialsTypeEnum.values()[i - 1];
    }
}
