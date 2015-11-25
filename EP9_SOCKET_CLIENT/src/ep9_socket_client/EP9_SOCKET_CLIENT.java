/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dungnt
 */
public class EP9_SOCKET_CLIENT {

    public static void main(String[] args) {
        Socket socket = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        String host = "127.0.0.1";
        try {
            socket = new Socket(host, 1234);
            byte[] message = "DUNGNT TEST 1234 alsjkdlasjd ".getBytes("UTF-8");
            OutputStream socketOutputStream = socket.getOutputStream();
            socketOutputStream.write(message);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(EP9_SOCKET_CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
