package com.example.cimagepatliputra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.cimagepatliputra.screens.AddAttendanceActivity;
import com.example.cimagepatliputra.screens.AddStudentActivity;
import com.example.cimagepatliputra.screens.AddSubject;
import com.example.cimagepatliputra.screens.AdminLoginActivity;
import com.example.cimagepatliputra.screens.AttendanceActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    private CardView btn_add_student,btn_add_attendance,btn_attendance_list,btn_subject,btn_add_annoucement,btn_add_teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        btn_add_student = findViewById(R.id.btn_add_student);
        btn_add_attendance = findViewById(R.id.btn_add_attendance);
        btn_attendance_list = findViewById(R.id.btn_attendance_list);
        btn_subject = findViewById(R.id.btn_subject);
        btn_add_annoucement = findViewById(R.id.btn_add_annoucement);
        btn_add_teacher = findViewById(R.id.btn_add_teacher);

        btn_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, AddSubject.class));
            }
        });

        btn_add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, AddStudentActivity.class));
            }
        });

        btn_add_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, AddAttendanceActivity.class));
            }
        });

        btn_attendance_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, AttendanceActivity.class));
            }
        });
        btn_add_annoucement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, activity_announcement.class));
            }
        });

        btn_add_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminDashboardActivity.this, activity_add_teacher.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.action_logout){
            SharedPreferences sp= getSharedPreferences("user_info", Context.MODE_PRIVATE);
            SharedPreferences.Editor spe =sp.edit();
            spe.putBoolean("islogin",false);
            spe.apply();
            Intent intent =new Intent(AdminDashboardActivity.this, AdminLoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();


        }


        return super.onOptionsItemSelected(item);
    }
}