package com.example.flowerpotapp;

public class Flowerpot {

    private String ip;
    public int waterProcentage, humidity, light, CO2level, heat;

    public Flowerpot(String ip){
        this.ip = ip;
        fillInformation();
    }

    public void fillInformation(){
        TcpCommunication tcpCommunication = new TcpCommunication(ip, 1234);
        tcpCommunication.getInformation(this);
    }

    public String getIp() {
        return ip;
    }
}
