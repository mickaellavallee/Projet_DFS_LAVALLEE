package com.projetdfs.lavallee.mickapp;

import android.app.ListActivity;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends ListActivity {

    List<Second> mesSeconds = new ArrayList<Second>();
    Double distance;
    String media;

    String TAG = "Parametres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String jsonReceived = getIntent().getStringExtra("data");
        mesSeconds.clear();
        try{
            JSONObject jsonObjectReceived = new JSONObject(jsonReceived);
            Log.i(TAG,"Le JSON: "+jsonObjectReceived);
            for (int i=0;i<jsonObjectReceived.getJSONArray("data").length();i++){
                JSONArray data = jsonObjectReceived.getJSONArray("data");
                String type = data.getJSONObject(i).getString("type");
                String title = data.getJSONObject(i).getString("display");
                //Log.i("Type_data", type); test personnel
                String choice1 = "POI";
                String choice2 = "HOTEL";
                String choice3 = "RESTAURANT";
                String choice4 = "CITY";
                if (type.equals(choice1) ||
                        type.equals(choice2) ||
                        type.equals(choice3) ||
                        type.equals(choice4)) {
                    if (data.getJSONObject(i).has("distance")) {
                        distance = data.getJSONObject(i).getDouble("distance");
                        if (data.getJSONObject(i).has("media")) {

                            media = data.getJSONObject(i).getString("media");

                            mesSeconds.add(new Second(media, type, title, distance));
                            Log.i(TAG, media + " . " + type + " . " + title + " . " + distance.toString());
                        } else {
                            if (data.getJSONObject(i).has("media")) {
                                media = data.getJSONObject(i).getString("media");
                            } else {
                                media = "https://www.cocoland.info/coco_files/coco-marche-pas.png";
                            }
                            mesSeconds.add(new Second(media, type, title, 0.0));
                            Log.i(TAG, media + " . " + type + " . " + title + " . no distance available");
                        }
                    }
                }
            }
            Log.i(TAG,mesSeconds.toString());
        }
        catch (Exception e){
            Log.e(TAG,e.toString());
        }
        ListView myListView = getListView();
        SecondActivityAdapter adapter = new SecondActivityAdapter(this, mesSeconds);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}