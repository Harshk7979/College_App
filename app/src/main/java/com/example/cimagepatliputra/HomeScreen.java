package com.example.cimagepatliputra;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.cimagepatliputra.adapter.AmenitiesListAdapter;
import com.example.cimagepatliputra.adapter.EducationListAdapter;
import com.example.cimagepatliputra.adapter.SliderAdapterExample;
import com.example.cimagepatliputra.models.EducationModel;
import com.example.cimagepatliputra.models.SliderItem;
import com.example.cimagepatliputra.screens.AdminLoginActivity;
import com.example.cimagepatliputra.screens.NewsEventActivity;
import com.example.cimagepatliputra.utils.Helper;
import com.example.cimagepatliputra.utils.HorizontalListView;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.cimagepatliputra.databinding.ActivityHomeScreenBinding;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    private AppBarConfiguration mAppBarConfiguration;
//    private ActivityHomeScreenBinding binding;

    //slider
    SliderView sliderView;
    private SliderAdapterExample adapter;
    private HorizontalListView lst_education;
    private EducationListAdapter educationListAdapter;
    private ListView lst_amenities;
    private EditText edt_fullname, edt_mobile_number, edt_email_id;
    private Spinner spnr_course_name;
    private Button btn_send_enq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationMenuView navMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navMenuView.addItemDecoration(new DividerItemDecoration(HomeScreen.this, DividerItemDecoration.VERTICAL));

        View header = navigationView.getHeaderView(0);

        edt_fullname = findViewById(R.id.edt_fullname);
        edt_mobile_number = findViewById(R.id.edt_mobile_number);
        edt_email_id = findViewById(R.id.edt_email_id);
        spnr_course_name = findViewById(R.id.spnr_course_name);
        btn_send_enq = findViewById(R.id.btn_send_enq);


        lst_education = findViewById(R.id.lst_education);

        educationListAdapter = new EducationListAdapter(HomeScreen.this, Helper.getEducationList());
        lst_education.setAdapter(educationListAdapter);

        lst_amenities = findViewById(R.id.lst_amenities);
        AmenitiesListAdapter amenitiesListAdapter = new AmenitiesListAdapter(HomeScreen.this, Helper.getAmenities());
        lst_amenities.setAdapter(amenitiesListAdapter);



        btn_send_enq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full_name = edt_fullname.getText().toString();
                String mobile_number = edt_mobile_number.getText().toString();
                String email_id = edt_email_id.getText().toString();

                if (full_name.isEmpty()) {
                    Toast.makeText(HomeScreen.this, "Write your full name", Toast.LENGTH_SHORT).show();
                } else if (mobile_number.isEmpty()) {
                    Toast.makeText(HomeScreen.this, "write your mobile number", Toast.LENGTH_SHORT).show();
                } else if (email_id.isEmpty()) {
                    Toast.makeText(HomeScreen.this, "write your email id", Toast.LENGTH_SHORT).show();
                } else {

                    RequestQueue mRequestQueue = null;

                    ProgressDialog pDialog = new ProgressDialog(HomeScreen.this);
                    pDialog.setMessage("Loading...PLease wait");
                    pDialog.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://thedeveloperpoint.com/cimage_patli/insert_enquiry.php",
                            new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            pDialog.dismiss();
                            Toast.makeText(HomeScreen.this, "Response : "+response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pDialog.dismiss();
                            Toast.makeText(HomeScreen.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map params = new HashMap();
                            params.put("full_name", full_name);
                            params.put("mobile_number", mobile_number);
                            params.put("email_id", email_id);
                            params.put("course", spnr_course_name.getSelectedItem().toString());
                            return params;
                        }
                    };
                    mRequestQueue = Volley.newRequestQueue(HomeScreen.this);
                    mRequestQueue.add(stringRequest);

                }
            }
        });


    }

    void setImageUrl(ImageView img, String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .fitCenter()
                .into(img);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news_event) {
            startActivity(new Intent(HomeScreen.this,NewsEventActivity.class));
        } else if (id==R.id.nav_gallery) {
            startActivity(new Intent(HomeScreen.this,gallery.class));

        } else if(id==R.id.nav_login){
           // startActivity(new Intent(HomeScreen.this, AdminLoginActivity.class));
            SharedPreferences sp= getSharedPreferences("user_info", Context.MODE_PRIVATE);
            boolean islogin =sp.getBoolean("islogin",false);
            if(islogin){
                startActivity(new Intent(HomeScreen.this,AdminDashboardActivity.class));
                finish();

            }
            else{
                startActivity(new Intent(HomeScreen.this,AdminDashboardActivity.class));
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}