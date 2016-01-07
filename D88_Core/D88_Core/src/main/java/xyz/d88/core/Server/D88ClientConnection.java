/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.d88.core.Server;

import java.net.Socket;

/**
 *
 * @author dungnt
 */
public class D88ClientConnection implements Runnable{

    private Socket socket;
    private boolean isConnection = true;
    
    @Override
    public void run() {
        while (isConnection) {            
            
        }
    }

    public D88ClientConnection(Socket _socket) {
        
    }
    
    
    
}
