package com.example.flowerpotapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private GestureDetectorCompat mDetector;
    public Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database(this);

        //setting up the gesture detector
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        //setting up the recycle view
        /*
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(this);
        recyclerView.setAdapter(mAdapter);

         */
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        public boolean onDown(MotionEvent event){
            return true;
        }

        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
            float relX = event2.getX() - event1.getX();
            float relY = event2.getY() - event1.getY();
            if(relX > 0 && relX > Math.abs(relY)){
                Log.d("gestures", "go left");

            }
            if(relX < 0 && Math.abs(relX) > Math.abs(relY)){
                Log.d("gestures", "go right");

            }
            return true;
        }
    }
}
