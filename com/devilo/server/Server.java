package com.devilo.server;

import com.devilo.server.GET.ActionDB;
import com.devilo.server.GET.ActionHELP;
import com.devilo.server.GET.ActionObject;
import com.devilo.server.util.HttpUtil;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 *  Created by Arpit Bhayani on 6/1/14.
 */

/**
 *  Server Class
 *  This class is responsible for all server activities.
 */
public class Server {

    int listenPort = 0;

    /**
     *  Class constructor specifying the port number to listen to.
     *  @param  tempListenPort port number to listen
     *  @return none
     */
    public Server(int tempListenPort) {
        this.listenPort = tempListenPort;
    }

    /**
     *  Starts the server
     *  server starts to listen to the port listenPort
     */
    public void start() {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(listenPort);
            System.out.println("Listening to port " + listenPort + " on localhost");
        } catch (IOException e) {
            System.out.println("[SERVER ERROR] " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Done!");

        while ( true ) {
            System.out.println("Waiting for REQUEST");

            try {

                /*
                 *  Waits till someone sends the REQUEST.
                 */
                Socket socket = serverSocket.accept();

                InetAddress clientAddress = socket.getInetAddress();
                //System.out.println(clientAddress.getHostName() + " connect to server");

                BufferedReader inputStream = new BufferedReader( new InputStreamReader( socket.getInputStream() ));

                DataOutputStream outputStream = new DataOutputStream( socket.getOutputStream() );

                //handleHTTPRequestResponse(inputStream, outputStream);

                RequestThread requestThread = new RequestThread(inputStream , outputStream);
                requestThread.start();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
