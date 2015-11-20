/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguyendung
 */
public class SocketClient {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",12345);
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("d_lg");
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String cmd = br.readLine();
            System.out.println("REV " + cmd);
            
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private static void PrintWriter(OutputStream outputStream) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
