/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket_client;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author dungnt
 */
public class EP9_MESSAGE implements Serializable {

    private static final long serialVersionUID = 1L;
    private String type = null;

    public EP9_MESSAGE(String type) {
        this.type = type;

    }
    /*
            // Convert to JSON
            // chứa được tất cả những loại dữ liệu phổ biến
            // gửi và nhận được tới iphone
     */

    @Override
    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("test1",1234);
        hashMap.put("test2","dungnt");
        hashMap.put("test3",new int[]{ 1,2,3,4,5,6,7,8,9,10 });
        
 
        System.out.println("" + hashMap.get("test1"));
        System.out.println("" + hashMap.get("test2"));
        int[] listid = (int[]) hashMap.get("test3");
        System.out.println("" + listid[9]);
        
        Gson gson = new Gson(); 
        String json = gson.toJson(hashMap);
        System.out.println("" + json);
        return json;
    }
    
    public String toJsonString(){
        HashMap hashMap = new HashMap();
        hashMap.put("test1",1234);
        hashMap.put("test2","dungnt");
        hashMap.put("test3",new int[]{ 1,2,3,4,5,6,7,8,9,10 });
        
 
        System.out.println("" + hashMap.get("test1"));
        System.out.println("" + hashMap.get("test2"));
        int[] listid = (int[]) hashMap.get("test3");
        System.out.println("" + listid[9]);
        
        Gson gson = new Gson(); 
        String json = gson.toJson(hashMap);
        System.out.println("" + json);
        return json;
    }

    public byte[] toData() {
        byte[] obj = toJsonString().getBytes();
        return obj;
    }
}
