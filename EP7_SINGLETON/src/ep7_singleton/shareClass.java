/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep7_singleton;

/**
 *
 * @author nguyendung
 */
public class shareClass {
   private static shareClass instance = null;
   private final String appMainBaseURL = "http://google.com.vn";
   protected shareClass() {
      // Exists only to defeat instantiation.
   }
   public static shareClass getInstance() {
      if(instance == null) {
         instance = new shareClass();
      }
      return instance;
   }
   public void printHelloWord(){
       System.out.println("HELLO WORD!");
   }
   public String getMainAPI(){
       return this.appMainBaseURL;
   }
   
}
