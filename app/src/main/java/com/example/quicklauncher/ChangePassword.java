package com.example.quicklauncher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;

public class ChangePassword extends AppCompatActivity {

    String newPass;
    String checkNewPass;

    Button submitNewPassBut;
    EditText newPassword;
    EditText checkNewPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPassword = (EditText) findViewById(R.id.newPassword);
        checkNewPassword = (EditText) findViewById(R.id.checkNewPassword);

        submitNewPassBut = (Button) findViewById(R.id.submitNewPassBut);
        submitNewPassBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPass = newPassword.getText().toString();
                checkNewPass = checkNewPassword.getText().toString();

                if(newPass.equals(checkNewPass)){
                    Database db = new Database(ChangePassword.this);
                    for(int i = 0; i < db.passwordArr.size(); i++){
                        if (db.passwordArr.get(i).eID.equals(Login.eid)){
                            db.passwordArr.get(i).setPassword(newPass);
                            db.passwordArr.get(i).setNewEmp(false);
                            db.updateNewUsr(Login.eid, newPass, "false");
                            String hi = Boolean.toString(Database.passwordArr.get(i).isNewEmp);
                            Log.d("QuickLauncher", hi);
                            //add dialog box if there's time
                            Intent startIntent = new Intent(getApplicationContext(), Login.class);
                            startActivity(startIntent);
                            finish();
                        }
                    }
                }

            }
        });

    }
}