package com.example.quicklauncher;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import androidx.annotation.Nullable;



public class HolidayDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "Holiday.db";
    public static final String TABLE_NAME= "Holiday_table";
    public static final String COL1= "SNO";
    public static final String COL2= "DATE";
    public static final String COL3= "MONTH";
    public static final String COL4= "YEAR";
    public static final String COL5= "DAY";




    public HolidayDB( Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(Sno INTEGER PRIMARY KEY AUTOINCREMENT,DATE INTEGER,MONTH TEXT,YEAR INTEGER,DAY TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" Drop table If Exists "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String date,String month,String year,String day){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,date);
        contentValues.put(COL3,month);
        contentValues.put(COL4,year);
        contentValues.put(COL5,day);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result== -1)
            return false;
        else
            return true;


    }
}
