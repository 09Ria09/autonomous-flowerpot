package com.example.flowerpotapp;


import java.util.ArrayList;

public class Database {

    private ArrayList<Device> list;

    public void fill(){
        //TODO scan the network and fill the database
    }

    Device getDevice(int position){
        return list.get(position);
    }

    int getItemCount(){
        return list.size();
    }
}
