/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88server;

import d88server.core.DAO.D88Database;
import d88server.core.DAO.D88Database.D88DatabaseDelegate;
import d88server.core.object.D88Object;
import d88server.core.service.D88Service;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dungnt
 */

public class D88Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        D88Object d88Object = new D88Object("ping");
        d88Object.setBooleansForKey(new boolean[]{true,false,true,false}, "b");
        byte[] message = d88Object.getMessage();
        
        D88Database.getInstance().loadSettingFormDatabase(new D88DatabaseDelegate() {
                @Override
                public void didLoadConfig() {
                    try {
                        D88Service.getInstance().doStartService();
                    } catch (IOException ex) {
                        Logger.getLogger(D88Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });
        

    }

}
