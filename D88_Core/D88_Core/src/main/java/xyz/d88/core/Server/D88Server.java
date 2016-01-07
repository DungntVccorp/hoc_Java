/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.d88.core.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.d88.core.Common.D88Constant;

/**
 *
 * @author dungnt
 *         +---------------+    +-------------------+     +-------------------+    +-------------------+
 *         | SERVER START  | -> |LOAD DEFAULT CONFIG| ->  |   CONFING LOAD    | -> |   SHARE ABSTRACT  |
 *         +---------------+    +-------------------+     +-------------------+    +-------------------+
 *                                                        +-------------------+
 *                                                    ->  |   SHARE DATABASE  |
 *                                                        +-------------------+
 * 
 *                              +-------------------+
 *                           -> |START SOCKET SERVER| 
 *                              +-------------------+
 * 
 *                              +-------------------+
 *                           -> |START LOAD MODULE  | 
 *                              +-------------------+
 *                           
 * 
 */
public class D88Server implements Runnable{
    protected ServerSocket server = null;
    private boolean isStart = false;

    public D88Server() {
        try {
            //int port = Integer.parseInt(D88Share.getInstance().getConfigForKey(D88Constant.SERVER_PORT));
            server = new ServerSocket(1234);
            isStart = true;
        } catch (IOException ex) {
            isStart = false;
            Logger.getLogger(D88Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        System.out.println("SERVER STARTED");
        while (isStart) {            
            try {
                Socket accept = this.server.accept();
                if(D88Share.getInstance().getListClient().size() < Integer.parseInt(D88Share.getInstance().getConfigForKey(D88Constant.CLIENT_MAX_CONNECTION))){                    
                    D88Share.getInstance().addClient(new D88ClientConnection(accept));
                }
                else{
                    accept.close();
                    System.out.println("MAX CLIENT");
                }
                
            } catch (IOException ex) {
                Logger.getLogger(D88Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

