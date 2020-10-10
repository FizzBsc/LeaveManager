package Model;

public class PublicHoliday {

    String pHId;
    String holidayName;
    String holidayDate;

    public PublicHoliday(String pHId, String holidayName, String holidayDate) {
        this.pHId = pHId;
        this.holidayName = holidayName;
        this.holidayDate = holidayDate;
    }

    public String getpHId() {
        return pHId;
    }

    public void setpHId(String pHId) {
        this.pHId = pHId;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(String holidayDate) {
        this.holidayDate = holidayDate;
    }



}
