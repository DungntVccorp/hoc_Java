/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutilthread;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DisplayMessage implements Runnable{
        private String message = "";
        private Thread t;
        
        DisplayMessage(String sender){
            message = sender;
            System.out.println("Creating " +  sender );
        }
        
        @Override
        public void run() {
            
            try {
                for (int i=0;i<4;i++){
                    System.out.println("Message " + message);
                    Thread.sleep(1000);
                }
                
                System.out.println("Thread exiting.");
            } catch (InterruptedException ex) {
                Logger.getLogger(DisplayMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        public void start(){
            System.out.println("Starting " +  message );
            if(t == null){
                t = new Thread(this, message);
                t.start();
            }
        }
        
    
    }
