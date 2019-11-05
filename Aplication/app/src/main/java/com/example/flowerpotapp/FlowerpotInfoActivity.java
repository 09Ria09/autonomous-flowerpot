package com.example.flowerpotapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FlowerpotInfoActivity extends AppCompatActivity {

    private Flowerpot flowerpot;
    private TextView waterProcentage, heat, humidity, light, CO2levels;

    FlowerpotInfoActivity(Flowerpot flowerpot){
        this.flowerpot = flowerpot;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowerpot_info);

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
