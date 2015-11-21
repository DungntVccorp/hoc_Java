/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket;

/**
 *
 * @author nguyendung
 */
public class EP9_SOCKET {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EP9_SERVER_SOCKET server = new EP9_SERVER_SOCKET();
        server.startServer();
    }
    
}
