package com.example.quicklauncher;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String eid = getIntent().getStringExtra("ID");
        int index = 0;

        for (int i =0; i <db.user.size(); i++){
            if (db.user.get(i).getEmployeeID().equals(eid)){
                index = i;
            }
        }

        final int position = index;

        Log.d("indexcheck", String.valueOf(index));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);
        employmentTypeSpinner = findViewById(R.id.employmentTypeSpinner);
        updateBtn =(Button)findViewById(R.id.btnUpdate);
        employeeID = (TextView) findViewById(R.id.employeeID);

        employeeID.setText(eid);

        ArrayAdapter <String> typeOfLeaveAdapter = new ArrayAdapter<String>(EditEmployee.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.employeeType));
        typeOfLeaveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        employmentTypeSpinner.setAdapter(typeOfLeaveAdapter);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newEmpType = employmentTypeSpinner.getSelectedItem().toString();
                db.user.get(position).setEmploymentType(newEmpType);
                Log.d("empType",db.user.get(position).getEmploymentType());
            }
        });


    }


}