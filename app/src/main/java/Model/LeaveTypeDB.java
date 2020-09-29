package Model;

public class LeaveTypeDB {

    String lTID;
    String leaveName;
    int daysAvail;

    public LeaveTypeDB(String lTID, String leaveName, int daysAvail) {
        this.lTID = lTID;
        this.leaveName = leaveName;
        this.daysAvail = daysAvail;
    }


    public String getlTID() {
        return lTID;
    }

    public void setlTID(String lTID) {
        this.lTID = lTID;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        leaveName = leaveName;
    }

    public int getDaysAvail() {
        return daysAvail;
    }

    public void setDaysAvail(int daysAvail) {
        this.daysAvail = daysAvail;
    }


}
