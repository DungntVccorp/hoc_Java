/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_chat;

import java.io.*;
import java.net.*;
        
        
public class ServerChat {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(6789);
        while(true){
            Socket accept = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String a = bufferedReader.readLine();
            String b = bufferedReader.readLine();
            Float t = Float.parseFloat(a) + Float.parseFloat(b);
            DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
            dataOutputStream.writeBytes(t + "\n");
            accept.close();
        }
    }
}
