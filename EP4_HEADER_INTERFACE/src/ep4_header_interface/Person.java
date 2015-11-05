/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep4_header_interface;

/**
 *
 * @author nguyendung
 */
public class Person implements PersonHeader {
    public String name = "Nguyen Tien Dung";
    
    @Override
    public void getFullInfo() {
        System.out.println(this.name);
    }

    @Override
    public void getJobInfo() {
        
    }

    
    
}
