package com.example.flowerpotapp;


public class Database {

    private Device list[];

    public void fill(){
        //TODO scan the network and fill the database
    }

    Device getDevice(int position){
        return list[position];
    }

    int getItemCount(){
        return list.size();
    }
}
