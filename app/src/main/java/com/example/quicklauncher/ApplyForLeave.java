package com.example.quicklauncher;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Model.Database;
import Model.LeaveApplication;

public class ApplyForLeave extends AppCompatActivity {

    DatePickerDialog picker;
    EditText startDateText;
    EditText endDateText;
    Button submitLeavebutton;
    Button cancelBut;
    TextView messageView;
    Date firstDate;
    Date secondDate;
    String leaveName;
    Database db = new Database(ApplyForLeave.this);


    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_leave);

        final Spinner leaveSpinner = (Spinner) findViewById(R.id.typeOfLeaveSpinner);

        ArrayAdapter <String> typeOfLeaveAdapter = new ArrayAdapter<String>(ApplyForLeave.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.typeOfLeave));
        typeOfLeaveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        leaveSpinner.setAdapter(typeOfLeaveAdapter);

        startDateText = (EditText) findViewById(R.id.startDateText);
        startDateText.setInputType(InputType.TYPE_NULL);
        startDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ApplyForLeave.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                startDateText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        endDateText = (EditText) findViewById(R.id.endDateText);
        endDateText.setInputType(InputType.TYPE_NULL);
        endDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ApplyForLeave.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                endDateText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        messageView = (TextView) findViewById(R.id.messageView);
        submitLeavebutton = (Button) findViewById(R.id.submitLeavebutton);
        submitLeavebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d1 = startDateText.getText().toString();
                String d2 = endDateText.getText().toString();
                try {
                    firstDate = df.parse(d1);
                    secondDate = df.parse(d2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int i = dayCalc(firstDate,secondDate);
                String s =  Integer.toString(i);
                int m = 0;
                String laID = null;
                if (i>0) {
                    int iDCount = 0;
                    leaveName = leaveSpinner.getSelectedItem().toString();
                    for (int j = 0; j < Database.employeeLeaveAvailArr.size(); j++){
                        if ( Database.employeeLeaveAvailArr.get(j).getTypeOfLeave().equals(leaveName) &&  Login.eid.equals(Database.employeeLeaveAvailArr.get(j).geteID())) {
                            for (int k = 0; k < Database.leaveApplicationDBArr.size(); k++) {
                                if (!(leaveName.equals(Database.leaveApplicationDBArr.get(k).getTypeOfLeave()))) {
                                    iDCount = 0;
                                } else if (leaveName.equals(Database.leaveApplicationDBArr.get(k).getTypeOfLeave())) {
                                    iDCount++;
                                }

                            }
                            laID = Database.employeeLeaveAvailArr.get(j).geteLAID()+ iDCount;

                            m = Database.employeeLeaveAvailArr.get(j).getDaysAvail();

                        }
                    }
                    if (m > i) {
                        LeaveApplication la = new LeaveApplication(laID, Login.eid, leaveName, d1, d2, i, "pending");
                        db.leaveApplicationDBArr.add(la);
                        db.addLeaveApplication(laID, Login.eid, leaveName, d1, d2, i, "pending");
                        for (int z = 0; z<Database.leaveApplicationDBArr.size(); z++) {
                            Log.d("Leave approved", iDCount +" "+Database.leaveApplicationDBArr.get(z).leaveAppID +" "+Database.leaveApplicationDBArr.get(z).eID);
                        }
                    } else{
                        messageView.setError(" ");
                        messageView.setTextColor(Color.RED);
                        messageView.setText("You do not have enough days avail");
                    }

                }else if (i<=0){
                    messageView.setError(" ");
                    messageView.setTextColor(Color.RED);
                    messageView.setText("End Date is before Start Date");
                }
                Log.d("numberOfDays", Integer.toString(i));
            }
        });
        cancelBut = (Button) findViewById(R.id.cancelBut);
        cancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(startIntent);
            }
        });
    }

    public int dayCalc(Date d1, Date d2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);

        if (d1.compareTo(d2) <=0) {
            int numberOfDays = 1;
            while (cal1.before(cal2)) {
                if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                        && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                    numberOfDays++;
                }
                cal1.add(Calendar.DATE, 1);
            }
            return numberOfDays;
        }else {
            return 0;
        }

    }
}