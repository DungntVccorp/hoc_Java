/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep7_singleton;

/**
 *
 * @author nguyendung
 */
public class EP7_SINGLETON {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        shareClass.getInstance().printHelloWord();
        System.out.println(shareClass.getInstance().getMainAPI());
    }
    
}
