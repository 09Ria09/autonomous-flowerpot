package com.example.flowerpotapp;


import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.FileLock;
import java.util.ArrayList;

public class Database {

    private Context context;
    private ArrayList<Flowerpot> list;
    private final int timeout=100;
    private final String debugTag = "debugNetwork";

    public Database(Context context){
        //TODO scan the network and fill the database
        this.context = context;
        list = new ArrayList<Flowerpot>();
        Thread thread = new Thread(new NetworkScanner(context));
        thread.start();
    }

    Flowerpot getFlowerpot(int position){
        return list.get(position);
    }

    int getItemCount(){
        return list.size();
    }

    //TODO multithreading on 4 threads (maybe 8 but test it out)
    private void startPingService(Context context)
    {
        InetAddress in;

        try {

            Log.d(debugTag, "got here");
            WifiManager mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            String subnet = getSubnetAddress(mWifiManager.getDhcpInfo().gateway);
            Log.d(debugTag, subnet);

            for (int i=0;i<255;i++) {

                String host = subnet + "." + i;
                Log.d(debugTag, host);


                if (InetAddress.getByName(host).isReachable(timeout)) {
                    Log.d(debugTag, "yes");
                    if (isFlowerPot(host)) {
                        list.add(new Flowerpot(host));
                    }
                } else {
                    Log.d(debugTag, "no");
                }
            }



        }
        catch(Exception e){
            Log.d(debugTag, e.toString());
        }
    }

    private String getSubnetAddress(int address)
    {
        String ipString = String.format(
                "%d.%d.%d",
                (address & 0xff),
                (address >> 8 & 0xff),
                (address >> 16 & 0xff));

        return ipString;
    }

    private boolean isFlowerPot(String ip){
        TcpCommunication tcpCommunication = new TcpCommunication(ip, 1234);
        return tcpCommunication.authenticate();
    }

    private class NetworkScanner implements Runnable{

        private Context context;

        public NetworkScanner(Context context){
            this.context = context;
        }

        public void run(){
            startPingService(context);
        }
    }
}
