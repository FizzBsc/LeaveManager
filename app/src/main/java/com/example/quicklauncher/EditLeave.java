package com.example.quicklauncher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;

public class EditLeave extends AppCompatActivity {

    TextView idView;
    TextView leaveNameView;
    TextView daysView;
    EditText editTextNumber;
    Button subBut;
    Button canBut;
    int newDays;
    String lName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_leave);

        final int index = getIntent().getIntExtra("ID",0);
        idView = (TextView) findViewById(R.id.idView);
        leaveNameView = (TextView) findViewById(R.id.leaveNameView);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);

        lName = Database.leaveTypeDBArr.get(index).getLeaveName();
        idView.setText("ID: " + Database.leaveTypeDBArr.get(index).getlTID());
        leaveNameView.setText(lName);

        subBut = (Button) findViewById(R.id.subButton);
        subBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Database db = new Database(EditLeave.this);
                newDays = Integer.parseInt(editTextNumber.getText().toString());
                Database.leaveTypeDBArr.get(index).setDaysAvail(newDays);
                db.updateLeaveType(Database.leaveTypeDBArr.get(index).getlTID(),newDays);
                Toast.makeText(getApplicationContext(), lName + " has been updated", Toast.LENGTH_SHORT).show();

                Intent startIntent = new Intent(getApplicationContext(),ViewLeaveTypes.class);
                startActivity(startIntent);
                finish();
            }
        });

        canBut = (Button) findViewById(R.id.canButton);
        canBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),ViewLeaveTypes.class);
                startActivity(startIntent);
                finish();
            }
        });

    }
}