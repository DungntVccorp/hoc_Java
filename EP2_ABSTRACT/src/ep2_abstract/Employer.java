/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep2_abstract;

/**
 *
 * @author nguyendung
 */
public class Employer extends Employee {

    @Override
    public void getFullDetails() {
        System.out.println(this.name + " " + this.address);
    }
    
}
