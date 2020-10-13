package com.example.quicklauncher;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;

import static Model.Database.passwordArr;

public class Login extends AppCompatActivity {

    public static String eid;
    String password;
    int tries = 0;

    Button loginButton;
    EditText eIDField;
    EditText empPasswordField;
    TextView triesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Database d = new Database(Login.this);
        d.loadHoliday();
        d.loadPassToArr();
        d.loadLeaveTypeToArr();
        d.loadLeaveApplicationToArr();
        d.loadEmployeeLeaveAvailable();
        d.loadUser();
        StartProg sp= new StartProg();
        sp.setup();
        if (Database.passwordArr.size()==0){
            d.addPassword("B","123");
            d.passwordArr.get(0).setNewEmp(false);
        }

        eIDField = (EditText) findViewById(R.id.eIDField);
        empPasswordField = (EditText) findViewById(R.id.empPasswordField);



//        for (int i = 0; i <Database.leaveApplicationDBArr.size();i++ ){
//            Log.d("leave Applications",  Database.leaveApplicationDBArr.get(i).getLeaveAppID() +" "+Database.leaveApplicationDBArr.get(i).getApprovalStatus());
//
//        }
//        for(int x = 0; x < Database.leaveTypeDBArr.size();x++) {
//            Log.d("checkLeaveType", Database.leaveTypeDBArr.get(x).getLeaveName());
//        }
//        for (int i = 0; i <d.passwordArr.size();i++) {
//            Log.d("password", d.passwordArr.get(i).getEID() +" "+d.passwordArr.get(i).getPassword());
//        }
        for (int i = 0; i < Database.employeeLeaveAvailArr.size(); i++){
            Log.d("Leave avail", d.employeeLeaveAvailArr.get(i).geteID());

        }




        for (int i =0; i <d.employeeLeaveAvailArr.size(); i++) {
            if (d.employeeLeaveAvailArr.get(i).geteLAID()==null){
                d.employeeLeaveAvailArr.remove(i);
            }

            //Log.d("added here ", d.employeeLeaveAvailArr.get(i).geteLAID() + " " + Database.employeeLeaveAvailArr.get(i).geteID() + " " + Database.employeeLeaveAvailArr.get(i).getTypeOfLeave() + " " + Database.employeeLeaveAvailArr.get(i).getDaysAvail() + " " + Database.employeeLeaveAvailArr.get(i).getDaysTaken());
        }

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                eid = eIDField.getText().toString().toUpperCase();
                password = empPasswordField.getText().toString();



                if (checkPassword(eid,password) == true){
                    if (checkNewUser(eid) == false) {
                        checkAnnualLeave();
                        Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(startIntent);
                        finish();



                    } else if(checkNewUser(eid) == true){
                        Intent startIntent = new Intent(getApplicationContext(), ChangePassword.class);
                        startActivity(startIntent);
                        finish();
                    }
                } else if (checkPassword(eid,password) == false){
                    empPasswordField.setError("Wrong Password");
                    tries++;
                    triesText = (TextView) findViewById(R.id.passErrorText);
                    triesText.setError("");
                    triesText.setTextColor(Color.RED);
                    triesText.setText("Attempt left:" + (3-tries));
                    if (tries == 3){
                        triesText.setText("You've been locked out please contact an Administrator");
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
    public void checkAnnualLeave() {
        for (int i = 0; i < Database.employeeLeaveAvailArr.size(); i++) {//PBI6
            Log.d("yup", eid);

            if (Database.employeeLeaveAvailArr.get(i).geteID().equals(eid)) {
                Log.d("yup", Database.employeeLeaveAvailArr.get(i).geteID());

                if (Database.employeeLeaveAvailArr.get(i).getTypeOfLeave().equals("Annual Leave")) {
                    if (Database.employeeLeaveAvailArr.get(i).getDaysTaken() == 0) {
                        Toast.makeText(Login.this, "You have not taken any leave",Toast.LENGTH_LONG).show();

                    }
                }
            }
        }
    }
}