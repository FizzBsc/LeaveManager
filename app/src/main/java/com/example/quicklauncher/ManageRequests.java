package com.example.quicklauncher;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Model.Database;

public class ManageRequests extends AppCompatActivity {

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

        Database db = new Database(ManageRequests.this);

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

            convertView = getLayoutInflater().inflate(R.layout.customrequestlayout, null);

            final TextView nameView = (TextView) convertView.findViewById(R.id.leaveTypeView1);
            final TextView noOfDaysView = (TextView) convertView.findViewById(R.id.noOfDaysView);
            final TextView typeOfLeaveView = (TextView) convertView.findViewById(R.id.typeOfLeaveView);
            final TextView dateView1 = (TextView) convertView.findViewById(R.id.dateView1);
            final ImageButton approveButton = (ImageButton) convertView.findViewById(R.id.approveBut);
            final ImageButton rejectButton = (ImageButton) convertView.findViewById(R.id.deniedBut);

            approveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Database.leaveApplicationDBArr.get(position).setApprovalStatus("approved");
                    db.updateLeaveApplication("approved",Database.leaveApplicationDBArr.get(position).getLeaveAppID());
                    updateDaysTaken(Database.leaveApplicationDBArr.get(position).geteID(),Database.leaveApplicationDBArr.get(position).getTypeOfLeave(),Database.leaveApplicationDBArr.get(position).getNoOfDays());

                    hide(nameView,noOfDaysView,typeOfLeaveView,dateView1,approveButton,rejectButton);

                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ManageRequests.this);
                    dlgAlert.setMessage("Leave has been approved");
                    dlgAlert.setTitle("Leave Manager");
                    dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            sendEmail(position);
                            //dismiss the dialog
                        }
                    });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
            });
            rejectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Database.leaveApplicationDBArr.get(position).setApprovalStatus("rejected");
                    db.updateLeaveApplication("rejected",Database.leaveApplicationDBArr.get(position).getLeaveAppID());
                    hide(nameView,noOfDaysView,typeOfLeaveView,dateView1,approveButton,rejectButton);

                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(ManageRequests.this);
                    dlgAlert.setMessage("Leave has been rejected");
                    dlgAlert.setTitle("Leave Manager");
                    dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
            });

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

                    if (Database.leaveApplicationDBArr.get(position).getApprovalStatus().equals("pending")){
                        nameView.setText(db.user.get(index).getGivenName() + " "+db.user.get(index).getLastName());
                        noOfDaysView.setText("Number of\n days : " + Database.leaveApplicationDBArr.get(position).getNoOfDays());
                        typeOfLeaveView.setText(Database.leaveApplicationDBArr.get(position).getTypeOfLeave());
                        dateView1.setText(Database.leaveApplicationDBArr.get(position).getStartDate() + " - " + Database.leaveApplicationDBArr.get(position).getEndDate());
                    } else {
                        hide(nameView,noOfDaysView,typeOfLeaveView,dateView1,approveButton,rejectButton);

                    }
                }else if (role.equals("manager")){
                    for (int i = 0; i < db.user.size(); i++) {
                        if (db.user.get(i).getEmployeeID().equals(db.leaveApplicationDBArr.get(position).geteID())) {
                            index = i;
                            break;
                        }
                    }
                    if (Database.user.get(index).getManagedBy().equals(Login.eid)){
                        if (Database.leaveApplicationDBArr.get(position).getApprovalStatus().equals("pending")) {
                            nameView.setText(db.user.get(index).getGivenName() + " "+db.user.get(index).getLastName());
                            noOfDaysView.setText("Number of\n days : " + Database.leaveApplicationDBArr.get(position).getNoOfDays());
                            typeOfLeaveView.setText(Database.leaveApplicationDBArr.get(position).getTypeOfLeave());
                            dateView1.setText(Database.leaveApplicationDBArr.get(position).getStartDate() + " - " + Database.leaveApplicationDBArr.get(position).getEndDate());
                        }else {
                            hide(nameView,noOfDaysView,typeOfLeaveView,dateView1,approveButton,rejectButton);
                        }
                    } else{
                        hide(nameView,noOfDaysView,typeOfLeaveView,dateView1,approveButton,rejectButton);
                    }
                }


            }else{
                hide(nameView,noOfDaysView,typeOfLeaveView,dateView1,approveButton,rejectButton);
            }



            return convertView;


        }
        public void hide(TextView nameView, TextView noOfDaysView,TextView typeOfLeaveView,TextView dateView1, ImageButton approveButton, ImageButton rejectButton ){
            nameView.setVisibility(View.GONE);
            noOfDaysView.setVisibility(View.GONE);
            typeOfLeaveView.setVisibility(View.GONE);
            dateView1.setVisibility(View.GONE);
            approveButton.setVisibility(View.GONE);
            rejectButton.setVisibility(View.GONE);
        }

        public void updateDaysTaken(String eid, String typeOfLeave,int noOfDays){
            Database db = new Database(ManageRequests.this);
            Log.d("size",Integer.toString(Database.employeeLeaveAvailArr.size()));
            for (int i = 0; i < Database.employeeLeaveAvailArr.size();i++){
                Log.d("check crash", Database.employeeLeaveAvailArr.get(i).geteID());
                if (Database.employeeLeaveAvailArr.get(i).geteID().equals(eid)){
                    if (Database.employeeLeaveAvailArr.get(i).getTypeOfLeave().equals(typeOfLeave)){
                        Database.employeeLeaveAvailArr.get(i).setDaysTaken(Database.employeeLeaveAvailArr.get(i).getDaysTaken() + noOfDays);
                        Database.employeeLeaveAvailArr.get(i).setDaysAvail(Database.employeeLeaveAvailArr.get(i).getDaysAvail()-noOfDays);
                        db.updateEmployeeLeaveAvailable(Database.employeeLeaveAvailArr.get(i).getDaysTaken(), Database.employeeLeaveAvailArr.get(i).getDaysAvail(),Database.employeeLeaveAvailArr.get(i).geteLAID());
                        Log.d("did it update",Database.employeeLeaveAvailArr.get(i).getDaysTaken() +" "+ Database.employeeLeaveAvailArr.get(i).getDaysAvail()+" "+Database.employeeLeaveAvailArr.get(i).geteLAID());
                        break;
                    }
                }
            }

        }

        public void sendEmail(int position){
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{ Database.user.get(position).getEmail()});
            email.putExtra(Intent.EXTRA_SUBJECT, "Leave Request");
            email.putExtra(Intent.EXTRA_TEXT, "Your request has been approved!");

            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "Choose an Email client :"));
        }
    }
}