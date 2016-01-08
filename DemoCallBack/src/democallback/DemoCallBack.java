/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package democallback;

/**
 *
 * @author dungnt
 */
public class DemoCallBack {

    /**
     * @param args the command line arguments
     */
    
    
    interface delegateTest{
        public void testtest();
    }
    
    
    public static void runtesttest(delegateTest callbackTest){
        System.out.println("RUN RUN");
        callbackTest.testtest();
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        runtesttest(new delegateTest(){

            @Override
            public void testtest() {
                System.out.println("RUN OK");
            }
            
        });
    }
    
}
