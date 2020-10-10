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

public class ViewLeaveTypes extends AppCompatActivity {

    ListView leaveListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_leave_types);

        leaveListView = (ListView) findViewById(R.id.leaveListView);

        CustomAdapter customAdapter = new CustomAdapter();
        leaveListView.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            Database db = new Database(ViewLeaveTypes.this);


            return db.leaveTypeDBArr.size();
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

            convertView = getLayoutInflater().inflate(R.layout.customleavelayout, null);

            RelativeLayout rl = (RelativeLayout) convertView.findViewById(R.id.relativeLa);
            TextView lTIDText = (TextView) convertView.findViewById(R.id.leaveTypeView1);
            TextView leaveNameText = (TextView) convertView.findViewById(R.id.daysLeftView1);
            TextView daysAvailText = (TextView) convertView.findViewById(R.id.typeOfLeaveView);
            Button editBut = (Button) convertView.findViewById(R.id.deleteButton);

            editBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent startIntent = new Intent(getApplicationContext(),EditLeave.class);
                    startIntent.putExtra("ID", position);
                    startActivity(startIntent);
                }
            });


            rl.setVisibility(RelativeLayout.VISIBLE);
            lTIDText.setText("ID: " + Database.leaveTypeDBArr.get(position).getlTID());
            leaveNameText.setText("Leave Name: "+Database.leaveTypeDBArr.get(position).getLeaveName());
            daysAvailText.setText("Days Available:" + Database.leaveTypeDBArr.get(position).getDaysAvail());



            return convertView;
        }
    }
}

