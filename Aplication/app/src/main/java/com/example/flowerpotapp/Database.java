package com.example.flowerpotapp;


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
        Thread thread = new Thread(new NetworkScanner(context));
        thread.start();
    }

    Flowerpot getFlowerpot(int position){
        return list.get(position);
    }

    int getItemCount(){
        return list.size();
    }

    private void startPingService(Context context)
    {
        InetAddress in;

        try {

            Log.d(debugTag, "got here");
            WifiManager mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            String subnet = getSubnetAddress(mWifiManager.getDhcpInfo().gateway);
            Log.d(debugTag, subnet);

            for (int i=1;i<255;i++){

                String host = subnet + "." + i;
                Log.d(debugTag, host);



                if (InetAddress.getByName(host).isReachable(timeout)){
                    Log.d(debugTag, "yes");
                    String hostName = InetAddress.getByName(host).getHostName();
                    Log.d(debugTag, hostName);
                    if(isFlowerPot(hostName)) {
                        list.add(new Flowerpot(host, hostName));
                        //Log.d(debugTag, hostName);
                    }
                }
                else {
                    //Log.d("network", "Not Reachable Host: " + host);
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

    private boolean isFlowerPot(String name){
        return false;
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
