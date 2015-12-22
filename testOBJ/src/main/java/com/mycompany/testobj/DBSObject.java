package com.mycompany.testobj;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
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
            byte[] decompress = DBSObject.decompress(data);  // UNZIP
            byte[] decrypt = DBSCrypto.decrypt(decompress); // UNCRYPT
            JSONObject json = new JSONObject(new String(decrypt));
            this.data = (HashMap<String, Object>) toMap(json);

        } catch (IOException ex) {
            Logger.getLogger(DBSObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataFormatException ex) {
            Logger.getLogger(DBSObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DBSObject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Object[] toList(JSONArray array) throws JSONException {
        Object[] list = null;
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                if(list == null){
                    list = new JSONArray[array.length()];
                }
                value = toList((JSONArray) value);
                list[i] = value;
            } else if (value instanceof JSONObject) {
                if(list == null){
                    list = new JSONObject[array.length()];
                }
                value = toMap((JSONObject) value);
                list[i] = value;
            } else if (value instanceof String) {
                if(list == null){
                    list = new String[array.length()];
                }
                list[i] = (String)value;
            }
            else if (value instanceof Integer) {
                if(list == null){
                    list = new Integer[array.length()];
                }
                list[i] = (Integer)value;
            }
            else if (value instanceof Float) {
                if(list == null){
                    list = new Float[array.length()];
                }
                list[i] = (Float)value;
            }
            else if (value instanceof Double) {
                if(list == null){
                    list = new Double[array.length()];
                }
                list[i] = (Double)value;
            }
            else if (value instanceof Byte) {
                if(list == null){
                    list = new Byte[array.length()];
                }
                list[i] = (Byte)value;
            }
            else if (value instanceof Character) {
                if(list == null){
                    list = new Character[array.length()];
                }
                list[i] = (Character)value;
            }
            else if (value instanceof Boolean) {
                if(list == null){
                    list = new Boolean[array.length()];
                }
                list[i] = (Boolean)value;
            }
            else if (value instanceof Short) {
                if(list == null){
                    list = new Short[array.length()];
                }
                list[i] = (Short)value;
            }
            
            
        }
        return list;
    }

    public static HashMap<String, Object> toMap(JSONObject object) throws JSONException {
        HashMap<String, Object> map = new HashMap<>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static byte[] compress(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        deflater.finish();
        byte[] buffer = new byte[data.length];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        byte[] output = outputStream.toByteArray();

        return output;
    }

    public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        byte[] output = outputStream.toByteArray();

        return output;
    }

    public String toJSONMessage() {
        JSONObject json = new JSONObject(this.data);
        return json.toString();
    }

    public byte[] toByteMessage() {
        try {
            byte[] bytes = this.toJSONMessage().getBytes(); //OBJ MODEL -> JSON ->BYTE ARRAY
            byte[] encrypt = DBSCrypto.encrypt(bytes); // ENCRYPT
            byte[] compress = compress(encrypt);  // ZIP
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
    public void setStringForKey(String value, String key) {
        this.data.put(key, value);
    }

    public String getStringForKey(String key) {
        return (String) this.data.get(key);
    }

    public void setStringsForKey(String[] value, String key) {
        this.data.put(key, value);
    }
    public String[] getStringsForKey(String key) {
        return (String[]) this.data.get(key);
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

}
