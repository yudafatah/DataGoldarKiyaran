package com.umy.yudasoft.datagoldarkiyaranv3.mDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by YudaRanger on 04/04/2018.
 */

public class DBAdapter {
    Context c;
    SQLiteDatabase db;
    DBHelper dh;

    public DBAdapter(Context c){
        this.c = c;
        dh = new DBHelper(c);
    }

    public void openDB(){
        try {
            db=dh.getWritableDatabase();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeDB(){
        try {
            dh.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean add(String name, String goldar, String alamat, String tgllahir, String jk){
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constan.NAME, name);
            cv.put(Constan.GOLDAR, goldar);
            cv.put(Constan.ALAMAT, alamat);
            cv.put(Constan.TGLLAHIR, tgllahir);
            cv.put(Constan.JK, jk);

            db.insert(Constan.TB_NAME, Constan.ROW_ID, cv);

            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Cursor retrieve(String searchTerm){
        String[] columns = {Constan.ROW_ID, Constan.NAME, Constan.GOLDAR, Constan.ALAMAT, Constan.TGLLAHIR, Constan.JK};
        Cursor c = null;

        if(searchTerm != null && searchTerm.length()>0){
            String sql = "SELECT * FROM "+Constan.TB_NAME+" WHERE " + Constan.GOLDAR +" = '"+searchTerm.toUpperCase()+"'";
            c=db.rawQuery(sql,null);
            return c;
        }
        c=db.query(Constan.TB_NAME,columns,null,null,null,null,null);
        return c;
    }
}
