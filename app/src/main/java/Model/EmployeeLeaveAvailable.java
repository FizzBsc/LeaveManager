package Model;

import android.content.Context;
import android.util.Log;

public class EmployeeLeaveAvailable {

    String eLAID;
    String eID;
    String typeOfLeave;
    int daysTaken;
    int daysAvail;

    public EmployeeLeaveAvailable(String eLAID, String eID, String typeOfLeave, int daysTaken, int daysAvail) {
        this.eLAID = eLAID;
        this.eID = eID;
        this.typeOfLeave = typeOfLeave;
        this.daysTaken = daysTaken;
        this.daysAvail = daysAvail;
    }

    private Context mContext;

    public EmployeeLeaveAvailable(Context context){
        mContext = context;
    }
    public void addNewUserHolidays(String a){
        Database db = new Database(mContext);
        Log.d("works here", "works here");
        for (int i = 0; i < Database.leaveTypeDBArr.size(); i++) {
            Log.d("works here again", Database.leaveTypeDBArr.get(i).getLeaveName());
            Database.employeeLeaveAvailArr.add(new EmployeeLeaveAvailable(a + Database.leaveTypeDBArr.get(i).lTID, a, Database.leaveTypeDBArr.get(i).leaveName, 0, Database.leaveTypeDBArr.get(i).daysAvail));
            db.addEmployeeLeaveAvailable(a + Database.leaveTypeDBArr.get(i).lTID, a, Database.leaveTypeDBArr.get(i).leaveName, 0, Database.leaveTypeDBArr.get(i).daysAvail);
        }

    }


    public String geteLAID() {
        return eLAID;
    }

    public void seteLAID(String eLAID) {
        this.eLAID = eLAID;
    }

    public String geteID() {
        return eID;
    }

    public void seteID(String eID) {
        this.eID = eID;
    }

    public String getTypeOfLeave() {
        return typeOfLeave;
    }

    public void setTypeOfLeave(String typeOfLeave) {
        this.typeOfLeave = typeOfLeave;
    }

    public int getDaysTaken() {
        return daysTaken;
    }

    public void setDaysTaken(int daysTaken) {
        this.daysTaken = daysTaken;
    }

    public int getDaysAvail() {
        return daysAvail;
    }

    public void setDaysAvail(int daysAvail) {
        this.daysAvail = daysAvail;
    }





}
