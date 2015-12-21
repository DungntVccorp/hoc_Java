/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sum_socket_server_mutil_connection;

import java.util.ArrayList;
import java.util.concurrent.*;

public final class AppShare {
    
    protected ExecutorService exxecutors;
    protected ArrayList<SUM> listClient;
    
    private AppShare() {
        this.setExxecutors(Executors.newCachedThreadPool());
        this.listClient = new ArrayList<>();
    }
    
    public static AppShare getInstance() {
        return AppShareHolder.INSTANCE;
    }
    
    private static class AppShareHolder {
        private static final AppShare INSTANCE = new AppShare();
    }

    public ExecutorService getExxecutors() {
        return exxecutors;
    }

    public void setExxecutors(ExecutorService exxecutors) {
        this.exxecutors = exxecutors;
    }    

    public ArrayList<SUM> getListClient() {
        return listClient;
    }

    public void setListClient(ArrayList<SUM> listClient) {
        this.listClient = listClient;
    }
    
}
