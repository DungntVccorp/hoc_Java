/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguyendung
 */
public class Command extends Thread{
    private Socket sk = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    public  Command(Socket sk){
        this.sk = sk;
    }
    @Override
    public void run(){
        System.out.println("Accepted connection. ");
        try {
            out = new PrintWriter(sk.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            System.out.println("Reader and writer created. ");
            String CMD = in.readLine();
            if("d_lg".equals(CMD)){
                out.println("Login");
            }
            else{
                out.println(CMD);
            }
            

        } catch (Exception e) {
            try {
                this.out.close();
                this.in.close();
                this.sk.close();
                System.out.println("Connection Close" + e.getLocalizedMessage());
            } catch (IOException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        finally{
            try {
                this.out.close();
                this.in.close();
                this.sk.close();
                System.out.println("Connection Close");
            } catch (IOException ex) {
                Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
