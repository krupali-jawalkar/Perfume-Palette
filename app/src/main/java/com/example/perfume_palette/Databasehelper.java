package com.example.perfume_palette;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.perfume_palette.Models.OrdersModel;

import java.util.ArrayList;

public class Databasehelper extends SQLiteOpenHelper {

    public static final String databaseName = "perfume_palette.db";

    public Databasehelper(@Nullable Context context) {
        super(context, databaseName, null, 47);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(username TEXT, email TEXT primary key, password TEXT)");
        MyDatabase.execSQL("create Table orders (id INTEGER primary key, name TEXT, description TEXT, image INTEGER )");
        MyDatabase.execSQL("create Table address(id INTEGER primary key, fullName TEXT, building TEXT, area TEXT, city TEXT, state TEXT, pincode TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists allusers");
        MyDatabase.execSQL("drop Table if exists orders");
        MyDatabase.execSQL("drop Table if exists address");
        onCreate(MyDatabase);

    }

    public Boolean insertData(String username, String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        //  contentValues.put("contact", contact);
        long result = MyDatabase.insert("allusers", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[]{email});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where username = ? and email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isLoggedIn() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + "allusers", null);
        boolean loggedIn = cursor.getCount() > 0;
        cursor.close();
        return loggedIn;
    }


    public Boolean insertAddressData(String fullName, String building, String area, String city, String state, String pincode) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullName", fullName);
        contentValues.put("building", building);
        contentValues.put("area", area);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("pincode", pincode);
        long result = MyDatabase.insert("address", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasAddress() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + "address", null);
        boolean hasAddress = cursor.getCount() > 0;
        cursor.close();
        return hasAddress;
    }




    public boolean insertOrder(String name, String desc, int image) {
        SQLiteDatabase database = getWritableDatabase();

        if (isItemInCart(name)) {
            return false; // Item already exists, return false
        }
        // Check if the current number of items in the cart is less than or equal to  3
        if (getOrderCount() <= 3) {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("description", desc);
            values.put("image", image);

            long id = database.insert("orders", null, values);
            return id != -1; // Return true if insertion was successful, false otherwise
        } else {
            return false; // Return false if the cart already contains 3 items
        }

    }

    // Helper method to get the current number of items in the cart
    private int getOrderCount() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM orders", null);
        int count = 0;
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(0);
            cursor.close();
        }
        return count;
    }

    public boolean isItemInCart(String itemName) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM orders WHERE name = ?", new String[]{itemName});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


    public boolean removeOrder(String name) {
        SQLiteDatabase database = getWritableDatabase();
        int result = database.delete("orders", "name = ?", new String[]{name});
        return result > 0;
    }



    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select  name, image  from orders", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                OrdersModel model = new OrdersModel();
           //     model.setOrderNumber(cursor.getInt(0) + "");
                model.setSoldItemName(cursor.getString(0));
                model.setOrderImage(cursor.getInt(1));
            //    model.setOrderPrice(cursor.getInt(2));
                orders.add(model);

            }
        }
        cursor.close();
        database.close();
        return orders;


    }
}

