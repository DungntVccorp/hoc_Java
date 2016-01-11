/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gma.network;

import d88.core.network.D88ClientConnection;
import d88.core.network.D88ServerNetwork;
import d88.core.object.D88SObject;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

/**
 *
 * @author dungnt
 */
public class GMAServer implements D88ServerNetwork.D88NetworkDelegate{

    public GMAServer() {
        
        D88ServerNetwork d88ServerNetwork = new D88ServerNetwork(1234, Executors.newCachedThreadPool(), this);
        
    }

    @Override
    public void onD88NetworkError(String message) {
    }

    @Override
    public void ServerConnectionDidStart() {
        System.out.println("SERVER DID START");
    }

    @Override
    public void ServerConnectionStartFailure(String message) {
    }

    @Override
    public void ServerConnectionDidEnd() {
    }

    @Override
    public void ServerConnectionDidEndFailure(String message) {
    }

    @Override
    public void ServerConnectionClientDidConnect(D88ClientConnection client) {
    }

    @Override
    public void ServerConnectionClientConnectFailure(String message) {
    }

    @Override
    public void clientdidReceiveMessage(byte[] message, D88ClientConnection formClient) {
        try {
            D88SObject d88SObject = new D88SObject(message);
            System.out.println(d88SObject.getCmd());
        } catch (DataFormatException ex) {
            Logger.getLogger(GMAServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GMAServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void clientConnectionLost(D88ClientConnection client) {
    }

    @Override
    public void clientConnectionTimeout(D88ClientConnection client) {
    }

    @Override
    public void clientdidDisconnect(D88ClientConnection client) {
    }

    @Override
    public void clientDidSendMessage(byte[] message, D88ClientConnection client) {
    }

    @Override
    public void clientdidSendMessageFailer(D88ClientConnection client, String message) {
    }
    
    
}
