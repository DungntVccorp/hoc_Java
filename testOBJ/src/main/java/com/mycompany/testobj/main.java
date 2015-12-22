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
        dbsObject.setStringsForKey(new String[] { "foo1", "bar2" , "bar3" , "ba4" }, "d_pl_c");
        DBSObject dbsObject1 = new DBSObject(dbsObject.toByteMessage());
        String[] stringsForKey = dbsObject1.getStringsForKey("d_pl_c");
        for (String stringsForKey1 : stringsForKey) {
            System.out.println(stringsForKey1);
        }
    }
}
