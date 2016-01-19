/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88.core.common;

/**
 *
 * @author dungnt
 */
public final class D88Constants {
    public static enum OBJTYPE{
        
        USER(0), REQUEST(1), CHAT(2), OTHER(3);
        
        private final int value;
 
        OBJTYPE(int value) {
            this.value = value;
        }
 
        public int getValue() {
            return this.value;
        }
      
    };
    public static enum OBJFORM{
        
        SERVER(0), IOS(1), JAVA(2), OTHER(3);
        
        private final int value;
 
        OBJFORM(int value) {
            this.value = value;
        }
 
        public int getValue() {
            return this.value;
        }
      
    };
    
    
    public static final String prefix_String = "s_"; // String
    public static final String prefix_Integer = "i_"; // Intergerg
    public static final String prefix_Double = "d_"; // Double
    public static final String prefix_Boolean = "o_";  // Boolean
    
    
    
    public static final String CMD_STRING                           = "cmd";
}
