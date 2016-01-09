package d88server.core.object;

import d88server.core.common.D88Ziper;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.zip.DataFormatException;
import org.json.JSONArray;
import org.json.JSONObject;

//                 DATA SEND AND RECEIVE
//                 +---------+--------+----------+----------+------------+
//                 |  TYPE   | DEVICE |   APPID  |   VER    |    DATA    |
//                 +---------+--------+----------+----------+------------+
//                 |    2    |   2    |    12    |    8     |   x - 24   | bit
//                 +---------+--------+----------+----------+------------+

//                 +----------+-------------------------------------------+
//                 |   TYPE   |                DESCRIPTION                |
//                 +----------+-------------------------------------------+
//                 |    0     | user                                      |
//                 +----------+-------------------------------------------+
//                 |    1     | request                                   |
//                 +----------+-------------------------------------------+
//                 |    2     | Chat                                      |
//                 +----------+-------------------------------------------+
//                 |    3     | OTHER                                     |
//                 +----------+-------------------------------------------+
//                 |  DEVICE  | Mã ngôn ngữ tạo ra model này              |
//                 +----------+-------------------------------------------+
//                 |    0     |   Create by Server                        |
//                 +----------+-------------------------------------------+
//                 |    1     |   JAVA                                    |
//                 +----------+-------------------------------------------+
//                 |    2     |   SWIFT , OBJECTIVE C                     |
//                 +----------+-------------------------------------------+
//                 |    3     |   .NET                                    |
//                 +----------+-------------------------------------------+
//                 +----------+-------------------------------------------+
//                 |  APPID   | ID Của App Giao Tiếp Với Server           |
//                 +----------+-------------------------------------------+
//                 |   VER    | VER Của OBJ để quản lý hàm theo version   |
//                 +----------+-------------------------------------------+
//                 |   DATA   | Quản lý dữ liệu của obj theo gzip         |
//                 +----------+-------------------------------------------+
//                 DATA OF OBJECT
//                 +------------------+------------------------------------------------------------------------------+
//                 |       BOOL       | Boolean value                                      |            o_           |
//                 +------------------+------------------------------------------------------------------------------+
//                 |    ARRAY BOOL    | Array Boolean value                                |            o_           |
//                 +------------------+------------------------------------------------------------------------------+
//                 |      DOUBLE      | Double  value                                      |            d_           |
//                 +------------------+------------------------------------------------------------------------------+
//                 |   ARRAY DOUBLE   | Array Double value                                 |            d_           |
//                 +------------------+------------------------------------------------------------------------------+
//                 |      STRING      | String  value                                      |            s_           |
//                 +------------------+------------------------------------------------------------------------------+
//                 |   ARRAY STRING   | Array String value                                 |            s_           |
//                 +------------------+------------------------------------------------------------------------------+
//                 |     Interger     | int  value                                         |            i_           |
//                 +------------------+------------------------------------------------------------------------------+
//                 |   ARRAY Interer  | Array Interger value                               |            i_           |
//                 +------------------+------------------------------------------------------------------------------+

public class D88Object {

    private String cmd = "";
    private String appID = "";

    private int objType = 3;
    private int objForm = 0; // SERVER CREATE
    private int objAppID = 0;
    private int objVer = 1;

    private HashMap<String, Object> properties = null;
    private static final String prefix_String = "s_"; // String
    private static final String prefix_Integer = "i_"; // Interger
    private static final String prefix_Double = "d_"; // Double
    private static final String prefix_Boolean = "o_";  // Boolean
    // CONSTRUCTOR

    public D88Object() {
        if (this.properties == null) {
            this.properties = new HashMap<>();
        }
    }

    public D88Object(String _cmd) {
        this.cmd = _cmd;
        if (this.properties == null) {
            this.properties = new HashMap<>();
            this.properties.put("cmd", _cmd);
        }
    }

    public D88Object(String _cmd, String _appid) {
        this.cmd = _cmd;
        this.appID = _appid;
        if (this.properties == null) {
            this.properties = new HashMap<>();
            this.properties.put("cmd", _cmd);
            this.properties.put("appID", _appid);
        }
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public byte[] onCreateObjectInfo() {
        String objTypeString = String.format("%2s", Integer.toBinaryString(this.objType)).replace(' ', '0');
        String objFormString = String.format("%2s", Integer.toBinaryString(this.objForm)).replace(' ', '0');
        String objAppIDString = String.format("%12s", Integer.toBinaryString(this.objAppID)).replace(' ', '0');
        String objVerString = String.format("%8s", Integer.toBinaryString(this.objVer)).replace(' ', '0');
        String info = objVerString + objAppIDString + objFormString + objTypeString;
        System.out.println(toBinary(new BigInteger(info, 2).toByteArray()));
        return new BigInteger(info, 2).toByteArray();
    }
    public void onRetoreInfo(byte[] info){
        String toBinary = toBinary(info);
        this.objType = Integer.parseInt(toBinary.substring(toBinary.length() - 2, toBinary.length()), 2);
        this.objForm = Integer.parseInt(toBinary.substring(toBinary.length() - 4, toBinary.length() - 2), 2);
        this.objAppID = Integer.parseInt(toBinary.substring(toBinary.length() - 16, toBinary.length() - 4), 2);
        this.objVer = Integer.parseInt(toBinary.substring(toBinary.length() - 24, toBinary.length() - 16), 2);
    }

    

    private HashMap<String, Object> toHashMap(JSONObject json) {
        HashMap<String, Object> maptemp = new HashMap<>();

        for (String key : json.keySet()) {
            if (key.startsWith(prefix_String)) {
                if (json.get(key) instanceof JSONArray) {
                    JSONArray jsonArray = json.getJSONArray(key);
                    String[] list = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        list[i] = jsonArray.getString(i);
                    }
                    maptemp.put(key, list);
                } else {
                    maptemp.put(key, json.getString(key));
                }
            } else if (key.startsWith(prefix_Integer)) {
                if (json.get(key) instanceof JSONArray) {
                    JSONArray jsonArray = json.getJSONArray(key);
                    int[] list = new int[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        list[i] = jsonArray.getInt(i);

                    }
                    maptemp.put(key, list);
                } else {
                    maptemp.put(key, json.getInt(key));
                }
            } else if (key.startsWith(prefix_Double)) {
                if (json.get(key) instanceof JSONArray) {
                    JSONArray jsonArray = json.getJSONArray(key);
                    double[] list = new double[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        list[i] = jsonArray.getDouble(i);
                    }
                    maptemp.put(key, list);
                } else {
                    maptemp.put(key, json.getDouble(key));
                }
            } else if (key.startsWith(prefix_Boolean)) {
                if (json.get(key) instanceof JSONArray) {
                    JSONArray jsonArray = json.getJSONArray(key);
                    boolean[] list = new boolean[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        list[i] = jsonArray.getBoolean(i);
                    }
                    maptemp.put(key, list);
                } else {
                    maptemp.put(key, json.getBoolean(key));
                }
            } else if ("cmd".equals(key)) {
                maptemp.put(key, json.getString(key));
                this.cmd = json.getString(key);
            } else if ("appID".equals(key)) {
                maptemp.put(key, json.getString(key));
                this.appID = json.getString(key);
            }

        }
        return maptemp;
    }

    public byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
    
    public byte[] getMessage() throws Exception {
        // STEP 1 to JSON OBJ
        JSONObject jsonOBJ = new JSONObject(this.properties);
        System.out.println(jsonOBJ.toString());
        // STEP 2 to JSON STRING AND GZIP
        byte[] zip = D88Ziper.d88Compress(jsonOBJ.toString());
        // STEP 3 APPEN INFO
        return concatenateByteArrays(zip, onCreateObjectInfo());
    }
    public static String toBinary(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for (int i = 0; i < Byte.SIZE * bytes.length; i++) {
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        }
        return sb.toString();
    }
    public D88Object(byte[] d88Message) throws IOException, DataFormatException, Exception {
        if (this.properties == null) {
            this.properties = new HashMap<>();
        }
        // kiểm tra xem message có hơp lệ không > 3  byte
        if (d88Message.length >= 3) {
            // lấy ra 3 byte cuối để xác định info 
            // những file còn lại là file rawdata cần unzip
            byte[] dataInfo = Arrays.copyOfRange(d88Message, d88Message.length - 3, d88Message.length);
            this.onRetoreInfo(dataInfo);
            byte[] rawData = Arrays.copyOfRange(d88Message, 0, d88Message.length - 3);
            String d88Decompress = D88Ziper.d88Decompress(rawData);
            System.out.println(d88Decompress);
            JSONObject jsonModel = new JSONObject(d88Decompress);
            this.properties = this.toHashMap(jsonModel);
        }
    }
    

    public boolean containsKey(String key) {
        if (this.properties.containsKey(prefix_String + key)) {
            return true;
        } else if (this.properties.containsKey(prefix_Integer + key)) {
            return true;
        } else if (this.properties.containsKey(prefix_Double + key)) {
            return true;
        } else {
            return this.properties.containsKey(prefix_Boolean + key);
        }
    }

    public String[] getKeys() {
        String[] keys = new String[this.properties.keySet().size()];
        int t = 0;
        for (String key : this.properties.keySet()) {
            keys[t] = key.replace(prefix_String, "").replace(prefix_Integer, "").replace(prefix_Double, "").replace(prefix_Boolean, "");
            t += 1;
        }
        return keys;
    }

    // SET GET
    public String getCmd() {
        return (String) this.properties.get("cmd");
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
        this.properties.put("cmd", cmd);
    }

    public String getAppID() {
        return (String) this.properties.get("appID");
    }

    public void setAppID(String appID) {
        this.appID = appID;
        this.properties.put("appID", appID);
    }

    public void setStringForKey(String value, String key) {
        this.properties.put(prefix_String + key, value);
    }

    public String getStringForKey(String key) {
        return (String) this.properties.get(prefix_String + key);
    }

    public void setStringsForKey(String[] value, String key) {
        this.properties.put(prefix_String + key, value);
    }

    public String[] getStringsForKey(String key) {
        return (String[]) this.properties.get(prefix_String + key);
    }

    //Integer
    public void setIntegerForKey(int value, String key) {
        this.properties.put(prefix_Integer + key, value);
    }

    public int getIntegerForKey(String key) {
        return (int) this.properties.get(prefix_Integer + key);
    }

    public void setIntegersForKey(int[] value, String key) {
        this.properties.put(prefix_Integer + key, value);
    }

    public int[] getIntegersForKey(String key) {
        return (int[]) this.properties.get(prefix_Integer + key);
    }

    //Double
    public void setDoubleForKey(double value, String key) {
        this.properties.put(prefix_Double + key, value);

    }

    public double getDoubleForKey(String key) {
        return (double) this.properties.get(prefix_Double + key);
    }

    public void setDoublesForKey(double[] value, String key) {
        this.properties.put(prefix_Double + key, value);
    }

    public double[] getDoublesForKey(String key) {
        return (double[]) this.properties.get(prefix_Double + key);
    }

    //bool
    public void setBooleanForKey(boolean value, String key) {
        this.properties.put(prefix_Boolean + key, value);
    }

    public boolean getBooleanForKey(String key) {
        return (boolean) this.properties.get(prefix_Boolean + key);
    }

    public void setBooleansForKey(boolean[] value, String key) {
        this.properties.put(prefix_Boolean + key, value);
    }

    public boolean[] getBooleansForKey(String key) {
        return (boolean[]) this.properties.get(prefix_Boolean + key);
    }

    public int getObjType() {
        return objType;
    }

    public int getObjForm() {
        return objForm;
    }

    public int getObjAppID() {
        return objAppID;
    }

    public int getObjVer() {
        return objVer;
    }
}
