/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dungnt
 */
public class EP9_OBJECT implements Runnable {

    public Socket socket = null;
    public ObjectInputStream streamIn = null;
    public ObjectOutputStream streamout = null;

    EP9_OBJECT(Socket soc) throws IOException {
        System.out.println("INIT Thread");
        socket = soc;
        streamIn = new ObjectInputStream(socket.getInputStream());
        streamout = new ObjectOutputStream(this.socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            System.out.println("START Thread");
            streamout.writeObject("test".getBytes());
            streamout.flush();
            while (true) {
                try {
                    if (streamIn == null) {
                        System.out.println("NULL ME NO ROI");
                    }
                    
                    EP9_MESSAGE msg = null;
                    if ((msg = (EP9_MESSAGE) streamIn.readObject()) != null) {
                        if (msg != null) {
                            handle(msg);
                        }
                    }
                    
                } catch (IOException ioe) {
                    System.out.println(" ERROR reading: " + ioe);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EP9_OBJECT.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(EP9_OBJECT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handle(EP9_MESSAGE msg) {
        System.out.println(msg.content);
        msg.content = "SERVER SEND";
        sendMessage(msg);
    }

    public void sendMessage(EP9_MESSAGE msg) {
        try {
            streamout.writeObject(msg);
            streamout.flush();
        } catch (IOException ex) {
            Logger.getLogger(EP9_OBJECT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
