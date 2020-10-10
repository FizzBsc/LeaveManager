package com.example.quicklauncher;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import Model.Database;
import Model.PublicHoliday;

public class AddPublicHoliday extends AppCompatActivity {

    DatePickerDialog picker;
    EditText holidayNameText,datePicker;
    Button btnAddData;
    String holidayID;
    ListView phListview;
    Database db = new Database(AddPublicHoliday.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_public_holiday);


        holidayNameText = (EditText) findViewById(R.id.holidayNameText);
        datePicker = (EditText) findViewById(R.id.datePicker);
        btnAddData = (Button) findViewById(R.id.addHolidayBtn);


        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddPublicHoliday.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                datePicker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        phListview = (ListView) findViewById(R.id.holidayList);

        final CustomAdapter customAdapter = new CustomAdapter();
        phListview.setAdapter(customAdapter);

        btnAddData.setOnClickListener(new View.OnClickListener() {

            String holidayName;
            String holidayDate;

            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(holidayNameText.getText().toString()) ||TextUtils.isEmpty(datePicker.getText().toString())) {
                    Toast.makeText(AddPublicHoliday.this, "Fields cannot be empty",Toast.LENGTH_LONG).show();
                }else{
                    holidayName = holidayNameText.getText().toString();
                    holidayDate = datePicker.getText().toString();
                    holidayID = holidayName.substring(0, 3).toUpperCase() + (db.publicHolidayArrayList.size() + 1);

                    PublicHoliday ph = new PublicHoliday(holidayID, holidayName, holidayDate);
                    db.publicHolidayArrayList.add(ph);
                    for (int i = 0; i < db.publicHolidayArrayList.size(); i++) {
                        Log.d("public Holidays", db.publicHolidayArrayList.get(i).getpHId() + " " + db.publicHolidayArrayList.get(i).getHolidayName() + " " + db.publicHolidayArrayList.get(i).getHolidayDate());
                    }
                    customAdapter.notifyDataSetChanged();
                }

            }
        });


        }
        class CustomAdapter extends BaseAdapter{

            @Override
            public int getCount() {
                Database db = new Database(AddPublicHoliday.this);

                return db.publicHolidayArrayList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.customholiday, null);
                RelativeLayout rl = (RelativeLayout) convertView.findViewById(R.id.relativeLayout5);
                TextView holidayName = (TextView) convertView.findViewById(R.id.leaveTypeView1);
                TextView holidayDate = (TextView) convertView.findViewById(R.id.daysLeftView1);
                Button deleteBut = (Button) convertView.findViewById(R.id.deleteButton);

                holidayName.setText(db.publicHolidayArrayList.get(position).getHolidayName());
                holidayDate.setText(db.publicHolidayArrayList.get(position).getHolidayDate());
                deleteBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.publicHolidayArrayList.remove(position);
                        notifyDataSetChanged();                    }
                });
                return convertView;
            }
        }
    }