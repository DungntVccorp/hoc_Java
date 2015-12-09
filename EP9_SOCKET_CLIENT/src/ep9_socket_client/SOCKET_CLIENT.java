/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket_client;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SOCKET_CLIENT implements Runnable {

    private String host = null;
    private int port = 9999;
    private Socket sClient = null;
    private DataOutputStream dOut = null;
    private DataInputStream dIn = null;
    private Boolean isDisconect = true;
    private Thread t;

    SOCKET_CLIENT(String host, int portInt) {
        this.host = host;
        this.port = portInt;
    }

    public void startClient() {
        try {
            this.sClient = new Socket(this.host, this.port);
            this.dIn = new DataInputStream(this.sClient.getInputStream());
            this.dOut = new DataOutputStream(this.sClient.getOutputStream());
            if(t == null){
                t = new Thread(this, this.sClient.getLocalPort() + "");
                t.start();
            }
        } catch (IOException ex) {
            System.out.println("Client INIT FALSE");
        }
    }

    @Override
    public void run() {
        System.out.println("START CLIENT");
        while (!isDisconect) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte buffer[] = new byte[1024];
                for (int s; (s = dIn.read(buffer)) != -1;) {
                    baos.write(buffer, 0, s);
                }
                byte result[] = baos.toByteArray();
                System.out.println("result " + result.length);
                System.out.println(new String(result));
                isDisconect = true;
            } catch (IOException ex) {
                Logger.getLogger(SOCKET_CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (isDisconect == true) {
            System.out.println("CLIENT END");
            if (this.dIn != null) {
                try {
                    this.dIn.close();
                } catch (IOException ex) {
                    Logger.getLogger(SOCKET_CLIENT.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (this.dOut != null) {
                try {
                    this.dOut.close();
                } catch (IOException ex) {
                    Logger.getLogger(SOCKET_CLIENT.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (this.sClient != null) {
                try {
                    this.sClient.close();
                } catch (IOException ex) {
                    Logger.getLogger(SOCKET_CLIENT.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public void SendRequest(byte[] request) {
        try {
            
            this.dOut.write(request);
            this.dOut.flush();
        } catch (IOException ex) {
            System.out.println("SEND MESSAGE FALSE");
        }
    }

}
