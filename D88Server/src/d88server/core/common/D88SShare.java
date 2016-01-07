/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88server.core.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dung.nt
 */
public final class D88SShare {
    private static final D88SShare INSTANCE = new D88SShare();
    private static final String ConfigFileName = "ServerConfig.properties";
    
    private Properties sConfig = null;
    
    public static D88SShare getInstance() {
        return INSTANCE;
    }

    public D88SShare() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(ConfigFileName);
        if(resourceAsStream != null){
            try {
                this.sConfig = new Properties();
                this.sConfig.load(resourceAsStream);
                resourceAsStream.close();
            } catch (IOException ex) {
                Logger.getLogger(D88SShare.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public String getConfig(String keyString){
        if(this.sConfig == null){
            return "";
        }
        else{
            if(this.sConfig.containsKey(keyString)){
                return this.sConfig.getProperty(keyString);
            }
            else{
                return "";
            }
        }
    }
    public int GetIntConfig(String keyString){
        if(this.sConfig == null){
            return 0;
        }
        else{
            if(this.sConfig.containsKey(keyString)){
                return Integer.parseInt(this.sConfig.getProperty(keyString));
            }
            else{
                return 0;
            }
        }
    }
    
    
}
