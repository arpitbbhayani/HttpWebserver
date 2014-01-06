package com.devilo.server.GET;

import com.devilo.server.util.HttpUtil;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *  Created by Arpit Bhayani on 6/1/14.
 */
public class ActionHELP implements ActionObject {

    @Override
    public void act(String requestURL, DataOutputStream outputStream, String httpVersion) throws IOException {

        String fileType = "text/html";
        int responseCode = 200;

        String response;

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        response = HttpUtil.getHttpResponseHeader(responseCode, fileType, httpVersion);
        response = response + "<html>\n";

        response = response + "<!DOCTYPE html><html><head><title>Help Page</title></head><body style=\"font-family: sans-serif\">";
        response = response + "<h2>Help Page for xvzf server</h2><h3>Following are the services exposed :</h3><ul>";
        response = response + "<li>To get the data from the Database</li><li>To view static files</li><li>Help</li></ul>";
        response = response + "</body></html>";

        outputStream.writeBytes(response);
    }
}
