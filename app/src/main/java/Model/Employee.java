package Model;

public class Employee {
// Creating account for new employees
     int employeeID;
     int contactNumber;
     String givenName;
     String lastName;
     String email;
     String employmentType; //Admin, manager or staff
     boolean status; //if this is an active employee or not

     public Employee(int employeeID, int contactNumber, String givenName, String lastName, String email, String employmentType) {

          this.employeeID = employeeID;
          this.contactNumber = contactNumber;
          this.givenName = givenName;
          this.lastName = lastName;
          this.email = email;
          this.employmentType = employmentType;
          this.status = true;
     }

     //---------------------------------------------------------------------
     public int getEmployeeID(){ return this.employeeID; }

     public void setEmployeeID(int employeeID) {
          this.employeeID = employeeID;
     }

     public int getContactNumber() {
          return contactNumber;
     }

     public void setContactNumber(int contactNumber) {
          this.contactNumber = contactNumber;
     }

     public String getGivenName() {
          return givenName;
     }

     public void setGivenName(String givenName) {
          this.givenName = givenName;
     }

     public String getLastName() {
          return lastName;
     }

     public void setLastName(String lastName) {
          this.lastName = lastName;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getEmploymentType() {
          return employmentType;
     }

     public void setEmploymentType(String employmentType) {
          this.employmentType = employmentType;
     }

     public boolean isStatus() {return status; }

     public void setStatus(boolean status) { this.status = status; }
 //-----------------------------------------------------------------------------------------

}
