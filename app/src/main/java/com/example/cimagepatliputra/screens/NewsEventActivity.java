package com.example.cimagepatliputra.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cimagepatliputra.MainActivity;
import com.example.cimagepatliputra.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cimagepatliputra.adapter.NewsEventAdapter;
import com.example.cimagepatliputra.models.NewsEventModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NewsEventActivity extends AppCompatActivity {


    private static final String TAG = NewsEventActivity.class.getName();

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://192.168.17.23/cimage/news_event.php";
    private List<NewsEventModel> eventList;
    private ListView list_news_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_event);

        list_news_event  =findViewById(R.id.list_news_event);

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), "Response :" + response.toString(), Toast.LENGTH_LONG).show();

                try {
                    JSONArray array = new JSONArray(response);
                    eventList = new ArrayList<>();

                    for (int i=0;i<array.length();i++){

                        JSONObject jsonObject = array.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String desc=jsonObject.getString("description");
                        String image_url = jsonObject.getString("image_url");
                        eventList.add(new NewsEventModel(title,desc));
                    }

                    NewsEventAdapter newsEventAdapter =new NewsEventAdapter(NewsEventActivity.this,eventList);
                    list_news_event.setAdapter(newsEventAdapter);

                }
                catch (JSONException ex){

                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG, "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}