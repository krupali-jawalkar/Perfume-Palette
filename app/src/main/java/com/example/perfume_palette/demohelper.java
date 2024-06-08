package com.example.perfume_palette;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class demohelper extends SQLiteOpenHelper {

    public static final String databaseName = "perfume_palette.db";
    public demohelper(@Nullable Context context) {
        super(context, databaseName, null, 20);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {

        MyDatabase.execSQL("create Table orders (id INTEGER primary key, name TEXT, smell TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {

        MyDatabase.execSQL("drop Table if exists orders");
        onCreate(MyDatabase);

    }


    public boolean insertOrder(String name, String smell ){
        SQLiteDatabase database= getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("smell", smell);


        long id= database.insert("orders", null, values);
        if(id <=0){
            return false;
        }  else {
            return true;
        }
    }
}





