package Model;

import android.content.Context;

public class EmployeeLeaveAvailable {

    String eLAID;
    String eID;
    String typeOfLeave;
    int daysTaken;
    int daysAvail;

    public  EmployeeLeaveAvailable(){

    }
    public EmployeeLeaveAvailable(String eLAID, String eID, String typeOfLeave, int daysTaken, int daysAvail) {
        this.eLAID = eLAID;
        this.eID = eID;
        this.typeOfLeave = typeOfLeave;
        this.daysTaken = daysTaken;
        this.daysAvail = daysAvail;
    }

    private Context mContext;




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
