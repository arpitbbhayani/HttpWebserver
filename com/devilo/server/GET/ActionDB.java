package com.devilo.server.GET;

import com.devilo.server.util.DbUtil;
import com.devilo.server.util.HttpUtil;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *  Created by Arpit Bhayani on 6/1/14.
 */

public class ActionDB implements ActionObject {

    @Override
    public void act(String requestURL, DataOutputStream outputStream, String httpVersion) throws IOException {

        String fileType = "text/html";
        int responseCode = 200;
        StringBuffer response = new StringBuffer();

        requestURL = URLDecoder.decode(requestURL, "UTF-8");

        String sqlQuery = HttpUtil.getSqlQueryFromURL(requestURL);

        if ( sqlQuery == null ) {
            response.append(HttpUtil.getHttpResponseHeader(404, "text/html", httpVersion));
            response.append("<!DOCTYPE html><html><head><title>Help Page</title></head><body style=\"margin: 30px; font-family:monospace; background-color: #eeeeee;\">");
            response.append("<h2>404 Not Found</h2>");
            outputStream.writeBytes(new String(response));
            return;
        }

        response.append(HttpUtil.getHttpResponseHeader(responseCode, fileType, httpVersion));

        if ( sqlQuery == null ) {
            outputStream.writeBytes("SQL Query improper");
            return;
        }

        try {

            response.append("<!DOCTYPE html><html><head><title>Help Page</title></head><body style=\"margin: 30px; font-family:monospace; background-color: #eeeeee;\">");
            response.append("<h2>SQL Query</h2>");
            response.append("<p>For help click <a href=\"/help\">here</a><br/></p>");
            response.append("<h3>Query : " + requestURL +"</h3>");
            response.append("<br/><table border=1 cellspacing=2 cellpadding=8 align=left>");

            ArrayList<ArrayList<Object>> result = DbUtil.executeSelect(sqlQuery);

            Iterator<ArrayList<Object>> itr = result.iterator();

            while ( itr.hasNext() ) {
                response.append("<tr>");
                ArrayList<Object> list = itr.next();

                Iterator<Object> listIterator = list.iterator();

                while ( listIterator.hasNext() ) {
                    Object obj = listIterator.next();
                    response.append("<td>"+obj.toString()+"</td>");
                }

                response.append("</tr>");
            }

            response.append("</table>");
            response.append("</body></html>");

        } catch (ClassNotFoundException e) {

            response.append("Class Exception : " + e.getMessage());

        } catch (SQLException e) {

            response.append("SQL Exception : " + e.getMessage());
        }

        outputStream.writeBytes(new String(response));
    }
}
