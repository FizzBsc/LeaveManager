package com.example.quicklauncher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;
import Model.LeaveTypeDB;

import static Model.Database.passwordArr;

public class Login extends AppCompatActivity {

    public static String eid;
    String password;


    Button loginButton;
    EditText eIDField;
    EditText empPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Database d = new Database(Login.this);

        Database.leaveTypeDBArr.add(new LeaveTypeDB("1","Annual Leave", 20 ));
        Database.leaveTypeDBArr.add(new LeaveTypeDB("2","Carer's Leave", 15 ));
        Database.leaveTypeDBArr.add(new LeaveTypeDB("3","Blood Donor Leave", 365 ));
        Database.leaveTypeDBArr.add(new LeaveTypeDB("4","Sick Leave with certificate", 30 ));
        Database.leaveTypeDBArr.add(new LeaveTypeDB("5","Sick Leave without certificate", 7 ));
        Database.leaveTypeDBArr.add(new LeaveTypeDB("6","Parental Leave", 120 ));
        Database.leaveTypeDBArr.add(new LeaveTypeDB("6","Unpaid Leave", 365 ));

        d.addLeaveType();

        eIDField = (EditText) findViewById(R.id.eIDField);
        empPasswordField = (EditText) findViewById(R.id.empPasswordField);

        d.loadPassToArr();
        Log.d("array",passwordArr.get(0).eID + " " + passwordArr.get(0).isNewEmp);//delete before submit
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Database db = new Database(Login.this);
                //db.adduser("b", "123");
                Log.d("QuickLauncher", "its here");
                eid = eIDField.getText().toString();
                password = empPasswordField.getText().toString();

                if (checkPassword(eid,password) == true){
                    if (checkNewUser(eid) == false) {
                        Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(startIntent);
                    } else if(checkNewUser(eid) == true){
                        Intent startIntent = new Intent(getApplicationContext(), ChangePassword.class);
                        startActivity(startIntent);
                    }
                }

            }
        });


    }
    public boolean checkPassword(String ID, String password){
        for (int i = 0; i < passwordArr.size(); i++){
            if (passwordArr.get(i).eID.equals(ID)){
                if (passwordArr.get(i).password.equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkNewUser(String ID){
        for (int i = 0; i < passwordArr.size(); i++){
            if(passwordArr.get(i).eID.equals(ID)) {
                if (passwordArr.get(i).isNewEmp() == true) {
                    return true;
                }else{
                    return false;
                }
            }
        }
        return true;
    }


}