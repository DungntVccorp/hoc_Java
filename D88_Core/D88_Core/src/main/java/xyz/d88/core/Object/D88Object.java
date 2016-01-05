package xyz.d88.core.Object;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.zip.DataFormatException;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.d88.core.Common.D88Crypto;
import xyz.d88.core.Common.D88Ziper;

public class D88Object {

    private String cmd = "";
    private String appID = "";

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
    public D88Object(String _cmd,String _appid) {
        this.cmd = _cmd;
        this.appID = _appid;
        if (this.properties == null) {
            this.properties = new HashMap<>();
            this.properties.put("cmd", _cmd);
            this.properties.put("appID", _appid);
        }
    }

    public D88Object(byte[] d88Message) throws IOException, DataFormatException, Exception {
        if (this.properties == null) {
            this.properties = new HashMap<>();
        }
        // chuyển từ mảng byte về  object
        // Step 1 UNZIP
        System.out.println(Arrays.toString(d88Message));
        byte[] dataUnzip = D88Ziper.d88Decompress(d88Message);
        // Step 2 UNCRYPT
        byte[] d88Decrypt = D88Crypto.d88Decrypt(dataUnzip);
        // Step 3 JSON STRING
        String json = new String(d88Decrypt);
        // step 4 JSON MODEL
        JSONObject jsonModel = new JSONObject(json);
        // step 5 hash map
        this.properties = this.toHashMap(jsonModel);
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
            }
            else if ("appID".equals(key)) {
                maptemp.put(key, json.getString(key));
                this.appID = json.getString(key);
            }

        }
        return maptemp;
    }

    public byte[] toD88Message() throws Exception {
        // STEP 1 to JSON OBJ
        JSONObject jsonOBJ = new JSONObject(this.properties);
        // STEP 2 to JSON STRING -> ZIP -> ENCRYPT -> BYTE
        String json = jsonOBJ.toString();
        // STEP 3 to byte Array
        byte[] bytes = json.getBytes();
        // STEP 4 ENCRYPT
        byte[] enCryptByte = D88Crypto.d88Encrypt(bytes);
        // STEP 5 ZIP
        byte[] zip = D88Ziper.d88Compress(enCryptByte);
        
        return zip;
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

}
