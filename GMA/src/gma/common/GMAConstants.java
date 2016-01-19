/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gma.common;

/**
 *
 * @author dungnt
 */
public final class GMAConstants {
//+------------------+
//|    CONFIG        |                                                                  
//+------------------+    

    public static final int SERVER_LISTEN_PORT                                  = 1234;
//+------------------+
//|       CMD        |                                                                  
//+------------------+    
    // RECONECT
    public static final String RECONECT_SERVER_REQUEST                          = "rr"; // hỏi client xem là request mới hay request cũ 
//+------------------+
//|     PARAMS       |                                                                  
//+------------------+ 
    // RECONECT
    public static final String RECONECT_REQUEST_ANSWER                          = "ra"; // client trả lời là cũ hay mới giá trị là boolean // 
    public static final String RECONECT_REQUEST_OLD_TOKEN                       = "ro"; // token cũ nếu client trả lời là reconect 
    public static final String RECONECT_REQUEST_NEW_TOKEN                       = "rn"; // token mới nếu client trả lời là không phải reconect 
    public static final String RECONECT_TIME_OUT                                = "rt"; // connect cũ bị time out
    public static final String RECONECT_OK                                      = "rs"; // server gi nhận reconect và báo thành công về client
}
