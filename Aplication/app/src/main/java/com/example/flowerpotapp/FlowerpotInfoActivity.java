package com.example.flowerpotapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FlowerpotInfoActivity extends AppCompatActivity {

    private Flowerpot flowerpot;
    private int position;
    private TextView waterProcentage, heat, humidity, light, CO2levels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowerpot_info);

        //get intent
        Intent intent = getIntent();

        //initiate views
        waterProcentage = findViewById(R.id.water);
        heat = findViewById(R.id.heat);
        humidity = findViewById(R.id.humidity);
        light = findViewById(R.id.light);
        CO2levels = findViewById(R.id.CO2levels);

        //set text
        waterProcentage.setText(intent.getIntExtra("waterProcentage", 0));
        heat.setText(intent.getIntExtra("heat", 0));
        humidity.setText(intent.getIntExtra("humidity", 0));
        light.setText(intent.getIntExtra("light", 0));
        CO2levels.setText(intent.getIntExtra("CO2levels", 0));
    }
}
