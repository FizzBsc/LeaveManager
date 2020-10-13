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
    public static ArrayList<LeaveApplication> leaveApplicationDBArr = new ArrayList<>();
    public static ArrayList<Employee> user = new ArrayList<>();
    public static ArrayList<PublicHoliday> publicHolidayArrayList = new ArrayList<>();


    public static final String DATABASE_NAME= "Quicklauncher.db";
    public static final String PASSWORDB = "password_table";
    public static final String LEAVEDB = "LeaveType_table";
    public static final String LEAVEAPPLICATION = "Leave_application_table";
    public static final String EMPLOYEELEAVEAVAIL = "Employee_leave_available_table";
    public static final String USERS = "Users_table";
    public static final String PUBLICHOLIDAY = "public_holiday_table";

    public static final String COLUMN_PHID = "PHID";
    public static final String COLUMN_HOLIDAYNAME = "HOLIDAYNAME";
    public static final String COLUMN_HOLIDAYDATE = "HOLIDAYDATE";

    public static final String COLUMN_EID = "EID";
    public static final String COLUMN_CONTACTNUMBER = "CONTACTNUMBER";
    public static final String COLUMN_GIVENNAME = "GIVENNAME";
    public static final String COLUMN_LASTNAME = "LASTNAME";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_EMPTYPE = "EMPTYPE";
    public static final String COLUMN_MANAGER = "MANAGER";

    public static final String COLUMN_PID = "ID";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_ISNEWEMP = "ISNEWEMP";

    public static final String COLUMN_LTID = "LTID";
    public static final String COLUMN_LEAVENAME = "LEAVENAME";
    public static final String COLUMN_DAYSAVAIL = "DAYSAVAILABLE";

    public static final String COLUMN_LEAVEAPPID = "LEAVEAPPID";
    public static final String COLUMN_TYPEOFLEAVE = "TYPEOFLEAVE";
    public static final String COLUMN_STARTDATE = "STARTDATE";
    public static final String COLUMN_ENDDATE = "ENDDATE";
    public static final String COLUMN_NOOFDAYS = "NOOFDAYS";
    public static final String COLUMN_APPROVALSTATUS = "APPROVALSTATUS";

    public static final String COLUMN_ELAID = "ELAID";
    public static final String COLUMN_DAYSTAKEN = "DAYSTAKEN";

    public void addPassword(String eID, String password) {
        Log.d("QuickLauncher", "its crash");
        PasswordDB user = new PasswordDB(eID, password, null);
        passwordArr.add(user);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PID, eID);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_ISNEWEMP, "true");

        db.insert(PASSWORDB,null,cv);
        db.close();
    }

    public void addLeaveType() {
        Log.d("QuickLauncher", "adding leave");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        for (int i = 0; i<leaveTypeDBArr.size(); i++) {

            cv.put(COLUMN_LTID, leaveTypeDBArr.get(i).lTID);
            cv.put(COLUMN_LEAVENAME,  leaveTypeDBArr.get(i).leaveName);
            cv.put(COLUMN_DAYSAVAIL,  leaveTypeDBArr.get(i).daysAvail);

            db.insert(LEAVEDB, null, cv);
            Log.d("QuickLauncher", "added" +  leaveTypeDBArr.get(i).leaveName);
            db.close();

        }
    }
    public void updateNewUsr(String id,String pass, String i){
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE " + PASSWORDB + " SET " + COLUMN_ISNEWEMP + " = '" + i + "'," + COLUMN_PASSWORD + " = '" + pass + "'" + " WHERE " + COLUMN_PID + " = '" + id +"'";
        db.execSQL(strSQL);
        db.close();

    }
    public void loadLeaveTypeToArr(){
        int i = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + LEAVEDB;

        Cursor cursor = db.rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {

                while (!cursor.isAfterLast()) {

                    String a = cursor.getString(cursor.getColumnIndex(COLUMN_LTID));
                    String b = cursor.getString(cursor.getColumnIndex(COLUMN_LEAVENAME));
                    String c = cursor.getString(cursor.getColumnIndex(COLUMN_DAYSAVAIL));
                    int d = Integer.parseInt(c);
                    LeaveTypeDB lTDB = new LeaveTypeDB(a,b,d);
                    leaveTypeDBArr.add(lTDB);

                    Log.d("checking1", leaveTypeDBArr.get(i).lTID +" " + leaveTypeDBArr.get(i).leaveName + " " +leaveTypeDBArr.get(i).daysAvail);
                    i++;
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();

    }
    public void updateLeaveType(String id, int days){
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE " + LEAVEDB + " SET " + COLUMN_DAYSAVAIL + " = '" + days + "'" + " WHERE " + COLUMN_LTID + " = '" + id +"'";
        db.execSQL(strSQL);

        db.close();

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
                    String c1 = cursor.getString(cursor.getColumnIndex(COLUMN_ISNEWEMP));
                    boolean d = Boolean.parseBoolean(c1);
                    PasswordDB pd = new PasswordDB(a,b,null);
                    passwordArr.add(pd);
                    passwordArr.get(i).setNewEmp(d);//set false here to test first time password change

                    Log.d("checking", passwordArr.get(i).eID +" " + c1 + d + passwordArr.get(i).password + passwordArr.get(i).isNewEmp);
                    i++;
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addLeaveApplication(String leaveAppID, String eID, String typeOfLeave, String startDate, String endDate, int noOfDays, String approvalStatus){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LEAVEAPPID, leaveAppID);
        cv.put(COLUMN_EID, eID);
        cv.put(COLUMN_TYPEOFLEAVE, typeOfLeave);
        cv.put(COLUMN_STARTDATE, startDate);
        cv.put(COLUMN_ENDDATE, endDate);
        cv.put(COLUMN_NOOFDAYS, noOfDays);
        cv.put(COLUMN_APPROVALSTATUS, approvalStatus);

        db.insert(LEAVEAPPLICATION,null,cv);
        db.close();

    }
    public void loadLeaveApplicationToArr(){
        int i = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + LEAVEAPPLICATION;

        Cursor cursor = db.rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {

                while (!cursor.isAfterLast()) {

                    String a = cursor.getString(cursor.getColumnIndex(COLUMN_LEAVEAPPID));
                    String b = cursor.getString(cursor.getColumnIndex(COLUMN_EID));
                    String c = cursor.getString(cursor.getColumnIndex(COLUMN_TYPEOFLEAVE));
                    String d = cursor.getString(cursor.getColumnIndex(COLUMN_STARTDATE));
                    String e = cursor.getString(cursor.getColumnIndex(COLUMN_ENDDATE));
                    int f = cursor.getInt(cursor.getColumnIndex(COLUMN_NOOFDAYS));
                    String g = cursor.getString(cursor.getColumnIndex(COLUMN_APPROVALSTATUS));

                    LeaveApplication la = new LeaveApplication(a, b, c, d, e, f, g);
                    leaveApplicationDBArr.add(la);

                    Log.d("LoadDB Success", leaveApplicationDBArr.get(i).eID +" " );
                    i++;
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();

    }
    public void updateLeaveApplication(String status, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE " + LEAVEAPPLICATION + " SET " + COLUMN_APPROVALSTATUS + " = '" + status + "'" + " WHERE " + COLUMN_LEAVEAPPID + " = '" + id +"'";
        db.execSQL(strSQL);
        db.close();

    }
    public void updateEmpType(String id, String newemp){
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE " + USERS + " SET " + COLUMN_EMPTYPE + " = '" + newemp + "'" + " WHERE " + COLUMN_EID + " = '" + id +"'";
        db.execSQL(strSQL);
        db.close();

    }

    public void addEmployeeLeaveAvailable(String leaveAppID, String eID, String typeOfLeave, int daysTaken, int daysAvail){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ELAID, leaveAppID);
        cv.put(COLUMN_EID, eID);
        cv.put(COLUMN_TYPEOFLEAVE, typeOfLeave);
        cv.put(COLUMN_DAYSTAKEN, daysTaken);
        cv.put(COLUMN_DAYSAVAIL, daysAvail);

        db.insert(EMPLOYEELEAVEAVAIL,null,cv);
        db.close();

    }
    public void loadEmployeeLeaveAvailable(){
        int i = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + EMPLOYEELEAVEAVAIL;

        Cursor cursor = db.rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {

                while (!cursor.isAfterLast()) {

                    String a = cursor.getString(cursor.getColumnIndex(COLUMN_ELAID));
                    String b = cursor.getString(cursor.getColumnIndex(COLUMN_EID));
                    String c = cursor.getString(cursor.getColumnIndex(COLUMN_TYPEOFLEAVE));
                    int d = cursor.getInt(cursor.getColumnIndex(COLUMN_DAYSTAKEN));
                    int e = cursor.getInt(cursor.getColumnIndex(COLUMN_DAYSAVAIL));


                    EmployeeLeaveAvailable ela = new EmployeeLeaveAvailable(a, b, c, d, e);
                    employeeLeaveAvailArr.add(ela);

                    Log.d("employeeLeaveAvailArr", employeeLeaveAvailArr.get(i).eID +" " );
                    i++;
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();

    }
    public void updateEmployeeLeaveAvailable(int daysTaken, int daysAvail,  String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE " + EMPLOYEELEAVEAVAIL + " SET " + COLUMN_DAYSTAKEN + " = '" + daysTaken + "'," + COLUMN_DAYSAVAIL + " = '" + daysAvail + "'"+"  WHERE " + COLUMN_ELAID + " = '" + id +"'";
        db.execSQL(strSQL);
        db.close();

    }
    public void addUser(String eid, String contactNo, String givenName, String lastName, String email,String empType, String manager){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EID, eid);
        cv.put(COLUMN_CONTACTNUMBER, contactNo);
        cv.put(COLUMN_GIVENNAME, givenName);
        cv.put(COLUMN_LASTNAME, lastName);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_EMPTYPE, empType);
        cv.put(COLUMN_MANAGER, manager);


        db.insert(USERS,null,cv);
        db.close();

    }

    public void loadUser(){
        int i = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS;

        Cursor cursor = db.rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {

                while (!cursor.isAfterLast()) {

                    String a = cursor.getString(cursor.getColumnIndex(COLUMN_EID));
                    int b = cursor.getInt(cursor.getColumnIndex(COLUMN_CONTACTNUMBER));
                    String c = cursor.getString(cursor.getColumnIndex(COLUMN_GIVENNAME));
                    String d = cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME));
                    String e = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                    String f = cursor.getString(cursor.getColumnIndex(COLUMN_EMPTYPE));
                    String g = cursor.getString(cursor.getColumnIndex(COLUMN_MANAGER));


                    Employee us = new Employee(a, b, c, d, e,f,g);
                    user.add(us);

                    Log.d("employeeLeaveAvailArr", user.get(i).getEmployeeID() +" " );
                    i++;
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();

    }

    public void addHoliday(String phid, String hName, String hDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PHID, phid);
        cv.put(COLUMN_HOLIDAYNAME, hName);
        cv.put(COLUMN_HOLIDAYDATE, hDate);

        db.insert(PUBLICHOLIDAY,null,cv);
        db.close();

    }
    public void loadHoliday(){
        int i = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + PUBLICHOLIDAY;

        Cursor cursor = db.rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {

                while (!cursor.isAfterLast()) {

                    String a = cursor.getString(cursor.getColumnIndex(COLUMN_PHID));
                    String b = cursor.getString(cursor.getColumnIndex(COLUMN_HOLIDAYNAME));
                    String c = cursor.getString(cursor.getColumnIndex(COLUMN_HOLIDAYDATE));


                    PublicHoliday pH = new PublicHoliday(a, b, c);
                    publicHolidayArrayList.add(pH);

                    Log.d("employeeLeaveAvailArr", publicHolidayArrayList.get(i).getHolidayName() +" " );
                    i++;
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();

    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + PASSWORDB + "("+ COLUMN_PID +" TEXT PRIMARY KEY, "+ COLUMN_PASSWORD +" TEXT, "+ COLUMN_ISNEWEMP +" TEXT)");
        db.execSQL("create table " + LEAVEDB + "("+ COLUMN_LTID +" TEXT PRIMARY KEY, "+ COLUMN_LEAVENAME +" TEXT, "+ COLUMN_DAYSAVAIL +" INTEGER)");
        db.execSQL("create table " + LEAVEAPPLICATION + "("+ COLUMN_LEAVEAPPID +" TEXT PRIMARY KEY, "+ COLUMN_EID +" TEXT, "+ COLUMN_TYPEOFLEAVE +" TEXT, "+ COLUMN_STARTDATE +" TEXT, "+ COLUMN_ENDDATE +" TEXT, "+ COLUMN_NOOFDAYS +" INTEGER, "+ COLUMN_APPROVALSTATUS +" TEXT )");
        db.execSQL("create table " + EMPLOYEELEAVEAVAIL + "("+ COLUMN_ELAID +" TEXT PRIMARY KEY, "+ COLUMN_EID +" TEXT, "+ COLUMN_TYPEOFLEAVE +" TEXT, "+ COLUMN_DAYSTAKEN +" TEXT, "+ COLUMN_DAYSAVAIL +" TEXT )");
        db.execSQL("create table " + USERS + "("+ COLUMN_EID +" TEXT PRIMARY KEY, "+ COLUMN_CONTACTNUMBER +" TEXT, "+ COLUMN_GIVENNAME +" TEXT, "+ COLUMN_LASTNAME +" TEXT, "+ COLUMN_EMAIL +" TEXT, "+ COLUMN_EMPTYPE +" TEXT, "+ COLUMN_MANAGER +" TEXT)");
        db.execSQL("create table " + PUBLICHOLIDAY + "("+ COLUMN_PHID +" TEXT PRIMARY KEY, "+ COLUMN_HOLIDAYNAME +" TEXT, "+ COLUMN_HOLIDAYDATE +" TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LEAVEDB);
        onCreate(db);
    }
}
