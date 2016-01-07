/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.d88.core.Server;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author dungnt
 */
public class D88ShareServer {
    protected ExecutorService exxecutors;
    protected ArrayList<D88ClientConnection> listClient;

    public D88ShareServer() {
        this.setExxecutors(Executors.newCachedThreadPool());
        this.listClient = new ArrayList<>();
    }
    
    
    
    public static D88ShareServer getInstance() {
        return AppShareHolder.INSTANCE;
    }
    
    private static class AppShareHolder {
        private static final D88ShareServer INSTANCE = new D88ShareServer();
    }

    public ExecutorService getExxecutors() {
        return exxecutors;
    }

    public void setExxecutors(ExecutorService exxecutors) {
        this.exxecutors = exxecutors;
    }

    public ArrayList<D88ClientConnection> getListClient() {
        return listClient;
    }

    public void setListClient(ArrayList<D88ClientConnection> listClient) {
        this.listClient = listClient;
    }
    
    
    
    
}
