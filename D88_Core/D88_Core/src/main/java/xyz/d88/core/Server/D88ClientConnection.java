/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.d88.core.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dungnt
 */
public class D88ClientConnection implements Runnable {

    private Socket socket;
    private boolean isConnection = true;
    private BufferedReader din = null;
    private DataOutputStream dout = null;
    private String ClientName = null;

    @Override
    public void run() {
        System.out.println("CLIENT CONNECTION");
        while (isConnection) {
            if (!this.socket.isConnected()) {
                isConnection = false;
            }
            else{
                // read data form client 
            }
        }
        
        if (!isConnection) {
            try {
                this.din.close();
                this.dout.close();
                this.socket.close();
                D88Share.getInstance().getListClient().remove(this);
            } catch (IOException ex) {
                Logger.getLogger(D88ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public D88ClientConnection(Socket _socket) {
        try {
            boolean add = D88Share.getInstance().getListClient().add(this);
            if (add) {
                this.socket = _socket;
                this.socket.setSoTimeout(60000);
                this.din = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                this.dout = new DataOutputStream(this.socket.getOutputStream());
                this.isConnection = true;
            }
        } catch (SocketException ex) {
            Logger.getLogger(D88ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(D88ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String ClientName) {
        this.ClientName = ClientName;
    }

    public void sendMessage(String messageString) {
        try {
            this.dout.write(messageString.getBytes());
        } catch (IOException ex) {
            this.isConnection = false;
            D88Share.getInstance().getListClient().remove(this);
        }
    }

}
