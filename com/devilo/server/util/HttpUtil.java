package com.devilo.server.util;

import java.io.IOException;

/**
 *  Created by Arpit Bhayani on 7/1/14.
 */
public class HttpUtil {

    public static String getHttpResponseHeader(int responseCode, String fileType, String httpVersion) throws IOException {

        /**
         *  The initial response line, called the status line, has three parts separated by spaces:
         *      1. the HTTP version,
         *      2. a response status code that gives the result of the request,
         *      3. and an English reason phrase describing the status code.
         *
         *  e.g. HTTP/1.0 404 Not Found
         */

        String response = httpVersion;

        switch ( responseCode ) {
            case 200:
                response = response + " 200 OK\r\n";
                break;
            case 404:
                response = response + " 404 Not Found\r\n";
                break;
            default:
                break;
        }

        if ( fileType != null )
            response = response + "Content-Type: " + fileType + "\r\n";

        response = response + "\r\n";

        return response;
    }

}
