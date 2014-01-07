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

        StringBuffer response = new StringBuffer();

        response.append(HttpUtil.getHttpResponseHeader(responseCode, fileType, httpVersion));

        response.append("<!DOCTYPE html><html><head><title>Help Page</title></head><body style=\"margin: 30px; font-family:monospace; background-color: #eeeeee;\">");
        response.append("<h2>Help Page</h2><ul><li><p>For db query, you can hit following types of URLs :<ul>");
        response.append("<li><p><i>http://localhost:8011/db/databaseName/tableName?where=whereClause</i><br/><br/>The where clause can be a complete where clause that we specify in SQL with \"and\" and \"or\" , with spaces and all. The browser converts special characters into equivalent symbols; for example. space is converted into %20 , the Webserver written handles this as well.<br/><br/>Examples,<br/>http://localhost:8011/db/data/data_table<br/>http://localhost:8011/db/data/data_table?where=id=1 or name=\"Neel Jinwala\"</p></li>");
        response.append("<li><p><i>http://localhost:8011/db/databaseName/tableName?param=value & param=value</i><br/><br/>The \"param\" that we need to specify here is the column name and \"value\" is the value of it. So instead of writing complete where clause here, the constraints can be directly given. Here any relational operator can be used.<br/><br/>Example,<br/>http://localhost:8011/db/data/data_table?id>2&id<5 </p></li></ul>");
        response.append("<b><br/>db : is the keyword,");
        response.append("<br/>data : Name of the Database,");
        response.append("<br/>data_table : Name of the Table</b>");
        response.append("</p></li>");
        response.append("<li><p>For viewing or accessing any files use following URL syntax :");
        response.append("<br/><p><i>http://localhost:8011/static/filePath</i><br/>");
        response.append("<br/><b>static : is the keyword,<br/>filePath : path of the file starting relative to the ROOT directory of the application server.</b>");
        response.append("<br/><br/>Examples,<br/>http://localhost:8011/static/file.pdf<br/>http://localhost:8011/static/test.html</p></p></li><li><p>For help click <a href=\"/help\">here</a><br/></p></li></ul></body></html>");

        outputStream.writeBytes(new String(response));
    }
}
