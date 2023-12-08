package com.example.cimagepatliputra.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.cimagepatliputra.R;
import com.example.cimagepatliputra.utils.Config;
import com.example.cimagepatliputra.utils.MasterFunction;

import java.util.HashMap;

public class AddSubject extends AppCompatActivity {

    private Spinner spnr_course;
    private EditText edt_subject;
    private Button btn_submit;
    private Context mContext;
    private String courseId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        mContext =this;

        spnr_course = findViewById(R.id.spnr_course_name);
        edt_subject = findViewById(R.id.edt_subject);
        btn_submit = findViewById(R.id.btn_submit);

        spnr_course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_id = view.findViewById(R.id.tv_id);
                TextView tv_name = view.findViewById(R.id.tv_name);
                courseId = tv_id.getText().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String subjectName = edt_subject.getText().toString();

                if(subjectName.isEmpty()){
                    edt_subject.setError("Write subject name");
                }
                else{
                    HashMap hashMap = new HashMap();
                    hashMap.put("courseId",courseId);
                    hashMap.put("subjectName",subjectName);

                   MasterFunction.request(mContext, Config.AddSubject, hashMap, true, new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {
                           Toast.makeText(mContext, ""+response, Toast.LENGTH_SHORT).show();
                       }
                   },false);
                }
            }
        });

        MasterFunction.fetchCourseList(mContext,spnr_course);
    }
}