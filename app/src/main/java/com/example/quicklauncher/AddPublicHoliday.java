package com.example.quicklauncher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPublicHoliday extends AppCompatActivity {
    Database myDb;
    HolidayDB Hdb;
    EditText editDate,editMonth,editYear,editDay;
    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_public_holiday);
        myDb = new Database(this);
        Hdb = new HolidayDB(this);

        editDate = (EditText) findViewById(R.id.editText_DATE);
        editMonth = (EditText) findViewById(R.id.editText_MONTH);
        editYear = (EditText) findViewById(R.id.editText_YEAR);
        editDay = (EditText) findViewById(R.id.editText_DAY);
        btnAddData = (Button) findViewById(R.id.button_add);
        AddData();
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
                            Toast.makeText(AddPublicHoliday.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddPublicHoliday.this,"Data not Inserted",Toast.LENGTH_LONG).show();


                    }
                }


        );
    }
}