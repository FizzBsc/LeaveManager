package com.example.quicklauncher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;

public class StaffList extends AppCompatActivity {

    ListView userListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);

        userListView = (ListView) findViewById(R.id.employeeListView);

        CustomAdapter customAdapter = new CustomAdapter();
        userListView.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            Database db = new Database(StaffList.this);

            return db.user.size();
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

            String role = null;
            String id = null;
            convertView = getLayoutInflater().inflate(R.layout.customuserlayout, null);

            RelativeLayout rl = (RelativeLayout) convertView.findViewById(R.id.relativeLayout2);
            TextView firstNameText = (TextView) convertView.findViewById(R.id.firstNameText);
            TextView lastNameText = (TextView) convertView.findViewById(R.id.lastNameText);
            TextView emailText = (TextView) convertView.findViewById(R.id.emailText);
            TextView empStatus = (TextView) convertView.findViewById(R.id.notifyPersonView);
            TextView empLevelText = (TextView) convertView.findViewById(R.id.empLevelText);
            Button editBut = (Button) convertView.findViewById(R.id.notifyBut);

            id = Database.user.get(position).getEmployeeID();

            final String finalId = id;
            editBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent startIntent = new Intent(getApplicationContext(),EditEmployee.class);
                    startIntent.putExtra("ID", finalId);
                    startActivity(startIntent);
                }
            });
            for (int i = 0; i < Database.user.size(); i++){
                if (Database.user.get(i).getEmployeeID().equals(Login.eid)){
                    role = Database.user.get(i).getEmploymentType();
                }
            }

            if (role.equals("manager") || role.equals("admin")) {//!!!!!!!!!!!!!!!! set manager and admin here
                if (role.equals("admin")) {
                    rl.setVisibility(RelativeLayout.VISIBLE);
                    firstNameText.setText(Database.user.get(position).getGivenName());
                    lastNameText.setText(Database.user.get(position).getLastName());
                    emailText.setText(Database.user.get(position).getEmail());
                    empStatus.setText("Current employee: " + Database.user.get(position).isStatus());
                    empLevelText.setText(Database.user.get(position).getEmploymentType());
                }else if (role.equals("manager")){
                    if (Database.user.get(position).getManagedBy().equals(Login.eid)){
                        rl.setVisibility(RelativeLayout.VISIBLE);
                        firstNameText.setText(Database.user.get(position).getGivenName());
                        lastNameText.setText(Database.user.get(position).getLastName());
                        emailText.setText(Database.user.get(position).getEmail());
                        empStatus.setText("Current employee: " + Database.user.get(position).isStatus());
                        empLevelText.setText(Database.user.get(position).getEmploymentType());
                    } else{
                        rl.setVisibility(RelativeLayout.GONE);
                        firstNameText.setVisibility(View.GONE);
                        lastNameText.setVisibility(View.GONE);
                        emailText.setVisibility(View.GONE);
                        empLevelText.setVisibility(View.GONE);
                        empStatus.setVisibility(View.GONE);
                        editBut.setVisibility(View.GONE);
                    }
                }


            }else{
                    rl.setVisibility(RelativeLayout.GONE);
                    firstNameText.setVisibility(View.GONE);
                    lastNameText.setVisibility(View.GONE);
                    emailText.setVisibility(View.GONE);
                    empLevelText.setVisibility(View.GONE);
                    empStatus.setVisibility(View.GONE);
                    editBut.setVisibility(View.GONE);

            }
            return convertView;

        }

    }

}