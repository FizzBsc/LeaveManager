package com.example.quicklauncher;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;

public class AvailableLeave extends AppCompatActivity {

    ListView empavail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_leave);
        empavail = (ListView) findViewById(R.id.empavailListView);

        CustomAdapter customAdapter = new CustomAdapter();
        empavail.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            Database db = new Database(AvailableLeave.this);
            return db.employeeLeaveAvailArr.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.customempleaveavail, null);

            RelativeLayout rl = (RelativeLayout) convertView.findViewById(R.id.relativeLayout6);
            TextView leaveName = (TextView) convertView.findViewById(R.id.leaveTypeView1);
            TextView daysAvail = (TextView) convertView.findViewById(R.id.daysLeftView1);
            TextView daysTaken = (TextView) convertView.findViewById(R.id.daysTakenView1);

            if (Database.employeeLeaveAvailArr.get(position).geteID().equals(Login.eid)){
                leaveName.setText("Leave name: " + Database.employeeLeaveAvailArr.get(position).getTypeOfLeave());
                daysAvail.setText("Days Available: " + Database.employeeLeaveAvailArr.get(position).getDaysAvail());
                daysTaken.setText("Days Taken: " + Database.employeeLeaveAvailArr.get(position).getDaysTaken());
            }else{
                leaveName.setVisibility(View.GONE);
                daysAvail.setVisibility(View.GONE);
                daysTaken.setVisibility(View.GONE);
            }
            return convertView;
        }
    }
}