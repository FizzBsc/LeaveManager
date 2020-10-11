package com.example.quicklauncher;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Model.Database;
import Model.Employee;
import Model.EmployeeLeaveAvailable;


public class NewUser extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText phone;
    EditText email;
    Spinner manager;
    Spinner position;

    Button createUser;

    Database db = new Database(NewUser.this);

    ArrayList<String> managerList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        firstName = findViewById(R.id.firstNameText);
        lastName = findViewById(R.id.lastNameText);
        phone = findViewById(R.id.phoneText);
        email = findViewById(R.id.emailText);
        manager = findViewById(R.id.managerSpinner);
        position = findViewById(R.id.positionSpinner);

        createUser =(Button) findViewById(R.id.createButton);

        if (db.user.size() != 0){
            for (int i = 0; i < db.user.size(); i++){
                if (db.user.get(i).getEmploymentType().equals("manager")||db.user.get(i).getEmploymentType().equals("admin")){
                    managerList.add(db.user.get(i).getEmployeeID());
                }
            }
        }

        ArrayAdapter<String> managedBy = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, managerList);
        ArrayAdapter<String> typeOfemp = new ArrayAdapter<String>(NewUser.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.employeeType));
        typeOfemp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        manager.setAdapter(managedBy);
        position.setAdapter(typeOfemp);
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(firstName.getText().toString()) && !TextUtils.isEmpty(lastName.getText().toString()) && !TextUtils.isEmpty(phone.getText().toString()) && !TextUtils.isEmpty(email.getText().toString())) {
                    String fName = firstName.getText().toString();
                    String lName = lastName.getText().toString();
                    int pNo = Integer.parseInt(phone.getText().toString());
                    String mail = email.getText().toString();
                    String eid = "EMP" + (Database.user.size()+1);
                    String manID = manager.getSelectedItem().toString();
                    String pos = position.getSelectedItem().toString();

                    Employee newEmp = new Employee(eid,pNo,fName,lName,mail,pos,manID);
                    db.user.add(newEmp);
                    db.addUser(eid,Integer.toString(pNo),fName,lName,mail,pos,manID);
                    Log.d("user added ", eid + " " +fName+ " " + lName + " " +pNo+ " " +mail+ " " + manID+ " " +pos );
                    db.addPassword(eid,"password");
                    Log.d("password added", "yup");
                    EmployeeLeaveAvailable ela = new EmployeeLeaveAvailable();
                    addNewUserHolidays(eid);
                    Log.d("all done", "really");
                    Toast.makeText(NewUser.this, eid +" created",Toast.LENGTH_LONG).show();
                    Intent startIntent = new Intent(getApplicationContext(),MainMenu.class);
                    startActivity(startIntent);
                    finish();

                }

            }


        });
    }
    public void addNewUserHolidays(String a){
        Log.d("works here", "works here");
        for (int i = 0; i < Database.leaveTypeDBArr.size(); i++) {
            Log.d("works here again", Database.leaveTypeDBArr.get(i).getLeaveName());
            Database.employeeLeaveAvailArr.add(new EmployeeLeaveAvailable(a + Database.leaveTypeDBArr.get(i).getlTID(), a, Database.leaveTypeDBArr.get(i).getLeaveName(), 0, Database.leaveTypeDBArr.get(i).getDaysAvail()));
            db.addEmployeeLeaveAvailable(a + Database.leaveTypeDBArr.get(i).getlTID(), a, Database.leaveTypeDBArr.get(i).getLeaveName(), 0, Database.leaveTypeDBArr.get(i).getDaysAvail());
        }

    }
}