package com.example.cimagepatliputra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_add_teacher extends AppCompatActivity {
    Button submit;
    EditText txt_teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        submit=findViewById(R.id.btn_submit_teacher);
        txt_teacher=findViewById(R.id.edt_teachername);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(activity_add_teacher.this, "success", Toast.LENGTH_SHORT).show();
                txt_teacher.setText("");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(activity_add_teacher.this, AdminDashboardActivity.class);
                        startActivity(i);
                        finish();
                    }
                },2000);
            }
        });

    }
}