package com.umy.yudasoft.datagoldarkiyaranv3.mDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by YudaRanger on 04/04/2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, Constan.DB_NAME, null, Constan.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constan.CREATE_TB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(Constan.DROP_TB);
        onCreate(db);
    }

    public void deleteData(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery =
                "delete from kiyaran1_TB where name="+"'"+ id +"'";
        Log.d("query", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void hapusAllData(){
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "delete from kiyaran1_TB";
        Log.d("query", deleteQuery);
        database.execSQL(deleteQuery);
        database.close();
    }


}