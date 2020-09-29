package Model;

public class LeaveApplication {


    public String leaveAppID;
    public String eID;
    public String typeOfLeave;
    public String startDate;
    public String endDate;
    public int noOfDays;
    public String approvalStatus;

    public LeaveApplication(String leaveAppID, String eID, String typeOfLeave, String startDate, String endDate, int noOfDays, String approvalStatus) {
        this.leaveAppID = leaveAppID;
        this.eID = eID;
        this.typeOfLeave = typeOfLeave;
        this.startDate = startDate;
        this.endDate = endDate;
        this.noOfDays = noOfDays;
        this.approvalStatus = approvalStatus;
    }

    public String getLeaveAppID() {
        return leaveAppID;
    }

    public void setLeaveAppID(String leaveAppID) {
        this.leaveAppID = leaveAppID;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }


}
