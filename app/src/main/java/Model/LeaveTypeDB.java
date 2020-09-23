package Model;

public class LeaveTypeDB {

    String lTID;
    String LeaveName;
    int daysAvail;

    public LeaveTypeDB(String lTID, String leaveName, int daysAvail) {
        this.lTID = lTID;
        LeaveName = leaveName;
        this.daysAvail = daysAvail;
    }


    public String getlTID() {
        return lTID;
    }

    public void setlTID(String lTID) {
        this.lTID = lTID;
    }

    public String getLeaveName() {
        return LeaveName;
    }

    public void setLeaveName(String leaveName) {
        LeaveName = leaveName;
    }

    public int getDaysAvail() {
        return daysAvail;
    }

    public void setDaysAvail(int daysAvail) {
        this.daysAvail = daysAvail;
    }


}
