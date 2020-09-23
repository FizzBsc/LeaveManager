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
    public static ArrayList<EmployeeLeaveAvailable> employeeLeaveAvailArr = new ArrayList<>();
    public static ArrayList<LeaveTypeDB> leaveTypeDBArr = new ArrayList<>();

    public static final String DATABASE_NAME= "Quicklauncher.db";
    public static final String PASSWORDB = "password_table";
    public static final String LEAVEDB = "LeaveType_table";

    public static final String COLUMN_PID = "ID";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_ISNEWEMP = "ISNEWEMP";

    public static final String COLUMN_LTID = "LTID";
    public static final String COLUMN_LEAVENAME = "LEAVENAME";
    public static final String COLUMN_DAYSAVAIL = "DAYSAVAILABLE";
    Cursor c;

    public void adduser(String eID, String password) {
        Log.d("QuickLauncher", "its crash");
        PasswordDB fakeUser = new PasswordDB(eID, password, null);
        passwordArr.add(fakeUser);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PID, eID);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_ISNEWEMP, "true");

        db.insert(PASSWORDB,null,cv);
    }

    public void addLeaveType() {
        Log.d("QuickLauncher", "adding leave");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        for (int i = 0; i<leaveTypeDBArr.size(); i++) {

            cv.put(COLUMN_LTID, leaveTypeDBArr.get(i).lTID);
            cv.put(COLUMN_LEAVENAME,  leaveTypeDBArr.get(i).LeaveName);
            cv.put(COLUMN_DAYSAVAIL,  leaveTypeDBArr.get(i).daysAvail);

            db.insert(PASSWORDB, null, cv);
            Log.d("QuickLauncher", "added" +  leaveTypeDBArr.get(i).LeaveName);
        }
    }
    public void updateNewUsr(String id,String pass, String i){
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE " + PASSWORDB + " SET " + COLUMN_ISNEWEMP + " = '" + i + "'," + COLUMN_PASSWORD + " = '" + pass + "'" + " WHERE " + COLUMN_PID + " = '" + id +"'";
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

                    String a = cursor.getString(cursor.getColumnIndex(COLUMN_PID));
                    String b = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                    String c = cursor.getString(cursor.getColumnIndex(COLUMN_ISNEWEMP));
                    boolean d = Boolean.parseBoolean(c);
                    PasswordDB pd = new PasswordDB(a,b,null);
                    passwordArr.add(pd);
                    passwordArr.get(i).setNewEmp(d);//set false here to test first time password change

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
        db.execSQL("create table " + PASSWORDB + "("+ COLUMN_LTID +" TEXT PRIMARY KEY, "+ COLUMN_LEAVENAME +" TEXT, "+ COLUMN_DAYSAVAIL +" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PASSWORDB);
        onCreate(db);
    }
}
