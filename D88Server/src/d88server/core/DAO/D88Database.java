/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88server.core.DAO;

/**
 *
 * @author dungnt
 */
public class D88Database {
    private static final D88Database shareIntance = new D88Database();
    public static D88Database getInstance() {
        return shareIntance;
    }
    public D88Database() {
        
    }
    
}
