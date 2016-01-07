/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88server.core.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author dungnt
 */
public final class D88Service implements D88NetworkServiceDelegate,D88ClientConnectionDelegate{
    
    
    private ExecutorService exxecutors;
    private ArrayList<D88ClientConnection> connections;
    private D88NetworkService networkService;
    
    private boolean isStart = false;
    
    public static D88Service getInstance() {
        return AppShareHolder.INSTANCE;
    }
    
    private static class AppShareHolder {
        private static final D88Service INSTANCE = new D88Service();
    }

    public D88Service() {
        this.setExxecutors(Executors.newCachedThreadPool());
        this.connections = new ArrayList<>();
    }
    
    public ExecutorService getExxecutors() {
        return exxecutors;
    }

    public void setExxecutors(ExecutorService exxecutors) {
        this.exxecutors = exxecutors;
    }

    public ArrayList<D88ClientConnection> getListClient() {
        return connections;
    }

    public void setListClient(ArrayList<D88ClientConnection> listClient) {
        this.connections = listClient;
    }
    
    public void doStartService() throws IOException{
        this.networkService = new D88NetworkService();
        this.networkService.setDelegate(this);
        this.getExxecutors().execute(this.networkService);
        
    }
    public void doEndService(){
        
    }
    
    public boolean serviceIsStared(){
        return isStart;
    }
    
    // D88NetworkServiceDelegate DELEGATE

    @Override
    public void didStartNetworkService() {
        this.isStart = true;
    }

    @Override
    public void didStartFailer(String message) {
        this.isStart = false;
        System.out.println(message);
    }

    @Override
    public void didEndNetworkService() {
        this.isStart = false;
    }

    @Override
    public void ClientDidConnect(D88ClientConnection client) {
        String messageOnline = client.getClientName() + " is Online\n";
        connections.stream().forEach((connection) -> {
            connection.sendMessage(messageOnline.getBytes());
        });
        client.setDelegate(this);
        this.connections.add(client);
        this.exxecutors.execute(client);
    }
    // D88ClientConnectionDelegate DELEGATE

    @Override
    public void clientdidReceiveMessage(byte[] message, D88ClientConnection formClient) {
        System.out.println(new String(message) + formClient.getClientName());
    }

   

    @Override
    public void clientdidDisconnect(D88ClientConnection client) {
        String messageOnline = client.getClientName() + " is offline\n";
        this.connections.remove(client);
        client.closeConection();
        
        connections.stream().forEach((connection) -> {
            connection.sendMessage(messageOnline.getBytes());
        });
    }

    @Override
    public void clientdidSendMessageFailer(D88ClientConnection client) {
        
    }
    
    
    
    
    
}
