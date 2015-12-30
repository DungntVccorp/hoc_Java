/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.d88.core.Object;

import java.io.IOException;
import java.util.zip.DataFormatException;

public class D88User extends D88Object {


    public D88User() {
        
    }

    public D88User(String _userName, String _password) {
        super("user");
        
    }
    public D88User(byte[] d88Message) throws IOException, DataFormatException, Exception {
        super(d88Message);
    }
    public String getUserName() {
        return this.getStringForKey("userName");
    }
    public void setUserName(String userName) {
        this.setStringForKey(userName, "userName");
    }
    public String getPassword() {
        return this.getStringForKey("password");
    }
    public void setPassword(String password) {
        this.setStringForKey(password, "password");
    }
    public String getAppID() {
        return this.getStringForKey("appid");
    }
    public String getUuid() {
        return this.getStringForKey("uuid");
    }
}
