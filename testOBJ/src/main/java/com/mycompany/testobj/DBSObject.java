package com.mycompany.testobj;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author dungnt REV -> OBJ Byte Array -> UNZIP -> UNCRYPT -> JSON -> OBJ MODEL
 * SEND -> OBJ MODEL -> JSON ->BYTE ARRAY -> ENCRYPT -> ZIP -> SEND
 *
 */
public final class DBSObject {

    private String cmd = null;
    private HashMap<String, Object> data = null;
    private static final String prefix_String = "s_";
    private static final String prefix_Integer = "i_";
    private static final String prefix_Double = "d_";
    private static final String prefix_Boolean = "o_";
    private static final String prefix_Object = "m_";

    public void startOBJ() {
        this.data = new HashMap<>();
        this.data.put("cmd", cmd);
    }

    public DBSObject(String CMD) {
        this.cmd = CMD;
        startOBJ();
    }

    public DBSObject() {
        startOBJ();
    }

    public DBSObject(byte[] data) {
        try {
            this.data = new HashMap<>();
            byte[] decompress = DBSZiper.decompress(data);  // UNZIP
            byte[] decrypt = DBSCrypto.decrypt(decompress); // UNCRYPT
            System.out.println(new String(decrypt));
            JSONObject json = new JSONObject(new String(decrypt));

            this.data = toHashMap(json);

        } catch (IOException ex) {
            Logger.getLogger(DBSObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataFormatException ex) {
            Logger.getLogger(DBSObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DBSObject.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                    Integer[] list = new Integer[jsonArray.length()];
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
                    Double[] list = new Double[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        list[i] = jsonArray.getDouble(i);
                    }
                    maptemp.put(key, list);
                } else {
                    maptemp.put(key, json.getDouble(key));
                }
            } else if (key.startsWith(prefix_Boolean)) {
                maptemp.put(key,json.getBoolean(key));               
            }
            else if ("cmd".equals(key)) {
                maptemp.put(key, json.getString(key));
                this.cmd = json.getString(key);
            }

        }
        return maptemp;
    }

    public String toJSONMessage() {
        JSONObject json = new JSONObject(this.data);
        return json.toString();
    }

    public byte[] toByteMessage() {
        try {
            byte[] bytes = this.toJSONMessage().getBytes(); //OBJ MODEL -> JSON ->BYTE ARRAY
            byte[] encrypt = DBSCrypto.encrypt(bytes); // ENCRYPT
            byte[] compress = DBSZiper.compress(encrypt);  // ZIP
            return compress;
        } catch (IOException ex) {
            Logger.getLogger(DBSObject.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(DBSObject.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    // SET GET
    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    //String
    public void setStringForKey(String value, String key) {
        this.data.put(prefix_String + key, value);
    }

    public String getStringForKey(String key) {
        return (String) this.data.get(prefix_String + key);
    }

    public void setStringsForKey(String[] value, String key) {
        this.data.put(prefix_String + key, value);
    }

    public String[] getStringsForKey(String key) {
        return (String[]) this.data.get(prefix_String + key);
    }

    //Integer
    public void setIntegerForKey(Integer value, String key) {
        this.data.put(prefix_Integer + key, value);
    }

    public Integer getIntegerForKey(String key) {
        return (Integer) this.data.get(prefix_Integer + key);
    }

    public void setIntegersForKey(Integer[] value, String key) {
        this.data.put(prefix_Integer + key, value);
    }

    public Integer[] getIntegersForKey(String key) {
        return (Integer[]) this.data.get(prefix_Integer + key);
    }

    //Double
    public void setDoubleForKey(double value, String key) {
        this.data.put(prefix_Double + key, value);

    }

    public Double getDoubleForKey(String key) {
        return (double) this.data.get(prefix_Double + key);
    }

    public void setDoublesForKey(Double[] value, String key) {
        this.data.put(prefix_Double + key, value);
    }

    public Double[] getDoublesForKey(String key) {
        return (Double[]) this.data.get(prefix_Double + key);
    }

    //bool
    public void setBooleanForKey(Boolean value, String key) {
        this.data.put(prefix_Boolean + key, value);
    }

    public Boolean getBooleanForKey(String key) {
        return (Boolean) this.data.get(prefix_Boolean + key);
    }
    //OBJ
    public void setDBSObjectForKey(DBSObject value, String key) {
        this.data.put(prefix_Object + key, value);
    }

    public DBSObject getDBSObjectForKey(String key) {
        return (DBSObject) this.data.get(prefix_Object + key);
    }

    public void setDBSObjectsForKey(DBSObject[] value, String key) {
        this.data.put(prefix_Object + key, value);
    }

    public DBSObject[] getDBSObjectsForKey(String key) {
        return (DBSObject[]) this.data.get(prefix_Object + key);
    }
}

// Chưa chuẩn hoá được dữ liệu , vd byte , bit , boolean
// chưa chưa được objject trong object

