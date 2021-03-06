package com.example.quicklauncher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;

public class EditEmployee extends AppCompatActivity {
    Button updateBtn;
    Spinner employmentTypeSpinner;
    EditText employmentTypeText;
    EditText employmentStatusText;
    TextView employeeID;
    Database db = new Database(EditEmployee.this);
    String newEmpType = null;
    Switch statusSw;
    boolean isEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String eid = getIntent().getStringExtra("ID");
        int index = 0;

        for (int i =0; i <db.user.size(); i++){
            if (db.user.get(i).getEmployeeID().equals(eid)){
                index = i;
                isEmp = db.user.get(i).isStatus();
            }
        }

        final int position = index;

        Log.d("indexcheck", String.valueOf(index));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);
        employmentTypeSpinner = findViewById(R.id.employmentTypeSpinner);
        updateBtn =(Button)findViewById(R.id.btnUpdate);
        employeeID = (TextView) findViewById(R.id.employeeID);
        statusSw = (Switch) findViewById(R.id.statusSwitch);
        if (isEmp == true) {
            statusSw.setChecked(true);
        }

        employeeID.setText("Employee ID: " + eid +" \nName: " + db.user.get(position).getGivenName() + " " + db.user.get(position).getLastName() );

        ArrayAdapter <String> typeOfemp = new ArrayAdapter<String>(EditEmployee.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.employeeType));
        typeOfemp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        employmentTypeSpinner.setAdapter(typeOfemp);

        statusSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    db.user.get(position).setStatus(true);
                    Log.d("Set status", "works");
                    db.updateEmpStatus(db.user.get(position).getEmployeeID(),true);

                    Toast.makeText(getApplicationContext(), "Is a current employee" , Toast.LENGTH_SHORT).show();
                } else if(isChecked==false){
                    db.user.get(position).setStatus(false);
                    db.updateEmpStatus(db.user.get(position).getEmployeeID(),false);

                    Toast.makeText(getApplicationContext(), "Is not a current employee" , Toast.LENGTH_SHORT).show();

                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newEmpType = employmentTypeSpinner.getSelectedItem().toString();
                db.user.get(position).setEmploymentType(newEmpType);
                db.updateEmpType(db.user.get(position).getEmployeeID(),newEmpType);
                Log.d("empType",db.user.get(position).getEmploymentType());
                Toast.makeText(getApplicationContext(), db.user.get(position).getEmployeeID() +" updated" , Toast.LENGTH_SHORT).show();
                Intent startIntent = new Intent(getApplicationContext(), StaffList.class);
                startActivity(startIntent);
                finish();

            }
        });


    }


}