/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.io.*;
import java.net.*;

public class Clientprgm {

public static void main(String[] args)
{
    try
    {
        try (Socket socket = new Socket("localhost", 9999)) {
            if(!socket.isConnected())
                System.out.println("Socket Connection Not established");
            else
                System.out.println("Socket Connection established : "+socket.getInetAddress());
            
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
            while((trxBytes = bis.read(byteArray, 0, byteArray.length)) !=-1)
            {
                os.write(byteArray, 0, byteArray.length);
                System.out.println("Transfering bytes : "+trxBytes );
            }
            os.flush();
            bis.close();
        }

        System.out.println("File Transfered...");
    }
    catch(Exception e)
    {
        System.out.println("Client Exception : "+e.getMessage());
    }       
}
}