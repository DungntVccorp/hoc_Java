/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep10_hashmap_json;

import java.util.HashMap;
import com.google.gson.*;
/**
 *
 * @author nguyendung
 */
public class EP10_HASHMAP_JSON {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
    }
}
