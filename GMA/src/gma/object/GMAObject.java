/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gma.object;

import d88.core.object.D88SObject;
import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 *
 * @author dungnt
 */
public class GMAObject extends D88SObject{

    public int GMA_CURRENT_OBJECT_VERSION = 1;
    
    public GMAObject(String _cmd) {
        super(_cmd);
        this.setObjType(OBJTYPE.USER);
        this.setObjForm(OBJFORM.SERVER);
        this.setObjVer(GMA_CURRENT_OBJECT_VERSION);
    }

    public GMAObject(OBJTYPE objtype,String _cmd) {
        super(_cmd);
        this.setObjType(objtype);
        this.setObjForm(OBJFORM.SERVER);
    }
    
    
    public GMAObject(byte[] d88Message) throws IOException, DataFormatException, Exception {
        super(d88Message);
    }
    
    
    
}
