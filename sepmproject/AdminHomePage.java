package com.example.sepmproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminHomePage extends AppCompatActivity {

    private Button btnCreateAccount;
    private Button btnListOfAllEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        btnCreateAccount = findViewById(R.id.adminCreateAccountBtn);
        btnListOfAllEmployees = findViewById(R.id.adminTableViewAllEmployeeBtn);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnListOfAllEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomePage.this, ListViewOfAllEmployees.class);
                startActivity(intent);
            }
        });
    }
}