package ru.cmo.edu.rest.security;

public class AccessRights {
    public static final int None = 0x00;
    public static final int EduForms = 0x01;
    public static final int MunitForms = 0x02;
    public static final int RegionForms = 0x04;
    public static final int Dictionaries = 0x08;
    public static final int Queries = 0x10;
    public static final int CreateQueries = 0x20;
    public static final int Statistics = 0x40;
    public static final int UploadForm = 0x80;
    public static final int SendMessage = 0x100;
    public static final int Passport = 0x200;
}
