/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellomaven;

import org.json.*;

/**
 *
 * @author dungnt
 */
public class helloWord {

    public static void main(String[] args) {
        final String json = "{\"employees\": [{\"firstName\": \"John\",\"lastName\": \"Doe\"},{\"firstName\": \"Anna\",\"lastName\": \"Smith\"},{\"firstName\": \"Peter\",\"lastName\": \"Jones\"}]}";
        JSONObject jsonObject = new JSONObject(json);
        boolean has = jsonObject.has("employees");
        if(has){
            System.err.println(jsonObject.getJSONArray("employees"));    
            
            JSONArray array = jsonObject.getJSONArray("employees");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                System.out.println(obj.getString("firstName"));
            }
            
        }
        
    }
}
