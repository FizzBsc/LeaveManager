package com.example.sepmproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etEmployeeId;
    private EditText etEmployeeType;
    private EditText etDepartment;
    private EditText etName;
    private EditText etEmailId;
    private EditText etPhoneNo;
    private EditText etPassword;
    private TextView tvEmployeeId;
    private TextView tvEmployeeType;
    private TextView tvDepartment;
    private TextView tvName;
    private TextView tvEmailId;
    private TextView tvPhoneNo;
    private TextView tvPassword;
    private Button btnSignUp;

    boolean isValid = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmployeeId = findViewById(R.id.employeeIdET);
        etEmployeeType = findViewById(R.id.employeeTypeET);
        etDepartment = findViewById(R.id.departmentET);
        etName = findViewById(R.id.nameET);
        etEmailId = findViewById(R.id.emailIdET);
        etPhoneNo = findViewById(R.id.phoneET);
        etPassword = findViewById(R.id.passwordET);
        tvEmployeeId = findViewById(R.id.employeeIdTV);
        tvEmployeeType = findViewById(R.id.employeeTypeTV);
        tvDepartment = findViewById(R.id.departmentTV);
        tvName = findViewById(R.id.nameTV);
        tvEmailId = findViewById(R.id.emailTV);
        tvPhoneNo = findViewById(R.id.phoneTV);
        tvPassword = findViewById(R.id.password1TV);
        btnSignUp = findViewById(R.id.signUpButton);

        //etEmployeeId.setText("e00001");
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmployeeId = etEmployeeId.getText().toString();
                String inputEmployeeType = etEmployeeType.getText().toString();
                String inputDepartment = etDepartment.getText().toString();
                String inputName = etName.getText().toString();
                String inputEmailId = etEmailId.getText().toString();
                String inputPhoneNo = etPhoneNo.getText().toString();
                String inputPassword = etPassword.getText().toString();

                if (inputEmployeeId.charAt(0) != 'e' && !(inputEmployeeId.length() == 6)) {
                    Toast.makeText(MainActivity.this, "Employee Id should start with e and should be 6 characters long.", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (!(inputEmployeeType.equalsIgnoreCase("Admin"))) {
                    Toast.makeText(MainActivity.this, "Employee Type can be only of Admin as for others type admin will create login details.", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (inputPhoneNo.length() != 10 && inputPhoneNo.charAt(0) != '0') {
                    Toast.makeText(MainActivity.this, "Phone number should start with 0 and should be 10 characters long.", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (!(inputPassword.length() <= 12 && inputPassword.length() >= 8)) {
                    Toast.makeText(MainActivity.this, "Password should be in between 8 - 12 characters/numbers.", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else if (inputEmployeeId.isEmpty() || inputEmployeeType.isEmpty() ||
                        inputDepartment.isEmpty() || inputName.isEmpty() || inputEmailId.isEmpty() ||
                        inputPhoneNo.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                else {
                    isValid = true;
                }
                if(!isValid){
                    Toast.makeText(MainActivity.this, "Some of the Credentials are not correct.", Toast.LENGTH_SHORT).show();
                    btnSignUp.setEnabled(false);

                    //Add the code to go to new activity
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "SignUp Successful.", Toast.LENGTH_SHORT).show();

                    //Add the code to go to new activity
                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(intent);
                    }
                }


        });
    }
}