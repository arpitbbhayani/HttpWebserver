package com.devilo.server.GET;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *  Created by Arpit Bhayani on 6/1/14.
 */
public interface ActionObject {

    public void act(String requestURL, DataOutputStream outputStream, String httpVersion) throws IOException;

}
