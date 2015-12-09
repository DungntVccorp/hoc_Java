/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket;

import java.io.Serializable;

public class EP9_MESSAGE implements Serializable {

    private static final long serialVersionUID = 1L;
    private String type = null;

    public EP9_MESSAGE(String type) {
        this.type = type;

    }

    @Override
    public String toString() {
        return "{\"asd\": \"as\"}";
    }
    
    public byte[] toData(){
        byte[] obj = toString().getBytes();
        return obj;
    }
}
