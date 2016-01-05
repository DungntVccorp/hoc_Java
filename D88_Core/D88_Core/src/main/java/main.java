
import sum_socket_server_mutil_connection.AppShare;
import sum_socket_server_mutil_connection.SUMSERVER;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dungnt
 */
public class main {
    public static void main(String[] args) {
        AppShare.getInstance().getExxecutors().execute(new SUMSERVER());
    }
}
