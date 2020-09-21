package com.example.sepmproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword1;
    private TextView tvUsername;
    private TextView tvPassword1;
    private Button btnLogin;
    private Button btnSignUp1;

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        etUsername = findViewById(R.id.usernameET);
        etPassword1 = findViewById(R.id.password1ET);
        tvUsername = findViewById(R.id.userNameTV);
        //tvPassword1 = findViewById(R.id.password1TV);
        btnLogin = findViewById(R.id.loginButton);
        btnSignUp1 = findViewById(R.id.signUp1Button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = etUsername.getText().toString();
                String inputPassword1 = etPassword1.getText().toString();

                if(inputUsername.isEmpty() || inputPassword1.isEmpty()){
                    Toast.makeText(MainActivity3.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                }else{
                    isValid = validate(inputUsername, inputPassword1);

                    if(!isValid){

                        Toast.makeText(MainActivity3.this, "Incorrect Credentials entered.", Toast.LENGTH_SHORT).show();
                        btnLogin.setEnabled(false);
                        Intent intent = new Intent(MainActivity3.this, MainActivity3.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(MainActivity3.this, "Login Successful.", Toast.LENGTH_SHORT).show();

                        //Add the code to go to new activity
                        Intent intent = new Intent(MainActivity3.this, AdminHomePage.class);
                        startActivity(intent);
                    }

                }

            }
        });

        btnSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private boolean validate(String employeeId, String password) {
        if(employeeId.charAt(0) == 'e' && employeeId.length() == 6 && password.length() <=12 && password.length() >= 8){
            return true;
        }
        return false;
    }
}