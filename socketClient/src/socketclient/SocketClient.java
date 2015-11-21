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
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguyendung
 */
public class SocketClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {  
            Socket socket = new Socket("localhost", 9999);
//            Thread thrd = new Thread(new clientComand(socket));
//            thrd.start();
            File myfile = new File("/Users/nguyendung/Desktop/Beo1.jpg");       //local file path.
            
            
            if(!myfile.exists())
                System.out.println("File Not Existing.");
            else
                System.out.println("File Existing.");
            
            byte[] byteArray = new byte[(int)myfile.length()];
            
            FileInputStream fis = new FileInputStream(myfile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream os = socket.getOutputStream();
            int trxBytes =0;
            int total = 0;
            while((trxBytes = bis.read(byteArray, 0, byteArray.length)) !=-1)
            {
                os.write(byteArray, 0, byteArray.length);
                total += trxBytes;
                System.out.println("Transfering bytes : "+trxBytes );
            }
            System.out.println("bytes : "+total );
            os.flush();
            bis.close();

        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}