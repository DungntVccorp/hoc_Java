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
public class EP4_HEADER_INTERFACE {

    /**
     * @param args the command line arguments
     * không cho mình phương thức có thể không Override , chưa được linh hoạt lắm vì nhiều lúc cũng chẳng cần dùng tới nó 
     */
    public static void main(String[] args) {
        Employee er = new Employee();
        er.getFullInfo();
        er.getJobInfo();
    }
    
}
