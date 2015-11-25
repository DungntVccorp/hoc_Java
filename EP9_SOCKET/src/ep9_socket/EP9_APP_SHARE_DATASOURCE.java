/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket;

import java.util.concurrent.*;

/**
 *
 * @author nguyendung
 */
public class EP9_APP_SHARE_DATASOURCE {
   private static EP9_APP_SHARE_DATASOURCE instance = null;
   private static ExecutorService currentThreadUser = null;
   protected EP9_APP_SHARE_DATASOURCE() {
      // Exists only to defeat instantiation.
   }
    public static EP9_APP_SHARE_DATASOURCE getInstance() {
      if(instance == null) {
         instance = new EP9_APP_SHARE_DATASOURCE();
         if (currentThreadUser == null) {
                currentThreadUser = Executors.newCachedThreadPool();
            }
      }
      return instance;
   }
    public void addClient(EP9_BYTE_ARRAY server){
        currentThreadUser.execute(server);
    }
    public int CountClient(){
        return ((ThreadPoolExecutor) currentThreadUser).getActiveCount();
    }
    public void sendMessToAllUser(String message){
        
    }
    
    
    
}
