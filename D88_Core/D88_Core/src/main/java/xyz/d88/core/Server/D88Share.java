/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.d88.core.Server;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dungnt
 */
public class D88Share {
    private static class AppShareHolder {
        private static final D88Share INSTANCE = new D88Share();
    }
    private static final String ConfigFileName = "D88SConfig.properties";
    
    
    protected Properties sConfig = null;
    protected ExecutorService exxecutors;
    protected ArrayList<D88ClientConnection> connections;
    
    private boolean ShareState = false;
    
    public static D88Share getInstance() {
        return AppShareHolder.INSTANCE;
    }
    
    private boolean loadConfigFile(){
//        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(ConfigFileName);
//        if(resourceAsStream != null){
//            try {
//                this.sConfig = new Properties();
//                this.sConfig.load(resourceAsStream);
//                resourceAsStream.close();
//                return true;
//            } catch (IOException ex) {
//                Logger.getLogger(D88Share.class.getName()).log(Level.SEVERE, null, ex);
//                return false;
//            }
//        }
//        else{
//            return false;
//        }
        return true;
    }
    

    private D88Share() {
        this.ShareState = true;//this.loadConfigFile();
        if(this.ShareState){
            
            this.exxecutors = Executors.newCachedThreadPool();           
            this.connections = new ArrayList<>();
        }
    }
    // SET GET
    public boolean isShareState() {
        return ShareState;
    }
    
    public String getConfigForKey(String keyString){
        return this.sConfig.getProperty(keyString);
    }
    
    
    

    public ExecutorService getExxecutors() {
        return exxecutors;
    }
    
    public ArrayList<D88ClientConnection> getListClient() {
        return connections;
    }
    public void addClient(D88ClientConnection client){
            this.exxecutors.execute(client);
    }
    public void removeClient(D88ClientConnection client){
        this.connections.remove(client);
    }

    
    
}
