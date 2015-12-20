/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutiltask;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguyendung
 */
public class tag2 implements Runnable{

    public tag2() {
    }

    @Override
    public void run() {
        try {
            System.out.println("Tag 2");
            for(int i=0;i< 2000000000;i++){
                
            }
            Thread.sleep(5000);
            
            System.out.println("tag2 End");
        } catch (InterruptedException ex) {
            Logger.getLogger(tag2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
