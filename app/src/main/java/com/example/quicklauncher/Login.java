package com.example.quicklauncher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;

import static Model.Database.passwordArr;

public class Login extends AppCompatActivity {

    public static String eid;
    String password;


    Button loginButton;
    EditText eIDField;
    EditText empPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eIDField = (EditText) findViewById(R.id.eIDField);
        empPasswordField = (EditText) findViewById(R.id.empPasswordField);
        Database d = new Database(Login.this);
        d.loadPassToArr();
        Log.d("array",passwordArr.get(0).eID + " " + passwordArr.get(0).isNewEmp);//delete before submit
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Database db = new Database(Login.this);
                //db.adduser("b", "123");
                Log.d("QuickLauncher", "its here");
                eid = eIDField.getText().toString();
                password = empPasswordField.getText().toString();

                if (checkPassword(eid,password) == true){
                    if (checkNewUser(eid) == false) {
                        Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(startIntent);
                    } else if(checkNewUser(eid) == true){
                        Intent startIntent = new Intent(getApplicationContext(), ChangePassword.class);
                        startActivity(startIntent);
                    }
                }

            }
        });


    }
    public boolean checkPassword(String ID, String password){
        for (int i = 0; i < passwordArr.size(); i++){
            if (passwordArr.get(i).eID.equals(ID)){
                if (passwordArr.get(i).password.equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkNewUser(String ID){
        for (int i = 0; i < passwordArr.size(); i++){
            if(passwordArr.get(i).eID.equals(ID)) {
                if (passwordArr.get(i).isNewEmp() == true) {
                    return true;
                }else{
                    return false;
                }
            }
        }
        return true;
    }


}