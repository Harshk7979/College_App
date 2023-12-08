package com.example.cimagepatliputra.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cimagepatliputra.AdminDashboardActivity;
import com.example.cimagepatliputra.R;
import com.example.cimagepatliputra.adapter.CourseNameAdapter;
import com.example.cimagepatliputra.adapter.UniversityListAdapter;
import com.example.cimagepatliputra.models.CourseNameModel;
import com.example.cimagepatliputra.models.NewsEventModel;
import com.example.cimagepatliputra.models.UniversityModel;
import com.example.cimagepatliputra.utils.Config;
import com.example.cimagepatliputra.utils.MasterFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddStudentActivity extends AppCompatActivity {

    private EditText edt_username, edt_student_father_name;
    private Spinner spnr_university_name, spnr_course_name;
    private Button btn_submit;
    private String courseId = "", universityID = "";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        edt_username = findViewById(R.id.edt_username);
        edt_student_father_name = findViewById(R.id.edt_student_father_name);
        spnr_university_name = findViewById(R.id.spnr_university_name);
        spnr_course_name = findViewById(R.id.spnr_course_name);
        btn_submit = findViewById(R.id.btn_submit);

        mContext = this;

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        spnr_university_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_id = view.findViewById(R.id.tv_id);
                TextView tv_name = view.findViewById(R.id.tv_name);
                universityID = tv_id.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnr_course_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                String name = edt_username.getText().toString();
                String fathername = edt_student_father_name.getText().toString();

                if (name.isEmpty()) {
                    edt_username.setError("Write student name");
                } else if (fathername.isEmpty()) {
                    edt_student_father_name.setError("write father name");
                } else {
                    HashMap hashMap = new HashMap();
                    hashMap.put("stud_name", name);
                    hashMap.put("university_id", universityID);
                    hashMap.put("course_id", courseId);
                    hashMap.put("stud_father_name", fathername);

                    AddStudent(hashMap);

                }
            }
        });


        fetchUniversityList();

        fetchCourseList();
    }

    void fetchUniversityList() {
        String url = Config.baseUrl + "cimage_patli/university_list.php";

        MasterFunction.request_Get(mContext, url
                , false, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Toast.makeText(AddStudentActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        try {
                            List<UniversityModel> universityModelList = new ArrayList<>();
                            JSONObject object = new JSONObject(response);
                            JSONArray array = object.getJSONArray("success");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);
                                String university_id = jsonObject.getString("university_id");
                                String university_name = jsonObject.getString("university_name");
                                universityModelList.add(new UniversityModel(university_id, university_name));
                            }

                            // Toast.makeText(AddStudentActivity.this, "size : "+universityModelList.size(), Toast.LENGTH_SHORT).show();

                            UniversityListAdapter universityListAdapter =
                                    new UniversityListAdapter(AddStudentActivity.this, universityModelList);

                            spnr_university_name.setAdapter(universityListAdapter);
                        } catch (JSONException ex) {

                            Toast.makeText(AddStudentActivity.this, "" + ex.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, false);

    }

    void fetchCourseList() {
        String url = Config.baseUrl + "cimage_patli/course_list.php";

        MasterFunction.request_Get(mContext, url
                , false, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        MasterFunction.bindCourseList(response,mContext,spnr_course_name);


                    }
                }, false);

    }

    void AddStudent(HashMap hashMap) {
        String url = Config.baseUrl + "cimage_patli/add_student.php";

        MasterFunction.request(mContext, url
                , hashMap, true, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                      //  Toast.makeText(AddStudentActivity.this, "" + response, Toast.LENGTH_SHORT).show();

                        if (response.trim().equals("success")) {
                            Toast.makeText(AddStudentActivity.this, "Student added successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddStudentActivity.this, "Error occurred : " + response, Toast.LENGTH_SHORT).show();
                        }

                    }
                }, false);


    }


}