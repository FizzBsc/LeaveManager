package com.example.quicklauncher;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;

public class ViewMyRequests extends AppCompatActivity {


    ListView pendingLeaveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requests);
        pendingLeaveView = (ListView) findViewById(R.id.reqPendListView);

        CustomAdapter customAdapter = new CustomAdapter();
        pendingLeaveView.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter {

        Database db = new Database(ViewMyRequests.this);

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
            convertView = getLayoutInflater().inflate(R.layout.custompendinglayout, null);

            final TextView noOfDaysView = (TextView) convertView.findViewById(R.id.noOfDaysView3);
            final TextView typeOfLeaveView = (TextView) convertView.findViewById(R.id.typeOfLeaveView2);
            final TextView dateView1 = (TextView) convertView.findViewById(R.id.dateView3);
            Button cancelBut = (Button) convertView.findViewById(R.id.cancelLeaveBut);

            cancelBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Database.leaveApplicationDBArr.get(position).setApprovalStatus("cancelled");
                    db.updateLeaveApplication(Database.leaveApplicationDBArr.get(position).getApprovalStatus(),Database.leaveApplicationDBArr.get(position).geteID());
                    notifyDataSetChanged();
                }
            });

            if(Database.leaveApplicationDBArr.get(position).geteID().equals(Login.eid)) {
                if (Database.leaveApplicationDBArr.get(position).getApprovalStatus().equals("pending")) {
                    noOfDaysView.setText("Number of\n days : " + Database.leaveApplicationDBArr.get(position).getNoOfDays());
                    typeOfLeaveView.setText(Database.leaveApplicationDBArr.get(position).getTypeOfLeave());
                    dateView1.setText(Database.leaveApplicationDBArr.get(position).getStartDate() + " - " + Database.leaveApplicationDBArr.get(position).getEndDate());
                }else {
                    noOfDaysView.setVisibility(View.GONE);
                    typeOfLeaveView.setVisibility(View.GONE);
                    dateView1.setVisibility(View.GONE);
                    cancelBut.setVisibility(View.GONE);
                }
            }else{
                noOfDaysView.setVisibility(View.GONE);
                typeOfLeaveView.setVisibility(View.GONE);
                dateView1.setVisibility(View.GONE);
                cancelBut.setVisibility(View.GONE);
            }
            return convertView;
        }
    }
}