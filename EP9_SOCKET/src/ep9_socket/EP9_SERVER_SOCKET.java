package ep9_socket;

import java.net.*;

public class EP9_SERVER_SOCKET {

    protected ServerSocket server = null;
    
    EP9_SERVER_SOCKET(){
        
    }

    public void startServer() {
        try {
            
            if (this.server == null) {
                this.server = new ServerSocket(1234);
            }
            System.out.println("Server Started!");
            // create 1 thred nếu có 1 client connect 
            while (true) {  
                EP9_APP_SHARE_DATASOURCE.getInstance().addClient(new EP9_BYTE_ARRAY(this.server.accept()));
            }
            
            
        } catch (Exception e) {
        }

    }
}
