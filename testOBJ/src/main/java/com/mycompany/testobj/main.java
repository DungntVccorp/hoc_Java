/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testobj;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 *
 * @author dungnt
 */
public class main {

    
    public static void main(String[] args) throws Exception {

        
//        DBSObject dbsObject = new DBSObject("d_pl");
//        dbsObject.setStringForKey("123", "d_pl_a");
//        dbsObject.setStringsForKey(new String[] { "foo", "bar" , "bar1" , "ba2" }, "d_pl_b");
//        dbsObject.setIntegerForKey(5, "inkey");
//        dbsObject.setIntegersForKey(new Integer[] {1,2,3},"inkeys");
//        dbsObject.setDoubleForKey(1.0, "doubleKey");
//        dbsObject.setDoublesForKey(new Double[] {1.1,2.2,3.3}, "doubleKeys");
//        dbsObject.setBooleanForKey(Boolean.FALSE, "boolkey");
//        dbsObject.setDBSObjectForKey(dbsObject, "self");
//        
//        System.out.println(dbsObject.toJSONMessage());
//        
//        DBSObject dbsObject1 = new DBSObject(dbsObject.toByteMessage());
//        System.out.println(dbsObject1.getBooleanForKey("boolkey"));  
//        System.out.println(dbsObject1.getCmd());
//        String[] stringsForKey = dbsObject1.getStringsForKey("d_pl_b");
//        for (String stringsForKey1 : stringsForKey) {
//            System.out.println(stringsForKey1);
//        }
        
        
        String str = "I am what I am hhhhhhhhhhhhhhhhhhhhhhhhhhhhh I am what I am hhhhhhhhhhhhhhhhhhhhhhhhhhhhh I am what I am hhhhhhhhhhhhhhhhhhhhhhhhhhhhh I am what I am hhhhhhhhhhhhhhhhhhhhhhhhhhhhh";
        
        System.out.println(str.getBytes().length);
        byte[] compress = DBSZiper.compress(str.getBytes());
        System.out.println(compress.length);
        byte[] decompress = DBSZiper.decompress(compress);
        System.out.println(decompress.length);
        System.out.println(new String(decompress));
       
    }
}
