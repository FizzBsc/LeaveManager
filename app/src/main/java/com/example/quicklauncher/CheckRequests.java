package com.example.quicklauncher;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;

public class CheckRequests extends AppCompatActivity {

    ListView requestLeaveView;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_requests);

        requestLeaveView = (ListView) findViewById(R.id.requestListView);

        CustomAdapter customAdapter = new CustomAdapter();
        requestLeaveView.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter {

        Database db = new Database(CheckRequests.this);

        @Override
        public int getCount() {
            return db.leaveApplicationDBArr.size();
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

            convertView = getLayoutInflater().inflate(R.layout.customrequestlayout, null);

            TextView nameView = (TextView) convertView.findViewById(R.id.nameView1);
            TextView noOfDaysView = (TextView) convertView.findViewById(R.id.noOfDaysView);
            TextView typeOfLeaveView = (TextView) convertView.findViewById(R.id.typeOfLeaveView);
            TextView dateView1 = (TextView) convertView.findViewById(R.id.dateView1);
            ImageButton approveButton = (ImageButton) convertView.findViewById(R.id.approveBut);
            ImageButton rejectButton = (ImageButton) convertView.findViewById(R.id.deniedBut);

            int index = 0;

            for (int i = 0; i < db.user.size(); i++) {
                if (db.user.get(i).getEmployeeID().equals(Login.eid)) {
                    role = db.user.get(i).getEmploymentType();
                }
            }

            if (role.equals("manager") || role.equals("admin")) {//!!!!!!!!!!!!!!!! set manager and admin here
                if (role.equals("admin")) {
                    for (int i = 0; i < db.user.size(); i++) {
                        if (db.user.get(i).getEmployeeID().equals(db.leaveApplicationDBArr.get(position).geteID())) {
                            index = i;
                            break;
                        }
                    }
                    nameView.setText(db.user.get(index).getGivenName());
                    noOfDaysView.setText(Integer.toString(Database.leaveApplicationDBArr.get(position).getNoOfDays()));
                    typeOfLeaveView.setText(Database.leaveApplicationDBArr.get(position).getTypeOfLeave());
                    dateView1.setText(Database.leaveApplicationDBArr.get(position).getStartDate() + " - " + Database.leaveApplicationDBArr.get(position).getEndDate());
                }else if (role.equals("manager")){
                    for (int i = 0; i < db.user.size(); i++) {
                        if (db.user.get(i).getEmployeeID().equals(db.leaveApplicationDBArr.get(position).geteID())) {
                            index = i;
                            break;
                        }
                    }
                    if (Database.user.get(index).getManagedBy().equals(Login.eid)){
                        nameView.setText(db.user.get(index).getGivenName());
                        noOfDaysView.setText(Integer.toString(Database.leaveApplicationDBArr.get(position).getNoOfDays()));
                        typeOfLeaveView.setText(Database.leaveApplicationDBArr.get(position).getTypeOfLeave());
                        dateView1.setText(Database.leaveApplicationDBArr.get(position).getStartDate() + " - " +Database.leaveApplicationDBArr.get(position).getEndDate());
                    } else{
                        nameView.setVisibility(View.GONE);
                        noOfDaysView.setVisibility(View.GONE);
                        typeOfLeaveView.setVisibility(View.GONE);
                        dateView1.setVisibility(View.GONE);
                        approveButton.setVisibility(View.GONE);
                        rejectButton.setVisibility(View.GONE);
                    }
                }


            }else{
                nameView.setVisibility(View.GONE);
                noOfDaysView.setVisibility(View.GONE);
                typeOfLeaveView.setVisibility(View.GONE);
                dateView1.setVisibility(View.GONE);
                approveButton.setVisibility(View.GONE);
                rejectButton.setVisibility(View.GONE);

            }



            return convertView;

        }
    }
}