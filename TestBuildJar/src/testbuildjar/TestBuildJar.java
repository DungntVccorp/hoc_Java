/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbuildjar;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.log4j.Logger;
/**
 *
 * @author nguyendung
 */
public class TestBuildJar{
    
	
	public static void main(String[] args) {
            try {
                Properties prop = new Properties();
                InputStream input = null;
                input = new FileInputStream("log4j.properties");
                prop.load(input);
                System.out.println(prop.getProperty("HOSTNAME"));
                
            } catch (FileNotFoundException ex) {
                java.util.logging.Logger.getLogger(TestBuildJar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(TestBuildJar.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
	
}
