/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sum_socket_server_mutil_connection;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class SUM implements Runnable{

    protected Socket socket = null;
    protected boolean isConnection = true;
    protected BufferedReader din = null;
    protected DataOutputStream dout = null;
    protected String ClientName = null;
    

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String ClientName) {
        this.ClientName = ClientName;
    }
    
    
    
    public SUM(Socket soc,String uuid) {
        try {
            this.setClientName(uuid);
            this.socket = soc;
            this.socket.setSoTimeout(10000);
            this.din = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.dout = new DataOutputStream(this.socket.getOutputStream());
            this.isConnection = true;
            System.out.println("New Client Connection");
        } catch (IOException ex) {
            this.isConnection = false;
            Logger.getLogger(SUM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendMessage(String messageString){
        try {
            this.dout.writeBytes(messageString);
        } catch (IOException ex) {
            Logger.getLogger(SUM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public void run() {
        while (isConnection) {            
            try {
                String line = din.readLine();
                if(line != null){
                    dout.writeBytes("OK \n");
                }
            } catch (IOException ex) {
                isConnection = false;
            }
        }
        
        if(!isConnection){
            try {
                this.din.close();
                this.dout.close();
                this.socket.close();
                System.out.println("Client Close");
                // Gửi tin nhắn cho những người còn lại
                
                
            } catch (IOException ex) {
                Logger.getLogger(SUM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
