package com.umy.yudasoft.datagoldarkiyaranv3.mDatabase;

/**
 * Created by YudaRanger on 04/04/2018.
 */

public class Constan {
    static  final String ROW_ID ="id";
    static final String NAME="name";
    static final String GOLDAR ="goldar";
    static  final String ALAMAT ="alamat";
    static final String TGLLAHIR = "tgllahir";
    static final String JK = "jk";

    static final String DB_NAME ="kiyaran1_DB";
    static final String TB_NAME ="kiyaran1_TB";
    static final int DB_VERSION=1;

    static final String CREATE_TB = "CREATE TABLE kiyaran1_TB(id INTEGER PRIMARY KEY AUTOINCREMENT, "
            +"name TEXT NOT NULL, goldar TEXT NOT NULL, alamat TEXT NOT NULL, tgllahir TEXT NOT NULL, jk TEXT NOT NULL);";

    static final String DROP_TB="DROP TABLE IF EXISTS "+TB_NAME;
}
