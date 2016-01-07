/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88server.core.service;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dung.nt
 */
interface D88ClientConnectionDelegate {

    public void clientdidReceiveMessage(byte[] message,D88ClientConnection formClient);
    public void clientdidDisconnect(D88ClientConnection client);
    public void clientdidSendMessageFailer(D88ClientConnection client);
}

public class D88ClientConnection implements Runnable {

    private Socket cSocket = null;
    private boolean isConnection = true;
    private DataInputStream din = null;
    private DataOutputStream dout = null;
    private String ClientName = null;
    private D88ClientConnectionDelegate delegate;

    public D88ClientConnection(Socket _acceptSocket) {
        this.ClientName = UUID.randomUUID().toString();
        try {
            this.cSocket = _acceptSocket;
            this.cSocket.setSoTimeout(60000);
            this.din = new DataInputStream(this.cSocket.getInputStream());
            this.dout = new DataOutputStream(this.cSocket.getOutputStream());
            this.isConnection = true;
        } catch (IOException ex) {
            Logger.getLogger(D88ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (isConnection) {
            if (!this.cSocket.isConnected()) {
                isConnection = false;
            } else {
                try {
                    // read data form client
                    byte buffer[] = new byte[1024];
                    int s = this.din.read(buffer);
                    if (s != -1) {
                        ByteArrayOutputStream readBytes = new ByteArrayOutputStream();
                        readBytes.write(buffer, 0, s);
                        if (this.delegate != null) {
                            this.delegate.clientdidReceiveMessage(readBytes.toByteArray(), this);
                        }
                    }
                } catch (IOException ex) {
                    if("Read timed out".equals(ex.getMessage())){
                        if (this.delegate != null){
                            this.delegate.clientdidDisconnect(this);
                        }
                    }
                }
            }
        }

        if (!isConnection) {
            try {
                this.din.close();
                this.dout.close();
                this.cSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(D88ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void closeConection() {
        this.isConnection = false;
    }

    public boolean isOnline() {
        return isConnection && this.cSocket.isConnected();
    }

    public String getClientName() {
        return ClientName;
    }

    public void setDelegate(D88ClientConnectionDelegate delegate) {
        this.delegate = delegate;
    }

    public void sendMessage(byte[] message) {
        try {
            this.dout.write(message);
        } catch (IOException ex) {
            if (this.delegate != null) {
                this.delegate.clientdidSendMessageFailer(this);
            }
        }
    }
}
