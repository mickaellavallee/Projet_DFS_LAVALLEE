package com.projetdfs.lavallee.mickapp;

import android.content.Intent;
import android.graphics.drawable.DrawableContainer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView img = (ImageView) findViewById(R.id.logo);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent monIntent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(monIntent);
            }
        });
    }
}
