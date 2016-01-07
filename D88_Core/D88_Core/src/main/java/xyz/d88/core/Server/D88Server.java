/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.d88.core.Server;

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
public class D88Server {


    private boolean loadShareInstance(){
        return D88Share.getInstance().isShareState();
    }
    private boolean loadShareDatabase(){
        System.out.println("ShareDatabase Done!");
        return true;
    }
    
    private boolean loadConfig(){
        this.loadShareInstance();
        this.loadShareDatabase();
        System.out.println("Config Done!");
        return true;
    }
    
    private boolean startSocketServer(){
        System.out.println("SocketServer Done!");
        return true;
    }
    
    private boolean startModules(){
        System.out.println("Modules Done!");
        return true;
    }
    
    public boolean StartServer(){
        this.loadConfig();
        this.startSocketServer();
        this.startModules();
        System.out.println("SERVER STARTED!");
        System.out.println(D88Share.getInstance().getConfigForKey(D88Constant.SERVER_PORT));
        return true;
    }
    
}

