
import java.util.Arrays;
import xyz.d88.core.Object.D88Object;



public class core {
    public static void main(String[] args) throws Exception {
        D88Object d88Object = new D88Object("hallo", "1234");
        d88Object.setBooleansForKey(new boolean[] {true,false,true}, "booleanKey");
        d88Object.setIntegersForKey(new int[] {1,2,3,4}, "intskey");
        d88Object.setDoublesForKey(new double[] {1,2,3,4}, "double_key");
        d88Object.setStringsForKey(new String[] {"a","b","c"}, "String_Key");
        
        byte[] toD88Message = d88Object.toD88Message();
        D88Object d88Object1 = new D88Object(toD88Message);
        System.out.println(d88Object1.getCmd());
        System.out.println(d88Object1.getAppID());
        System.out.println(Arrays.toString(d88Object1.getBooleansForKey("booleanKey")));
        System.out.println(Arrays.toString(d88Object1.getIntegersForKey("intskey")));
        System.out.println(Arrays.toString(d88Object1.getDoublesForKey("double_key")));
        if(d88Object1.containsKey("String_Key")){
            System.out.println(Arrays.toString(d88Object1.getStringsForKey("String_Key")));        
        }
        System.out.println(Arrays.toString(d88Object1.getKeys()));
        
    }
}
