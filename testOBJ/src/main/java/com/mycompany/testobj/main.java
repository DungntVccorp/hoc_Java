/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testobj;


/**
 *
 * @author dungnt
 */
public class main {

    
    public static void main(String[] args) {

        
        DBSObject dbsObject = new DBSObject("d_pl");
        dbsObject.setStringForKey("123", "d_pl_a");
        dbsObject.setStringsForKey(new String[] { "foo", "bar" , "bar1" , "ba2" }, "d_pl_b");
        dbsObject.setIntegerForKey(5, "inkey");
        dbsObject.setIntegersForKey(new Integer[] {1,2,3},"inkeys");
        dbsObject.setDoubleForKey(1.0, "doubleKey");
        dbsObject.setDoublesForKey(new Double[] {1.1,2.2,3.3}, "doubleKeys");
        dbsObject.setBooleanForKey(Boolean.FALSE, "boolkey");
        dbsObject.setDBSObjectForKey(dbsObject, "self");
        
        System.out.println(dbsObject.toJSONMessage());
        
        DBSObject dbsObject1 = new DBSObject(dbsObject.toByteMessage());
        System.out.println(dbsObject1.getBooleanForKey("boolkey"));  
        System.out.println(dbsObject1.getCmd());
        String[] stringsForKey = dbsObject1.getStringsForKey("d_pl_b");
        for (String stringsForKey1 : stringsForKey) {
            System.out.println(stringsForKey1);
        }
    }
}
