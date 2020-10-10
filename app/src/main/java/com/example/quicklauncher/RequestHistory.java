package com.example.quicklauncher;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;

public class RequestHistory extends AppCompatActivity {

    ListView historyLeaveView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_history);
        historyLeaveView = (ListView) findViewById(R.id.reqPendListView);

        CustomAdapter customAdapter = new CustomAdapter();
        historyLeaveView.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter {

        Database db = new Database(RequestHistory.this);

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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.customreqhistlayout, null);

            TextView noOfDaysView = (TextView) convertView.findViewById(R.id.noOfDaysView4);
            TextView typeOfLeaveView = (TextView) convertView.findViewById(R.id.typeOfLeaveView3);
            TextView dateView1 = (TextView) convertView.findViewById(R.id.dateView4);
            TextView status = (TextView) convertView.findViewById(R.id.statusView);
            ImageView imgstatus = (ImageView) convertView.findViewById(R.id.statusImg);

            if (Database.leaveApplicationDBArr.get(position).geteID().equals(Login.eid)) {
                if (Database.leaveApplicationDBArr.get(position).getApprovalStatus().equals("cancelled")||Database.leaveApplicationDBArr.get(position).getApprovalStatus().equals("approved")||Database.leaveApplicationDBArr.get(position).getApprovalStatus().equals("rejected")) {
                    noOfDaysView.setText("Number of\n days : " + Database.leaveApplicationDBArr.get(position).getNoOfDays());
                    typeOfLeaveView.setText(Database.leaveApplicationDBArr.get(position).getTypeOfLeave());
                    dateView1.setText(Database.leaveApplicationDBArr.get(position).getStartDate() + " - " + Database.leaveApplicationDBArr.get(position).getEndDate());
                    status.setText(Database.leaveApplicationDBArr.get(position).getApprovalStatus());
                    if (Database.leaveApplicationDBArr.get(position).getApprovalStatus().equals("cancelled")||Database.leaveApplicationDBArr.get(position).getApprovalStatus().equals("rejected")){
                        imgstatus.setImageResource(R.drawable.xbut);
                    } else if(Database.leaveApplicationDBArr.get(position).getApprovalStatus().equals("approved")){
                        imgstatus.setImageResource(R.drawable.checkbut);
                    }
                } else {
                    noOfDaysView.setVisibility(View.GONE);
                    typeOfLeaveView.setVisibility(View.GONE);
                    dateView1.setVisibility(View.GONE);
                    status.setVisibility(View.GONE);
                    imgstatus.setVisibility(View.GONE);

                }
            } else {
                noOfDaysView.setVisibility(View.GONE);
                typeOfLeaveView.setVisibility(View.GONE);
                dateView1.setVisibility(View.GONE);
                status.setVisibility(View.GONE);
                imgstatus.setVisibility(View.GONE);

            }
            return convertView;
        }
    }
}