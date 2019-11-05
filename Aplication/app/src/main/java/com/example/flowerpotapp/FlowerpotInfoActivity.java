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

        //get the flowerpot we are representing
        Intent intent = getIntent();
        position = intent.getIntExtra("flowerpot", 0);


        //initiate views
        waterProcentage = findViewById(R.id.water);
        heat = findViewById(R.id.heat);
        humidity = findViewById(R.id.humidity);
        light = findViewById(R.id.light);
        CO2levels = findViewById(R.id.CO2levels);

        //set text
        waterProcentage.setText(flowerpot.waterProcentage);
        heat.setText(flowerpot.heat);
        humidity.setText(flowerpot.humidity);
        light.setText(flowerpot.light);
        CO2levels.setText(flowerpot.CO2level);
    }
}
