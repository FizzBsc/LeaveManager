package Model;

public class Employee {
// Creating account for new employees
     int employeeID;
     int contactNumber;
     String givenName;
     String lastName;
     int employeeDOB;
     String position;
     String department;
     String employmentType;
     String employeeAddress;

     public Employee(int employeeID, int contactNumber, String givenName, String lastName, int employeeDOB, String position, String department, String employmentType, String employeeAddress) {
          this.employeeID = employeeID;
          this.contactNumber = contactNumber;
          this.givenName = givenName;
          this.lastName = lastName;
          this.employeeDOB = employeeDOB;
          this.position = position;
          this.department = department;
          this.employmentType = employmentType;
          this.employeeAddress = employeeAddress;
     }



     //---------------------------------------------------------------------
     public int getEmployeeID(){
          return this.employeeID;

     }

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

     public int getEmployeeDOB() {
          return employeeDOB;
     }

     public void setEmployeeDOB(int employeeDOB) {
          this.employeeDOB = employeeDOB;
     }

     public String getPosition() {
          return position;
     }

     public void setPosition(String position) {
          this.position = position;
     }

     public String getDepartment() {
          return department;
     }

     public void setDepartment(String department) {
          this.department = department;
     }

     public String getEmploymentType() {
          return employmentType;
     }

     public void setEmploymentType(String employmentType) {
          this.employmentType = employmentType;
     }

     public String getEmployeeAddress() {
          return employeeAddress;
     }

     public void setEmployeeAddress(String employeeAddress) {
          this.employeeAddress = employeeAddress;
     }

 //-----------------------------------------------------------------------------------------

}
