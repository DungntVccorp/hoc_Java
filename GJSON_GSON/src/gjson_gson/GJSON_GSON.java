/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gjson_gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 *
 * @author nguyendung
 */
import java.lang.reflect.Type;
import java.util.Map;
public class GJSON_GSON {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Gson gson = new Gson();
        Map<String, Object> myMap = gson.fromJson("{\"t\": \"userB\",\"m\": \"hello\",\"f\": \"userA\"}", type);
        System.out.println(myMap.get("t"));
        System.out.println(myMap.get("m"));
        System.out.println(myMap.get("f"));
        
    }
    
}
