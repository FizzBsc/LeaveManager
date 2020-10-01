package com.example.quicklauncher;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;
import Model.Employee;
import Model.EmployeeLeaveAvailable;
import Model.LeaveApplication;

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
//        Database db = new Database(Login.this);
//        db.adduser("b", "123");
        Database d = new Database(Login.this);


        eIDField = (EditText) findViewById(R.id.eIDField);
        empPasswordField = (EditText) findViewById(R.id.empPasswordField);
        EmployeeLeaveAvailable ela = new EmployeeLeaveAvailable(null,null,null,0,0);
        Employee emp = new Employee("a",0430405557,"hafiz", "halid", "s@gmail.com", "admin", "a");
        Employee ep = new Employee("b",0430405557,"elmo", "sesame", "s@gmail.com", "manager", "b");
        Employee e = new Employee("c",0430405557,"Bobby", "Jones", "s@gmail.com", "staff", "b");
        LeaveApplication la = new LeaveApplication("try","c", "Annual Leave","30/09/2020","01/10/2020",1,"pending");
        LeaveApplication lA = new LeaveApplication("try1","a", "Annual Leave","02/10/2020","03/10/2020",1,"pending");
        LeaveApplication l = new LeaveApplication("try2","c", "Annual Leave","04/10/2020","05/10/2020",1,"pending");

        ela.addNewUserHolidays("b");
        ela.addNewUserHolidays("c");
        Log.d("password size", Integer.toString(d.employeeLeaveAvailArr.size()));

        d.loadPassToArr();
        d.loadLeaveTypeToArr();
        d.user.add(emp);
        d.user.add(ep);
        d.user.add(e);
        d.leaveApplicationDBArr.add(la);
        d.leaveApplicationDBArr.add(lA);
        d.leaveApplicationDBArr.add(l);
        for (int i = 0; i <d.passwordArr.size();i++) {
            Log.d("password", d.passwordArr.get(i).getPassword());
        }


        d.loadLeaveApplicationToArr();

        for(int i =0; i <d.employeeLeaveAvailArr.size(); i++) {
            Log.d("added here ", d.employeeLeaveAvailArr.get(i).geteLAID() + " " + Database.employeeLeaveAvailArr.get(i).geteID() + " " + Database.employeeLeaveAvailArr.get(i).getTypeOfLeave() + " " + Database.employeeLeaveAvailArr.get(i).getDaysAvail() + " " + Database.employeeLeaveAvailArr.get(i).getDaysTaken());
        }
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

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


}