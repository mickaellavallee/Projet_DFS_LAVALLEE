package com.projetdfs.lavallee.mickapp;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends ListActivity {

    List<Second> mesSeconds = new ArrayList<Second>();
    Double distance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String jsonReceived = getIntent().getStringExtra("data");
        mesSeconds.clear();
        try{
            JSONObject jsonObjectReceived = new JSONObject(jsonReceived);

            Log.i("Pass_parametre","Le JSON: "+jsonObjectReceived);

            for (int i=0;i<jsonObjectReceived.getJSONArray("data").length();i++){

                String type = jsonObjectReceived.getJSONArray("data").getJSONObject(i).getString("type");
                String title = jsonObjectReceived.getJSONArray("data").getJSONObject(i).getString("display");

                if (jsonObjectReceived.getJSONArray("data").getJSONObject(i).has("distance")){

                    Double distance = jsonObjectReceived.getJSONArray("data").getJSONObject(i).getDouble("distance");
                    mesSeconds.add(new Second(type,title,distance));

                    Log.i("Pass_parametre",type+" . "+title+" . "+distance.toString());

                }
                else {

                    mesSeconds.add(new Second(type,title,0.0));
                    Log.i("Pass_parametre",type+" . "+title+" . no distance available");
                }
            }

            Log.i("Pass_parametre",mesSeconds.toString());
        }
        catch (Exception e){

            Log.e("Pass_parametre",e.toString());
        }

        ListView myListView = getListView();
        SecondActivityAdapter adapter = new SecondActivityAdapter(this, mesSeconds);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}