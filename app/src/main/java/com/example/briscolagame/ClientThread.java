package com.example.briscolagame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private String ipAddress;
    private int port;
    private String sendMsg;
    //private TextView resultView;

    public ClientThread(String sendMsg) {
        this.ipAddress = "192.168.100.6";
        this.port = 8000;
        this.sendMsg = sendMsg;
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket(ipAddress, port);

            PrintWriter printWriter = Helper.getWriter(clientSocket);
            printWriter.println(sendMsg+"\r\n");

            BufferedReader bufferedReader = Helper.getReader(clientSocket);
            final String result = bufferedReader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
