package sum_socket_server_mutil_connection;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;


public class SUMSERVER implements Runnable{


    protected ServerSocket server = null;
    
    public SUMSERVER() {
        try {
            
            server = new ServerSocket(1234);
            System.out.println("Server Started!");
        } catch (IOException ex) {
            Logger.getLogger(SUMSERVER.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {            
            try {
                Socket accept = server.accept();
                AppShare.getInstance().getExxecutors().execute(new SUM(accept,UUID.randomUUID().toString()));
            } catch (IOException ex) {
                Logger.getLogger(SUMSERVER.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
