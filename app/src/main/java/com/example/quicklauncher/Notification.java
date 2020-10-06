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

public class Notification extends AppCompatActivity {

    ListView notificationListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationListView = (ListView) findViewById(R.id.notificationListView);

        CustomAdapter customAdapter = new CustomAdapter();
        notificationListView.setAdapter(customAdapter);
    }
    class CustomAdapter extends BaseAdapter {

        Database db = new Database(Notification.this);

        @Override
        public int getCount() {
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
            String role = null;
            boolean manage = false;
            convertView = getLayoutInflater().inflate(R.layout.customnotification, null);
            TextView name = (TextView) convertView.findViewById(R.id.notifyPersonView);
            Button notificationButton = (Button) convertView.findViewById(R.id.notifyBut);

            for (int i = 0; i < Database.user.size(); i++){
                if (Database.user.get(i).getEmployeeID().equals(Login.eid)){
                    role = Database.user.get(i).getEmploymentType();
                }
            }
            for (int i = 0; i < Database.user.size(); i++){
                if (Database.user.get(i).getManagedBy().equals(Login.eid)){
                    manage = true;
                }
            }

            if (role.equals("manager")){
                if (manage == true) {
                    if (db.employeeLeaveAvailArr.get(position).getTypeOfLeave().equals("Annual Leave")){
                        if (db.employeeLeaveAvailArr.get(position).getDaysTaken()==0){
                            name.setText(db.employeeLeaveAvailArr.get(position).geteID() + " has not taken any Annual Leave");
                        }else {
                            name.setVisibility(View.GONE);
                            notificationButton.setVisibility(View.GONE);
                        }

                    }else {
                        name.setVisibility(View.GONE);
                        notificationButton.setVisibility(View.GONE);
                    }

                }
            }

            return convertView;
        }
    }
}