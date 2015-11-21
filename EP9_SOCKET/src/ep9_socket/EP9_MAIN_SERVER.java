/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguyendung
 */
public class EP9_MAIN_SERVER implements Runnable{
    protected Socket socket = null;
    protected PrintWriter out = null;
    protected BufferedReader in = null;
    EP9_MAIN_SERVER(Socket accept) throws IOException {
        this.socket = accept;
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }
    
    public void run(){
        try {
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                if("GG".equals(inputLine)){
                    this.out.println("COUNT " + EP9_APP_SHARE_DATASOURCE.getInstance().CountClient());
                }
                else if("MESSAGE".equals(inputLine)){
                    this.sendMessageToAllUser();
                }
                else{
                    this.out.println("HIHI " + inputLine);
                }
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(EP9_MAIN_SERVER.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendMessage(String message){
        this.out.println(message);
    }
    public void stop(){
        
    }
    public void sendMessageToAllUser(){
        
    }
}
