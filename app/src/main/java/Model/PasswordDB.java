package Model;

public class PasswordDB {

    public String eID;
    public String password;
    public boolean isNewEmp;



    public PasswordDB(String eID, String password, Boolean isNewEmp){
        this.eID = eID;
        this.password = password;
        this.isNewEmp = true;
    }
    public String getEID() {
        return eID;
    }

    public void setEID(String EID) {
        this.eID = eID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNewEmp() {
        return isNewEmp;
    }

    public void setNewEmp(boolean newEmp) {
        isNewEmp = newEmp;
    }




}
