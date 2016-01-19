/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gma.common;

import gma.object.GMAClient;
import gma.server.GMAEventRemoveClientTimeOut;
import java.util.HashMap;
import java.util.Timer;
import java.util.Date;
/**
 *
 * @author dungnt
 */
public class GMAShare {
    private static final GMAShare ourInstance = new GMAShare();
    private HashMap<String, GMAClient> clients = null;
    private HashMap<String, GMAClient> clientsTimeout = null;
    private HashMap<String, GMAClient> clientsLostConnection = null;
    private Timer timer = null;
    public static GMAShare getInstance() {
        return ourInstance;
    }

    private GMAShare() {
        this.clients = new HashMap<>();
        this.clientsTimeout = new HashMap<>();
        this.clientsLostConnection = new HashMap<>();
        this.timer = new Timer();
        this.timer.schedule(new GMAEventRemoveClientTimeOut(),0, 10000);
       
    }

    // CURENT ONLINE
    public void onAddClient(GMAClient client){
        client.setTimeToBeginConnect(new Date().getTime());
        this.clients.put(client.getClientName(), client);
    }
    public GMAClient getClient(String clientID){
        return this.clients.get(clientID);
    }
    public GMAClient onRemoveClient(String clientID){
        return this.clients.remove(clientID);
    }

    public HashMap<String, GMAClient> getClients() {
        return clients;
    }

    
    // CURENT LOST
    public void onAddClientLost(GMAClient client){
       
       client.setTimeToDisconect(new Date().getTime());
        this.clientsLostConnection.put(client.getClientName(), client);
    }
    public GMAClient getClientLost(String clientID){
        return this.clientsLostConnection.get(clientID);
    }
    public GMAClient onRemoveClientLost(String clientID){
        return this.clientsLostConnection.remove(clientID);
    }
    public HashMap<String, GMAClient> getClientsLostConnection() {
        return clientsLostConnection;
    }
    
    // CURENT TIME OUT
    public void onAddClientTimeOut(GMAClient client){
        client.setTimeToTimeOut(new Date().getTime());
        this.clientsTimeout.put(client.getClientName(), client);
    }
    public GMAClient getClientTimeOut(String clientID){
        return this.clientsTimeout.get(clientID);
    }
    public GMAClient onRemoveClientTimeOut(String clientID){
        return this.clientsTimeout.remove(clientID);
    }
    public HashMap<String, GMAClient> getClientsTimeout() {
        return clientsTimeout;
    }

    
}
