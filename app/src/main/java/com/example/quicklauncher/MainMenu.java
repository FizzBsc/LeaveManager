package com.example.quicklauncher;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       String employeeType = "hi";
       Log.d("Employee type: ", employeeType);

        for (int i = 0; i < Database.user.size(); i++){
            Log.d("employee type", Database.user.get(i).getGivenName());
            if (Database.user.get(i).getEmployeeID().equals(Login.eid)){
                employeeType = Database.user.get(i).getEmploymentType();
                Log.d("employee type1", employeeType);
            }
        }
        for (int i = 0; i<Database.employeeLeaveAvailArr.size(); i++){//PBI6
            if (Database.employeeLeaveAvailArr.get(i).geteID().equals(Login.eid)){
                if (Database.employeeLeaveAvailArr.get(i).getTypeOfLeave().equals("Annual Leave")){
                    if(Database.employeeLeaveAvailArr.get(i).getDaysTaken()==0){
                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(MainMenu.this);
                        dlgAlert.setMessage("You have not taken any Annual Leave this year");
                        dlgAlert.setTitle("Leave Manager");
                        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                            }
                        });
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                    }
                }
            }
        }

        Log.d("Employee e: ", employeeType);
        Button leaveHistryBtn = (Button) findViewById(R.id.leaveHistoryBtn);
        leaveHistryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button notificationButton = (Button) findViewById(R.id.notificationButton);
        if (checkNotification(notificationButton, employeeType) == true){
            notificationButton.setError(" ");
        }
        Log.d("checkk",Boolean.toString(checkNotification(notificationButton, employeeType)));
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Notification.class);
                startActivity(startIntent);
            }
        });
        Button applyLeaveBtn = (Button) findViewById(R.id.applyLeaveBtn);
        applyLeaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),ApplyForLeave.class);
                startActivity(startIntent);
            }
        });

        Button viewRqstBtn = (Button) findViewById(R.id.viewRequestsBtn);
        //viewRqstBtn.setVisibility(Button.GONE);
        viewRqstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button availableLeaveBtn = (Button) findViewById(R.id.availableLeaveBtn);
        availableLeaveBtn.setVisibility(Button.GONE);
        availableLeaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button viewStffBtn = (Button) findViewById(R.id.viewStffBtn);
        viewStffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),StaffList.class);
                startActivity(startIntent);
            }
        });

        Button mngReqBtn = (Button) findViewById(R.id.mngRequestsBtn);
        mngReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),CheckRequests.class);
                startActivity(startIntent);
            }
        });

        Button createEmpBtn = (Button) findViewById(R.id.createEmpBtn);
        createEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), NewUser.class);
                startActivity(startIntent);
            }
        });

        Button addPblcHldyBtn = (Button) findViewById(R.id.addPblcHldyBtn);
        addPblcHldyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),AddPublicHoliday.class);
                startActivity(startIntent);
            }
        });

        Button dfltLeaveBtn = (Button) findViewById(R.id.dfltLeaveBtn);
        dfltLeaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),ViewLeaveTypes.class);
                startActivity(startIntent);
            }
        });

        if (employeeType.equals("manager")) {
            createEmpBtn.setVisibility(Button.GONE);
            dfltLeaveBtn.setVisibility(Button.GONE);
            addPblcHldyBtn.setVisibility(Button.GONE);
        }else if(employeeType.equals("staff")) {
            createEmpBtn.setVisibility(Button.GONE);
            dfltLeaveBtn.setVisibility(Button.GONE);
            addPblcHldyBtn.setVisibility(Button.GONE);
            mngReqBtn.setVisibility(Button.GONE);
            viewStffBtn.setVisibility(Button.GONE);
            notificationButton.setVisibility(View.GONE);
        }
    }
    public boolean checkNotification(Button but,String empType){
        Database db = new Database(MainMenu.this);

        boolean manage = false;
        for (int i = 0; i < Database.user.size(); i++){
            if (Database.user.get(i).getManagedBy().equals(Login.eid)){
                manage = true;
            }
        }
        if (empType.equals("manager")){
            if (manage == true) {
                for (int i = 0; i<Database.employeeLeaveAvailArr.size(); i++) {
                    if (db.employeeLeaveAvailArr.get(i).getTypeOfLeave().equals("Annual Leave")) {
                        if (db.employeeLeaveAvailArr.get(i).getDaysTaken() == 0) {
                            Log.d("E","error");
                            but.setError(" ");
                            return true;
                        }
                    }
                }
            }

        }
            return false;
    }
}