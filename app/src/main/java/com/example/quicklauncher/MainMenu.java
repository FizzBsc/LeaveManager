package com.example.quicklauncher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Button usrSettBtn = (Button) findViewById(R.id.usrSettingsBtn);
        //usrSettBtn.setVisibility(Button.GONE);
        usrSettBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button viewStffBtn = (Button) findViewById(R.id.viewStffBtn);
        //viewStffBtn.setVisibility(Button.GONE);
        viewStffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),StaffList.class);
                startActivity(startIntent);
            }
        });

        Button mngReqBtn = (Button) findViewById(R.id.mngRequestsBtn);
        //mngReqBtn.setVisibility(Button.GONE);
        mngReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button createEmpBtn = (Button) findViewById(R.id.createEmpBtn);
        //createEmpBtn.setVisibility(Button.GONE);
        createEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

        Button addPblcHldyBtn = (Button) findViewById(R.id.addPblcHldyBtn);
        //addPblcHldyBtn.setVisibility(Button.GONE);
        addPblcHldyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),AddPublicHoliday.class);
                startActivity(startIntent);
            }
        });

        Button dfltLeaveBtn = (Button) findViewById(R.id.dfltLeaveBtn);
        //dfltLeaveBtn.setVisibility(Button.GONE);
        dfltLeaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Error404.class);
                startActivity(startIntent);
            }
        });

    }
}