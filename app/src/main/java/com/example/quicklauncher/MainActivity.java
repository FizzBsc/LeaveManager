package com.example.quicklauncher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Database myDb;
    HolidayDB Hdb;
    EditText editDate,editMonth,editYear,editDay;
    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new Database(this);
        Hdb = new HolidayDB(this);

        editDate = (EditText) findViewById(R.id.editText_DATE);
        editMonth = (EditText) findViewById(R.id.editText_MONTH);
        editYear = (EditText) findViewById(R.id.editText_YEAR);
        editDay = (EditText) findViewById(R.id.editText_DAY);
        btnAddData = (Button) findViewById(R.id.button_add);
        AddData();




        Button leaveHistryBtn = (Button) findViewById(R.id.leaveHistoryBtn);
        leaveHistryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button applyLeaveBtn = (Button) findViewById(R.id.applyLeaveBtn);
        applyLeaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button viewRqstBtn = (Button) findViewById(R.id.viewRequestsBtn);
        viewRqstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button usrSettBtn = (Button) findViewById(R.id.usrSettingsBtn);
        usrSettBtn.setOnClickListener(new View.OnClickListener() {
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
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button mngReqBtn = (Button) findViewById(R.id.mngRequestsBtn);
        mngReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button createEmpBtn = (Button) findViewById(R.id.createEmpBtn);
        createEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button addPblcHldyBtn = (Button) findViewById(R.id.addPblcHldyBtn);
        addPblcHldyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button dfltLeaveBtn = (Button) findViewById(R.id.dfltLeaveBtn);
        dfltLeaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

    }
    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = Hdb.insertData(editDate.getText().toString(),
                                editMonth.getText().toString(),
                                editYear.getText().toString(),
                                editDay.getText().toString());
                        if(isInserted = true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();


                    }
                }


        );
    }
}