package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static ArrayList<PasswordDB> passwordArr = new ArrayList<>();



    public static final String DATABASE_NAME= "Quicklauncher.db";
    public static final String PASSWORDB = "password_table";
    public static final String TABLE1 = "admin_table";
    public static final String TABLE2 = "manager_table";
    public static final String TABLE3 = "staff_table";

    public static final String ADMINCOLUMN_1 = "ID";
    public static final String ADMINCOLUMN_2 = "NAME";
    public static final String ADMINCOLUMN_3 = "CREATE_NEW_ACCOUNT";
    public static final String ADMINCOLUMN_4 = "DEACTIVATE_ACCOUNT";
    public static final String ADMINCOLUMN_5 = "ADD_PUBLIC_HOLIDAY";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_ISNEWEMP = "ISNEWEMP";

    Cursor c;

    public void adduser(String eID, String password) {
        Log.d("QuickLauncher", "its crash");
        PasswordDB fakeUser = new PasswordDB(eID, password, null);
        passwordArr.add(fakeUser);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, eID);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_ISNEWEMP, "true");

        db.insert(PASSWORDB,null,cv);
    }
    public void updateNewUsr(String id,String pass, String i){
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE " + PASSWORDB + " SET " + COLUMN_ISNEWEMP + " = '" + i + "'," + COLUMN_PASSWORD + " = '" + pass + "'" + " WHERE " + COLUMN_ID + " = '" + id +"'";
        db.execSQL(strSQL);


    }
    public void loadPassToArr(){
        int i = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + PASSWORDB;

        Cursor cursor = db.rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {

                while (!cursor.isAfterLast()) {

                    String a = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                    String b = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                    String c = cursor.getString(cursor.getColumnIndex(COLUMN_ISNEWEMP));
                    boolean d = Boolean.parseBoolean(c);
                    PasswordDB pd = new PasswordDB(a,b,null);
                    passwordArr.add(pd);
                    passwordArr.get(i).setNewEmp(d);

                    Log.d("checking", passwordArr.get(i).eID +" " + c + d + passwordArr.get(i).password + passwordArr.get(i).isNewEmp);
                    i++;
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL("create table " + PASSWORDB + "("+ COLUMN_ID +" TEXT PRIMARY KEY, "+ COLUMN_PASSWORD +" TEXT, "+ COLUMN_ISNEWEMP +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        onCreate(db);
    }
}
