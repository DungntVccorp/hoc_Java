/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88server.core.service;

import d88server.core.common.D88Constant;
import d88server.core.common.D88SShare;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 * @author dung.nt
 */
interface D88NetworkServiceDelegate{
    public void didStartNetworkService();
    public void didStartFailer(String message);
    public void didEndNetworkService();
    public void ClientDidConnect(D88ClientConnection client);
}


public final class D88NetworkService implements Runnable{
    private ServerSocket server = null;
    private boolean isOnline = false;
    private D88NetworkServiceDelegate delegate;
    
    public D88NetworkService() throws IOException {
        this.isOnline = true;
        this.doStartNetwork();
    }
    
    public void doStartNetwork(){
        if(server == null){
            try {
                server = new ServerSocket(D88SShare.getInstance().GetIntConfig(D88Constant.SERVER_PORT));
                if(this.delegate != null){
                    this.delegate.didStartNetworkService();
                }
            } catch (IOException ex) {
                if(this.delegate != null){
                    this.delegate.didStartFailer(ex.getMessage());
                }
            }
        }
    }
    public void doEndNetwork(){
        this.isOnline = false;
    }

    public void setDelegate(D88NetworkServiceDelegate delegate) {
        this.delegate = delegate;
    }
    
    
    @Override
    public void run() {
        while (isOnline) {            
            try {
                Socket accept = this.server.accept();
                D88ClientConnection d88ClientConnection = new D88ClientConnection(accept);
                if(delegate != null){
                    this.delegate.ClientDidConnect(d88ClientConnection);
                }
            } catch (IOException ex) {
                Logger.getLogger(D88NetworkService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!isOnline){
            try {
                this.server.close();
                if(delegate != null){
                    this.delegate.didEndNetworkService();
                }
            } catch (IOException ex) {
                Logger.getLogger(D88NetworkService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
