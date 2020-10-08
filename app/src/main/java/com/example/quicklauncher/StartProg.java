package com.example.quicklauncher;

import android.util.Log;

import Model.Database;
import Model.Employee;
import Model.LeaveApplication;

public class StartProg {

    public void setup(){

        Employee emp = new Employee("a",0430405557,"hafiz", "halid", "s@gmail.com", "admin", "a");
        Employee ep = new Employee("b",0430405557,"elmo", "sesame", "s@gmail.com", "manager", "b");
        Employee e = new Employee("c",0430405557,"Bobby", "Jones", "s@gmail.com", "staff", "b");
        LeaveApplication la = new LeaveApplication("try","c", "Annual Leave","30/09/2020","01/10/2020",1,"pending");
        LeaveApplication lA = new LeaveApplication("try1","a", "Annual Leave","02/10/2020","03/10/2020",1,"pending");
        LeaveApplication l = new LeaveApplication("try2","c", "Annual Leave","04/10/2020","05/10/2020",1,"pending");


//            LeaveTypeDB al = new LeaveTypeDB("1", "Annual Leave", 20);
//            LeaveTypeDB cl = new LeaveTypeDB("2", "Carer's Leave", 15);
//            LeaveTypeDB bdl = new LeaveTypeDB("3", "Blood Donor Leave", 5);
//            LeaveTypeDB slc = new LeaveTypeDB("4", "Sick Leave with certificate", 30);
//            LeaveTypeDB sl = new LeaveTypeDB("5", "Sick Leave without certificate", 7);
//            LeaveTypeDB pl = new LeaveTypeDB("6", "Parental Leave", 120);
//            LeaveTypeDB ul = new LeaveTypeDB("7", "Unpaid Leave", 365);
//            Database.leaveTypeDBArr.add(al);
//            Database.leaveTypeDBArr.add(cl);
//            Database.leaveTypeDBArr.add(bdl);
//            Database.leaveTypeDBArr.add(slc);
//            Database.leaveTypeDBArr.add(sl);
//            Database.leaveTypeDBArr.add(pl);
//            Database.leaveTypeDBArr.add(ul);


        Log.d("Start Here", "start Here");

        for (int i=0; i<Database.employeeLeaveAvailArr.size(); i++) {
            Log.d("Employee leaves", Database.employeeLeaveAvailArr.get(i).geteID() + " "+ Database.employeeLeaveAvailArr.get(i).getDaysAvail());
        }


        Database.user.add(emp);
        Database.user.add(ep);
        Database.user.add(e);

        Database.leaveApplicationDBArr.add(la);
        Database.leaveApplicationDBArr.add(lA);
        Database.leaveApplicationDBArr.add(l);
    }
}
