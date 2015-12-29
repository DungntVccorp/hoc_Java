/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.d88.core.Object;

import java.io.IOException;
import java.util.UUID;
import java.util.zip.DataFormatException;


public class D88User extends D88Object{
    
    private String userName = "";
    private String password = "";
    private String appID = "";
    private String uuid = "";
    
   public D88User(String _userName,String _password){
       super("user");
       this.userName = _userName;
       this.password = _password;
       this.uuid = UUID.randomUUID().toString();
   }
    
    public D88User(String _cmd) {
        super(_cmd);
    }

    public D88User(byte[] d88Message) throws IOException, DataFormatException, Exception {
        super(d88Message);
    }
    
    
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppID() {
        return appID;
    }

    public String getUuid() {
        return uuid;
    }
    
    
}
