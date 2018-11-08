package com.projetdfs.lavallee.mickapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    private String TAG = "GETJSON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView mImageView;
        final TextView mTxtDisplay = (TextView) findViewById(R.id.text_main);
        String url = "http://voyage2.corellis.eu/api/v2/homev2?lat=43&lon=6";

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object o) {
                        // Display the first 500 characters of the response string.
                        String response = (String) o;
                        Log.i(TAG, response.toString());
                        //mTxtDisplay.setText("Response is: " + response.substring(0, 500));
                        Intent monIntent = new Intent(MainActivity.this, SecondActivity.class);
                        monIntent.putExtra("JSON",response);
                        startActivity(monIntent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTxtDisplay.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        }

    }
