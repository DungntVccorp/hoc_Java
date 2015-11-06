/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep5_data_structure;

/**
 *
 * @author nguyendung
 */
public class EP5_DATA_STRUCTURE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EP_BITSET ns = new EP_BITSET();
        System.out.println(ns.bits1.get(2));
        EP_VECTOR evt = new EP_VECTOR();
        System.out.println(evt.vt.elementAt(3));
        EP_MAP em = new EP_MAP();
        if (em.m.containsKey("Dung")){
            System.out.println(em.m.get("Dung"));
        }
    }
    
}
