/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversendfile;

import java.io.*;
import java.net.*;

public class ServerTest {

    public static void main(String[] args) {
        System.out.println("**********Server Program**************");
        int byteRead = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            if (!serverSocket.isBound()) {
                System.out.println("Sever Socket not Bounded...");
            } else {
                System.out.println("Server Socket bounded to Port : " + serverSocket.getLocalPort());
            }

            Socket clientSocket = serverSocket.accept();
            if (!clientSocket.isConnected()) {
                System.out.println("Client Socket not Connected...");
            } else {
                System.out.println("Client Socket Connected : " + clientSocket.getInetAddress());
            }

            while (true) {
                InputStream in = clientSocket.getInputStream();

                OutputStream os = new FileOutputStream("/Users/nguyendung/Desktop/Beo2.jpg");
                byte[] byteArray = new byte[2530818];
                 int total = 0;
                while ((byteRead = in.read(byteArray, 0, byteArray.length)) != -1) {
                    os.write(byteArray, 0, byteRead);
                    System.out.println("No. of Bytes Received : " + byteRead);
                    total += byteRead;
                }
                System.out.println("bytes : "+total );
                synchronized (os) {
                    os.wait(100);
                }
                os.flush();
                os.close();
                serverSocket.close();
                //System.out.println("File Received...");
            }

        } catch (Exception e) {
            System.out.println("Server Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
