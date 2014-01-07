package com.devilo.server.util;

/**
 * Created by Arpit Bhayani on 7/1/14.
 */

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import java.sql.*;
import java.util.ArrayList;

public class DbUtil {

    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public static ArrayList<ArrayList<Object>> executeSelect( String sqlQuery ) throws ClassNotFoundException, SQLException {

        ArrayList<ArrayList<Object>> rows = new ArrayList<ArrayList<Object>>();

        openConnection();

        statement = connect.createStatement();

        try {
            resultSet = statement.executeQuery(sqlQuery);
        }
        catch (MySQLSyntaxErrorException e) {
            return null;
        }

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        ArrayList<Object> row = new ArrayList<Object>();
        for ( int i = 1 ; i <= columnCount ; i++ ) {
            row.add( resultSetMetaData.getColumnName(i) );
        }
        rows.add(row);

        while ( resultSet.next() ) {

            row = new ArrayList<Object>();

            for ( int i = 1 ; i <= columnCount ; i++ ) {
                Object obj = resultSet.getObject(i);
                row.add(obj);
            }

            rows.add(row);
        }

        closeConnection();

        return rows;

    }

    private static void openConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=welcome");

    }

    private static void closeConnection() throws ClassNotFoundException, SQLException {

        resultSet.close();
        statement.close();
        connect.close();
    }


}