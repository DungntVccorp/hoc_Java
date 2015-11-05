/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep3_extends;

/**
 *
 * @author nguyendung
 */
public class Employee extends Person {
    public String job = "Developer";
    
    @Override
    public void fullInfo(){
        super.fullInfo();
        System.out.println(this.job + "\n");
    }
}
