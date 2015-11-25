/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket;

import java.net.Socket;

/**
 *
 * @author dungnt
 */
public class EP9_FILE implements Runnable{
    private Socket socket = null;
    
    EP9_FILE(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        
    }
}
