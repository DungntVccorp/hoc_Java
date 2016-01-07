/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88server.core.service;

/**
 *
 * @author dungnt
 */
public class D88Service{
    
    public static D88Service getInstance() {
        return AppShareHolder.INSTANCE;
    }
    
    private static class AppShareHolder {
        private static final D88Service INSTANCE = new D88Service();
    }
    
    
    public void StartService(){
        
    }
    public void EndService(){
        
    }
    
    public boolean serviceIsStared(){
        return false;
    }
    
    
    
}
