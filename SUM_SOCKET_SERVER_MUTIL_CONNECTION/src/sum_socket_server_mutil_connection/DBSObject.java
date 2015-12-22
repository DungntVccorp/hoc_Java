/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sum_socket_server_mutil_connection;

/**
 *
 * @author dungnt
 *  REV -> OBJ Byte Array -> UNZIP -> UNCRYPT -> JSON -> OBJ MODEL
 *  SEND -> OBJ MODEL -> JSON -> ENCRYPT -> ZIP -> BYTE ARRAY -> SEND 
 *  Cẩn 2 Hàm là lấy về array byte từ obj và ngược lại
 */
public class DBSObject {
    private Integer DBSType;
    private String CMD;
    private byte[] dataRaw;
    private Integer DBSAppId;
    private String senderID;

    public DBSObject() {
    }

    public DBSObject(Integer DBSType, String CMD, byte[] dataRaw, Integer DBSAppId, String senderID) {
        this.DBSType = DBSType;
        this.CMD = CMD;
        this.dataRaw = dataRaw;
        this.DBSAppId = DBSAppId;
        this.senderID = senderID;
    }

    public Integer getDBSType() {
        return DBSType;
    }

    public void setDBSType(Integer DBSType) {
        this.DBSType = DBSType;
    }

    public String getCMD() {
        return CMD;
    }

    public void setCMD(String CMD) {
        this.CMD = CMD;
    }

    public byte[] getDataRaw() {
        return dataRaw;
    }

    public void setDataRaw(byte[] dataRaw) {
        this.dataRaw = dataRaw;
    }

    public Integer getDBSAppId() {
        return DBSAppId;
    }

    public void setDBSAppId(Integer DBSAppId) {
        this.DBSAppId = DBSAppId;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }
   
}
