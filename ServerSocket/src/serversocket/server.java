/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversocket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author nguyendung
 */
public class server  {
    public ServerSocket server = null;
    public void startServer(){
        try {
            server = new ServerSocket(12345);
            System.out.println("Server Started!");
            while (true) {                
                Socket client = server.accept();
                Thread thrd = new Thread(new Command(client));
                thrd.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        server server1 = new server();
        server1.startServer();
    }
}
