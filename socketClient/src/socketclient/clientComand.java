/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dungnt
 */
public class clientComand extends Thread {

    private Socket sk = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public clientComand(Socket sk) {
        this.sk = sk;
    }

    @Override
    public void run() {
        System.out.println("Accepted connection. ");
        try {
            out = new PrintWriter(sk.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            out.println("d_lg");
//            while (true) {
//                String CMD = in.readLine();
//                if ("OK".equals(CMD)) {
//                    System.out.println("Login OK");
//                    out.println("d_get_info");
//                } else if ("DONE".equals(CMD)) {
//                    System.out.println("Begin Send File");
//                    File file = new File("/Users/nguyendung/Desktop/Beo1.jpg");
//                    if (!file.exists()) {
//                        System.out.println("File Not Existing.");
//                    } else {
//                        System.out.println("File Existing.");
//                    }
//                    int length = (int) file.length();
//                    byte[] byteArray = new byte[length];
//                    System.out.println("TOTAL " + length);
//                    FileInputStream fis = new FileInputStream(file);
//                    BufferedInputStream bis = new BufferedInputStream(fis);
//                    OutputStream os = sk.getOutputStream();
//                    int trxBytes = 0;
//                    while ((trxBytes = bis.read(byteArray, 0, byteArray.length)) != -1) {
//                        os.write(byteArray, 0, byteArray.length);
//                        System.out.println("Transfering bytes : " + trxBytes);
//                    }
//                    os.flush();
//
//                } else if ("END".equals(CMD)) {
//                    System.out.println("End Connection");
//                    break;
//                }
//                
//            }
            System.out.println("Begin Send File");
                    File file = new File("/Users/nguyendung/Desktop/Beo1.jpg");
                    if (!file.exists()) {
                        System.out.println("File Not Existing.");
                    } else {
                        System.out.println("File Existing.");
                    }
                    int length = (int) file.length();
                    byte[] byteArray = new byte[length];
                    System.out.println("TOTAL " + length);
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    OutputStream os = sk.getOutputStream();
                    int trxBytes = 0;
                    while ((trxBytes = bis.read(byteArray, 0, byteArray.length)) != -1) {
                        os.write(byteArray, 0, byteArray.length);
                        System.out.println("Transfering bytes : " + trxBytes);
                    }
                    os.flush();

        } catch (Exception e) {
            try {
                this.out.close();
                this.in.close();
                this.sk.close();
                System.out.println("Connection Close" + e.getLocalizedMessage());
            } catch (IOException ex) {
                Logger.getLogger(clientComand.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally {
//            try {
//                this.out.close();
//                this.in.close();
//                this.sk.close();
//                System.out.println("Connection Close");
//            } catch (IOException ex) {
//                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

}
