/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutiltask;

/**
 *
 * @author nguyendung
 */
public class tag1 implements Runnable{

    public tag1() {
    }

    @Override
    public void run() {
        System.out.println("Tag 1");
        for(int i=0;i< 1000000000;i++){
            
        }
        System.out.println("tag1 End");
    }
    
}
