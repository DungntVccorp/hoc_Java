/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutiltask;

import java.util.concurrent.*;

public class MutilTask {

    public static void main(String[] args) {
        
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        
        tag1 tag1 = new tag1();
        tag2 tag2 = new tag2();
        
        newCachedThreadPool.execute(tag1);
        newCachedThreadPool.execute(tag2);
        
    }
    
}
