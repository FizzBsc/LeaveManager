package com.example.quicklauncher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new Database(this);

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
}