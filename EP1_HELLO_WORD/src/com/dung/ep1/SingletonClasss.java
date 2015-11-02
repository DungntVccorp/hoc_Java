
package com.dung.ep1;


public class SingletonClasss {
    private static SingletonClasss _shareIntance = null;
    
    protected SingletonClasss(){
        
    }
    public static SingletonClasss shareIntance(){
        if(_shareIntance == null){
            _shareIntance = new SingletonClasss();
        }
        return _shareIntance;
    }
    
    private void callPrivateFunction(){
        System.out.println("callPrivateFunction");
    }
    protected void callProtectedFunction(){
        System.out.println("callProtectedFunction");
    }
    public void testFuncShareSingleton(){
        System.out.println("Dungnt Test Share singleton class");
        this.callPrivateFunction();
        this.callProtectedFunction();
    }
}
