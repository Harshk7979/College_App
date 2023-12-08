package com.example.cimagepatliputra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_announcement extends AppCompatActivity {
    Button submit;
    EditText txt_notice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        submit=findViewById(R.id.btn_submit_notice);
        txt_notice=findViewById(R.id.edt_notification);
//        activity_announcement = findViewById(R.id.btn_add_annoucement);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_announcement.this, "success", Toast.LENGTH_SHORT).show();
                txt_notice.setText("");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(activity_announcement.this, AdminDashboardActivity.class);
                        startActivity(i);
                        finish();
                    }
                },2000);

            }
        });
    }
}