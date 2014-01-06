import com.devilo.server.Server;

import javax.swing.*;

/**
 * Created by Arpit Bhayani on 6/1/14.
 */

public class Main {

    public static void main(String[] args) {

        /*
         *  Setting up the listen PORT
         */

        int listenPort = 8011;

        Server server = new Server( listenPort );
        server.start();

    }
}
