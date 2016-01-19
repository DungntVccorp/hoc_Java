/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88.core.object;

import d88.core.common.D88Constants;
import d88.core.common.D88SCommon;
import d88.core.common.D88SGzip;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.zip.DataFormatException;
import org.json.JSONArray;
import org.json.JSONObject;
import d88.core.common.D88Constants.OBJTYPE;
import d88.core.common.D88Constants.OBJFORM;

/**
 *
 * @author dung.nt
 */

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

public class D88SObject {
    
    
    
    private String cmd = null;
    private OBJTYPE objType = OBJTYPE.USER;
    private OBJFORM objForm = OBJFORM.SERVER; // SERVER CREATE
    private int objAppID = 0;
    private int objVer = 1;

    private HashMap<String, Object> properties = null;
    
    // CONSTRUCTOR
    
    public D88SObject(String _cmd) { // init default with CMD
        this.cmd = _cmd;
        if (this.properties == null) {
            this.properties = new HashMap<>();
            this.properties.put(D88Constants.CMD_STRING, _cmd);
        }
    }

    public D88SObject(OBJTYPE _objType,int _objAppID,int _objVer,String _cmd) {
        this.cmd = _cmd;
        this.objType = _objType;        
        this.objAppID = _objAppID;
        this.objVer = _objVer;
        
        if (this.properties == null) {
            this.properties = new HashMap<>();
            this.properties.put(D88Constants.CMD_STRING, _cmd);
        }
    }
    
    public D88SObject(byte[] d88Message) throws IOException, DataFormatException, Exception {
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
            String d88Decompress = D88SGzip.d88Decompress(rawData);
            JSONObject jsonModel = new JSONObject(d88Decompress);
            this.properties = this.toHashMap(jsonModel);
        }
    }
    
    
    private void onRetoreInfo(byte[] info){
        String toBinary = D88SCommon.toBinary(info);
        int inttype = Integer.parseInt(toBinary.substring(toBinary.length() - 2, toBinary.length()), 2);
        if(inttype == 0){
            this.objType = OBJTYPE.USER;
        }else if(inttype == 1){
            this.objType = OBJTYPE.REQUEST;
        }
        else if(inttype == 2){
            this.objType = OBJTYPE.CHAT;
        }
        else if(inttype == 3){
            this.objType = OBJTYPE.OTHER;
        }
        
        int objFormINT = Integer.parseInt(toBinary.substring(toBinary.length() - 4, toBinary.length() - 2), 2);
        if(objFormINT == 0){
            this.objForm = OBJFORM.SERVER;
        }else if(objFormINT == 1){
            this.objForm = OBJFORM.IOS;
        }
        else if(objFormINT == 2){
            this.objForm = OBJFORM.JAVA;
        }
        else if(objFormINT == 3){
            this.objForm = OBJFORM.OTHER;
        }
        this.objAppID = Integer.parseInt(toBinary.substring(toBinary.length() - 16, toBinary.length() - 4), 2);
        this.objVer = Integer.parseInt(toBinary.substring(toBinary.length() - 24, toBinary.length() - 16), 2);
    }
    private HashMap<String, Object> toHashMap(JSONObject json) {
        HashMap<String, Object> maptemp = new HashMap<>();

        for (String key : json.keySet()) {
            if (key.startsWith(D88Constants.prefix_String)) {
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
            } else if (key.startsWith(D88Constants.prefix_Integer)) {
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
            } else if (key.startsWith(D88Constants.prefix_Double)) {
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
            } else if (key.startsWith(D88Constants.prefix_Boolean)) {
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
            } else if (D88Constants.CMD_STRING.equals(key)) {
                maptemp.put(key, json.getString(key));
                this.cmd = json.getString(key);
            } 

        }
        return maptemp;
    }
    
    
    private byte[] onCreateObjectInfo() {
        String objTypeString = String.format("%2s", Integer.toBinaryString(this.objType.getValue())).replace(' ', '0');
        String objFormString = String.format("%2s", Integer.toBinaryString(this.objForm.getValue())).replace(' ', '0');
        String objAppIDString = String.format("%12s", Integer.toBinaryString(this.objAppID)).replace(' ', '0');
        String objVerString = String.format("%8s", Integer.toBinaryString(this.objVer)).replace(' ', '0');
        String info = objVerString + objAppIDString + objFormString + objTypeString;
        return new BigInteger(info, 2).toByteArray();
    }
    
    public byte[] getMessage() throws Exception {
        JSONObject jsonOBJ = new JSONObject(this.properties);
        byte[] zip = D88SGzip.d88Compress(jsonOBJ.toString());
        return D88SCommon.concatenateByteArrays(zip, onCreateObjectInfo());
    }
    
    
    // OBJECT ACTION
    public boolean containsKey(String key) {
        if (this.properties.containsKey(D88Constants.prefix_String + key)) {
            return true;
        } else if (this.properties.containsKey(D88Constants.prefix_Integer + key)) {
            return true;
        } else if (this.properties.containsKey(D88Constants.prefix_Double + key)) {
            return true;
        } else {
            return this.properties.containsKey(D88Constants.prefix_Boolean + key);
        }
    }

    public String[] getKeys() {
        String[] keys = new String[this.properties.keySet().size()];
        int t = 0;
        for (String key : this.properties.keySet()) {
            keys[t] = key.replace(D88Constants.prefix_String, "").replace(D88Constants.prefix_Integer, "").replace(D88Constants.prefix_Double, "").replace(D88Constants.prefix_Boolean, "");
            t += 1;
        }
        return keys;
    }
    
    public boolean removeObjectForKey(String key){
        if(D88Constants.CMD_STRING.equals(key)){
            return false;
        }
        if(this.containsKey(key)){
            if (this.properties.containsKey(D88Constants.prefix_String + key)) {
                this.properties.remove(D88Constants.prefix_String + key);
            }
            else if (this.properties.containsKey(D88Constants.prefix_Integer + key)) {
                this.properties.remove(D88Constants.prefix_Integer + key);
            }
            else if (this.properties.containsKey(D88Constants.prefix_Double + key)) {
                this.properties.remove(D88Constants.prefix_Double + key);
            }
            else{
                this.properties.remove(D88Constants.prefix_Boolean + key);
            }
            return true;
        }
        return false;
    }

    // SET GET
    public String getCmd() {
        return (String) this.properties.get("cmd");
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
        this.properties.put(D88Constants.CMD_STRING, cmd);
    }
    public void setStringForKey(String value, String key) {
        this.properties.put(D88Constants.prefix_String + key, value);
    }

    public String getStringForKey(String key) {
        return (String) this.properties.get(D88Constants.prefix_String + key);
    }

    public void setStringsForKey(String[] value, String key) {
        this.properties.put(D88Constants.prefix_String + key, value);
    }

    public String[] getStringsForKey(String key) {
        return (String[]) this.properties.get(D88Constants.prefix_String + key);
    }

    //Integer
    public void setIntegerForKey(int value, String key) {
        this.properties.put(D88Constants.prefix_Integer + key, value);
    }

    public int getIntegerForKey(String key) {
        return (int) this.properties.get(D88Constants.prefix_Integer + key);
    }

    public void setIntegersForKey(int[] value, String key) {
        this.properties.put(D88Constants.prefix_Integer + key, value);
    }

    public int[] getIntegersForKey(String key) {
        return (int[]) this.properties.get(D88Constants.prefix_Integer + key);
    }

    //Double
    public void setDoubleForKey(double value, String key) {
        this.properties.put(D88Constants.prefix_Double + key, value);

    }

    public double getDoubleForKey(String key) {
        return (double) this.properties.get(D88Constants.prefix_Double + key);
    }

    public void setDoublesForKey(double[] value, String key) {
        this.properties.put(D88Constants.prefix_Double + key, value);
    }

    public double[] getDoublesForKey(String key) {
        return (double[]) this.properties.get(D88Constants.prefix_Double + key);
    }

    //bool
    public void setBooleanForKey(boolean value, String key) {
        this.properties.put(D88Constants.prefix_Boolean + key, value);
    }

    public boolean getBooleanForKey(String key) {
        return (boolean) this.properties.get(D88Constants.prefix_Boolean + key);
    }

    public void setBooleansForKey(boolean[] value, String key) {
        this.properties.put(D88Constants.prefix_Boolean + key, value);
    }

    public boolean[] getBooleansForKey(String key) {
        return (boolean[]) this.properties.get(D88Constants.prefix_Boolean + key);
    }
    
    // obj param

    public OBJTYPE getObjType() {
        return objType;
    }

    public OBJFORM getObjForm() {
        return objForm;
    }

    public int getObjAppID() {
        return objAppID;
    }

    public int getObjVer() {
        return objVer;
    }

    public void setObjType(OBJTYPE objType) {
        this.objType = objType;
    }

    public void setObjForm(OBJFORM objForm) {
        this.objForm = objForm;
    }

    public void setObjAppID(int objAppID) {
        this.objAppID = objAppID;
    }

    public void setObjVer(int objVer) {
        this.objVer = objVer;
    }
    
}
