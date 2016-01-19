/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gma.server;

import gma.common.GMAShare;
import gma.object.GMAClient;
import java.util.Date;
import java.util.Set;
import java.util.TimerTask;

/**
 *
 * @author dungnt
 */
public class GMAEventRemoveClientTimeOut extends TimerTask{

    @Override
    public void run() {
        Set<String> keySet = GMAShare.getInstance().getClientsTimeout().keySet();
        for (String key : keySet) {
            System.out.println(key);
            GMAClient clientTimeOut = GMAShare.getInstance().getClientTimeOut(key);
            System.out.println(new Date().getTime() - clientTimeOut.getTimeToTimeOut());
        }
    }
    
}
