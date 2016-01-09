/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88server.core.service;

import d88server.core.object.D88Object;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

/**
 *
 * @author dungnt
 */
public final class D88Service implements D88NetworkServiceDelegate, D88ClientConnectionDelegate {

    private static final D88Service INSTANCE = new D88Service();

    private ExecutorService exxecutors;
    private ArrayList<D88ClientConnection> connections;
    private D88NetworkService networkService;

    private boolean isStart = false;

    public static D88Service getInstance() {
        return INSTANCE;
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

    public void doStartService() throws IOException {
        this.networkService = new D88NetworkService(this);
        this.getExxecutors().execute(this.networkService);

    }

    public void doEndService() {

    }

    public boolean serviceIsStared() {
        return isStart;
    }

    // D88NetworkServiceDelegate DELEGATE
    @Override
    public void didStartNetworkService() {
        this.isStart = true;
        System.out.println("SERVICE DID STARTED!");
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
        try {
            String messageOnline = client.getClientName() + " is Online\n";
            connections.stream().forEach((connection) -> {
                connection.sendMessage(messageOnline.getBytes());
            });
            client.setDelegate(this);
            this.connections.add(client);
            this.exxecutors.execute(client);

            D88Object d88Object = new D88Object("ping");
            client.sendMessage(d88Object.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(D88Service.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // D88ClientConnectionDelegate DELEGATE
    @Override
    public void clientdidReceiveMessage(byte[] message, D88ClientConnection formClient) {
        try {
            D88Object d88Object = new D88Object(message);
            System.out.println(d88Object.getCmd());
            System.out.println(d88Object.getObjVer());
            System.out.println(d88Object.getObjAppID());
            System.out.println(d88Object.getObjForm());
            System.out.println(d88Object.getObjType());
            System.out.println(Arrays.toString(d88Object.getBooleansForKey("o_b")));
            d88Object.setCmd("pong");
            formClient.sendMessage(d88Object.getMessage());
            
        } catch (DataFormatException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(D88Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void clientdidDisconnect(D88ClientConnection client) {
        this.connections.remove(client);
        client.closeConection();
    }

    @Override
    public void clientdidSendMessageFailer(D88ClientConnection client) {

    }

}
