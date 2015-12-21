/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sum_socket_server_mutil_connection;

public class SUM_SOCKET_SERVER_MUTIL_CONNECTION {

    public static void main(String[] args) {
        AppShare.getInstance().getExxecutors().execute(new SUMSERVER());
    }
    
}
