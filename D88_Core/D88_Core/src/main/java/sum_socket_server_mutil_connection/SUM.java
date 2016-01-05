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
import xyz.d88.core.Object.D88Object;

public final class SUM implements Runnable {

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

    public SUM(Socket soc, String uuid) {
        try {
            System.out.println("Client Did Connect");
            AppShare.getInstance().getListClient().add(this);
            this.setClientName(uuid);
            this.socket = soc;
            //this.socket.setSoTimeout(50000);
            this.din = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.dout = new DataOutputStream(this.socket.getOutputStream());
            this.isConnection = true;
            
            
            D88Object d88Object = new D88Object("hallo", "1234");
        d88Object.setBooleansForKey(new boolean[] {true,false,true}, "booleanKey");
        d88Object.setIntegersForKey(new int[] {1,2,3,4}, "intskey");
        d88Object.setDoublesForKey(new double[] {1,2,3,4}, "double_key");
        d88Object.setStringsForKey(new String[] {"a","b","c"}, "String_Key");
        
        byte[] toD88Message = d88Object.toD88Message();
            System.out.println(toD88Message.length);
            this.dout.write(toD88Message);
            
        } catch (IOException ex) {
            this.isConnection = false;
            Logger.getLogger(SUM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SUM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String messageString) {
        try {
            this.dout.write(messageString.getBytes());
        } catch (IOException ex) {
            this.isConnection = false;
            AppShare.getInstance().getListClient().remove(this);
        }
    }

    public void sendMessageToAll(String messString) {
        for (int i = 0; i < AppShare.getInstance().getListClient().size(); i++) {
            if (!AppShare.getInstance().getListClient().get(i).getClientName().equals(this.getClientName())) {
                System.out.println("Send " + AppShare.getInstance().getListClient().get(i).getClientName());
                AppShare.getInstance().getListClient().get(i).sendMessage(messString);
            }
        }
    }

    @Override
    public void run() {
        while (isConnection) {
            if(!this.socket.isConnected()){
                isConnection = false;
            }
            try {
                String line = din.readLine();
                if (line != null) {
                    if("Ping".equals(line)){
                        this.sendMessage("Pong\n");
                    }
                    else{
                        System.out.println(line);
                        this.sendMessageToAll(line);
                    }
                    
                }
            } catch (IOException ex) {
                isConnection = false;
            }
        }

        if (!isConnection) {
            try {
                this.din.close();
                this.dout.close();
                this.socket.close();

                // Gửi tin nhắn cho những người còn lại
                System.out.println("DISCONECT");
                AppShare.getInstance().getListClient().remove(this);
            } catch (IOException ex) {
                Logger.getLogger(SUM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
