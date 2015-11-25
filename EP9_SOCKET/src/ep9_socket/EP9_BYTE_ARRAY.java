/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dungnt
 */
public class EP9_BYTE_ARRAY implements Runnable {

    private Socket client = null;
    private PrintWriter out = null;
    private DataInputStream dIn = null;
    private BufferedReader in = null;
    private boolean online = true;

    EP9_BYTE_ARRAY(Socket accept) {
        try {
            this.client = accept;
            out = new PrintWriter(this.client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            dIn = new DataInputStream(this.client.getInputStream());
            System.out.println("Thread Create");
        } catch (IOException ex) {
            System.out.println("Thread Create ERROR");
            Logger.getLogger(EP9_BYTE_ARRAY.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread Start");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte buffer[] = new byte[1024];
            for (int s; (s = dIn.read(buffer)) != -1;) {
                baos.write(buffer, 0, s);
            }
            byte result[] = baos.toByteArray();
            System.out.println("result "+result.length);
            System.out.println(new String(result));

        } catch (IOException ex) {
            Logger.getLogger(EP9_BYTE_ARRAY.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
