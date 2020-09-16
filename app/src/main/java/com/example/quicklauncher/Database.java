package com.example.quicklauncher;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "Quicklauncher.db";
    public static final String TABLE1 = "admin_table";
    public static final String TABLE2 = "manager_table";
    public static final String TABLE3 = "staff_table";

    public static final String ADMINCOLUMN_1 = "ID";
    public static final String ADMINCOLUMN_2 = "NAME";
    public static final String ADMINCOLUMN_3 = "CREATE_NEW_ACCOUNT";
    public static final String ADMINCOLUMN_4 = "DEACTIVATE_ACCOUNT";
    public static final String ADMINCOLUMN_5 = "ADD_PUBLIC_HOLIDAY";



    public Database(Context context) {
        super(context, DATABASE_NAME, null , 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE1 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, CREATE_NEW_ACCOUNT TEXT, DEACTIVATE_ACCOUNT TEXT, ADD_PUBLIC_HOLIDAY TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        onCreate(db);
    }
}
