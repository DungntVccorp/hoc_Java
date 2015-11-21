/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversocket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguyendung
 */
public class Command implements Runnable {

    private Socket sk = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    int byteRead = 0;
    DataInputStream inData = null;
    String CMD = null;

    public Command(Socket sk) {
        this.sk = sk;
    }

    @Override
    public void run() {
        System.out.println("Accepted connection. ");
        try {
            out = new PrintWriter(sk.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            inData = new DataInputStream(new BufferedInputStream(sk.getInputStream()));
            System.out.println("Reader and writer created. ");

            while (true) {
//                if ((CMD = in.readLine()) != null) {
//                    if ("d_lg".equals(CMD)) {
//                        out.println("OK");
//                    } else if ("d_get_info".equals(CMD)) {
//                        out.println("DONE");
//                        
//                    } else {
//                        out.println("Error");
//                    }
//                }
//                else {
//                    
//                }
                try (OutputStream os = new FileOutputStream("/Users/nguyendung/Desktop/Beo2.jpg")) {
                    byte[] byteArray = new byte[2530817];

                    while ((byteRead = inData.read(byteArray, 0, byteArray.length)) != -1) {
                        os.write(byteArray, 0, byteRead);
                        System.out.println("No. of Bytes Received : " + byteRead);
                    }
                    synchronized (os) {
                        os.wait(100);
                    }
                    os.close();
                }

            }

        } catch (Exception e) {
            try {
                this.out.close();
                this.in.close();
                this.sk.close();
                System.out.println("Connection Close" + e.getLocalizedMessage());
            } catch (IOException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally {
            try {
                this.out.close();
                this.in.close();
                this.sk.close();
            } catch (IOException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stop() {
        System.out.println("STOP");
    }
}
