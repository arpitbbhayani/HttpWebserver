package com.devilo.server.GET;

import com.devilo.server.util.HttpUtil;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *  Created by Arpit Bhayani on 6/1/14.
 */

public class ActionDB implements ActionObject {

    @Override
    public void act(String requestURL, DataOutputStream outputStream, String httpVersion) throws IOException {
        String fileType = null;
        int responseCode = 200;

        String response = null;

        response = HttpUtil.getHttpResponseHeader(responseCode, fileType, httpVersion);
        response = response + "DB RESPONSE\n";

        outputStream.writeBytes(response);
    }
}
