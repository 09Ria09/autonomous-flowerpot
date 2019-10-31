package com.example.flowerpotapp;


import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.net.InetAddress;
import java.util.ArrayList;

public class Database {

    private Context context;
    private ArrayList<Device> list;
    private final int timeout=100;

    public Database(Context context){
        //TODO scan the network and fill the database
        this.context = context;
        startPingService(context);
    }

    Device getDevice(int position){
        return list.get(position);
    }

    int getItemCount(){
        return list.size();
    }

    private void startPingService(Context context)
    {
        try {

            Log.d("network", "got here");
            WifiManager mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            String subnet = getSubnetAddress(mWifiManager.getDhcpInfo().gateway);
            Log.d("network", subnet);

            for (int i=1;i<255;i++){

                String host = subnet + "." + i;
                Log.d("network", host);
                Log.d("network", Integer.toString(i));

                if (InetAddress.getByName(host).isReachable(timeout)){
                    Log.d("network", "yes");
                    Log.d("network", "Reachable Host: " + host);
                }
                else
                {
                    Log.d("network", "Not Reachable Host: " + host);
                    Log.d("network", "no");
                }
            }


        }
        catch(Exception e){
            //System.out.println(e);
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
}
