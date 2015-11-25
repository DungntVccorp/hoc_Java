/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket_client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dungnt
 */
public class EP9_OBJECT implements Runnable {

    public static Socket socket = null;
    public static ObjectOutputStream Out = null;

    EP9_OBJECT() {
        String host = "127.0.0.1";
        try {
            socket = new Socket(host, 1234);
            Out = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException ex) {
            Logger.getLogger(EP9_SOCKET_CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {            
            
        }
    }

    public void send() {
        try {
            Out.writeObject(new EP9_MESSAGE("FAP", "FAP", "FAP", "FAP"));
            Out.flush();
        } catch (IOException ex) {
            Logger.getLogger(EP9_OBJECT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
