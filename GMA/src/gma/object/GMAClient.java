/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gma.object;

import d88.core.network.D88ClientConnection;
/**
 *
 * @author dungnt
 */
public class GMAClient {
    private D88ClientConnection connection = null;
    private String clientName = "";
    private long timeToDisconect = 0;
    private long timeToTimeOut = 0;
    private long timeToBeginConnect = 0;
    
    public GMAClient(D88ClientConnection client) {
        this.connection = client;
        this.clientName = client.getClientName();
        
    }

    public D88ClientConnection getConnection() {
        return connection;
    }

    public void setConnection(D88ClientConnection connection) {
        this.connection = connection;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public long getTimeToDisconect() {
        return timeToDisconect;
    }

    public void setTimeToDisconect(long timeToDisconect) {
        this.timeToDisconect = timeToDisconect;
    }

    public long getTimeToTimeOut() {
        return timeToTimeOut;
    }

    public void setTimeToTimeOut(long timeToTimeOut) {
        this.timeToTimeOut = timeToTimeOut;
    }

    public long getTimeToBeginConnect() {
        return timeToBeginConnect;
    }

    public void setTimeToBeginConnect(long timeToBeginConnect) {
        this.timeToBeginConnect = timeToBeginConnect;
    }

    
    
    
    
}
