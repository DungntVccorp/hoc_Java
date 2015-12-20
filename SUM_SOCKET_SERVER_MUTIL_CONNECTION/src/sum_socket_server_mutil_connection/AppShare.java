/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sum_socket_server_mutil_connection;

import java.util.concurrent.*;

public final class AppShare {
    
    protected ExecutorService exxecutors;
    
    
    private AppShare() {
        this.setExxecutors(Executors.newCachedThreadPool());
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
}
