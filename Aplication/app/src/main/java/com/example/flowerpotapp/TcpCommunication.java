package com.example.flowerpotapp;

import android.util.JsonReader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpCommunication {
    private Socket sock;
    private PrintWriter out;
    private BufferedReader in;
    private String ip;
    private int port;
    private final String debugTag = "tcpCommunication";

    TcpCommunication(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public boolean authenticate(){
        connect();
        JSONObject dataJson = new JSONObject();
        try{
            dataJson.put("request", "authenticate");
        }
        catch (JSONException e){
            Log.d(debugTag, e.toString());
        }
        out.println(dataJson);
        String jsonAnswer = "";
        try{
            jsonAnswer = in.readLine();
        }
        catch (IOException e){
            Log.d(debugTag, e.toString());
        }
        disconnect();
        return jsonParseOneBool(jsonAnswer);
    }

    public void getInformation(Flowerpot flowerpot){
        connect();
        JSONObject dataJson = new JSONObject();
        try{
            dataJson.put("request", "information");
        }
        catch (JSONException e){
            Log.d(debugTag, e.toString());
        }
        out.println(dataJson);
        String jsonAnswer = "";
        try{
            jsonAnswer = in.readLine();
        }
        catch (IOException e){
            Log.d(debugTag, e.toString());
        }
        jsonParseInformation(jsonAnswer, flowerpot);
        disconnect();
    }

    private boolean jsonParseOneBool(String json){
        JSONObject object;
        boolean answer = false;
        try{
            object = new JSONObject(json);
            answer = object.getBoolean("response");
        }
        catch (JSONException e){
            Log.d(debugTag, e.toString());
        }
        return answer;
    }

    private void jsonParseInformation(String json, Flowerpot flowerpot){
        JSONObject object;
        try{
            object = new JSONObject(json);
            flowerpot.waterProcentage = object.getInt("water");
            flowerpot.humidity = object.getInt("humidity");
            flowerpot.light = object.getInt("light");
            flowerpot.CO2level = object.getInt("CO2");
        }
        catch (JSONException e){
            Log.d(debugTag, e.toString());
        }
    }

    private void connect(){
        Log.d("communication", "connecting to " + ip + ' ' + port);
        try {
            this.sock = new Socket(ip, port);
            this.out = new PrintWriter(sock.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        }
        catch (IOException e) {
            Log.d(debugTag, e.toString());
        }
    }

    private void disconnect(){
        try {
            this.in.close();
            this.out.close();
            this.sock.close();
        }
        catch (IOException e){
            Log.d(debugTag, e.toString());
        }
    }
}
