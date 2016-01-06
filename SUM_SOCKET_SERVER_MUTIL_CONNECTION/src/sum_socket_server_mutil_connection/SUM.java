/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sum_socket_server_mutil_connection;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class SUM implements Runnable {

    protected Socket socket = null;
    protected boolean isConnection = true;
    protected BufferedReader din = null;
    protected DataOutputStream dout = null;
    protected String ClientName = null;

    static String IV = "AAAAAAAAAAAAAAAA";
    static String plaintext = "test text 123\0\0\0"; /*Note null padding*/
    static String encryptionKey = "0123456789abcdef";

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String ClientName) {
        this.ClientName = ClientName;
    }

    public static byte[] d88Compress(byte[] data) throws IOException {
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

    public static byte[] compress(byte[] data) throws IOException {
        byte[] compressed;
        try (ByteArrayOutputStream os = new ByteArrayOutputStream(data.length)) {
            GZIPOutputStream gos = new GZIPOutputStream(os);
            gos.write(data);
            gos.close();
            compressed = os.toByteArray();
        }
        return compressed;
    }

    public static String toBinary(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for (int i = 0; i < Byte.SIZE * bytes.length; i++) {
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        }
        return sb.toString();
    }

    public SUM(Socket soc, String uuid) {
        try {
            System.out.println("Client Did Connect");
            AppShare.getInstance().getListClient().add(this);
            this.setClientName(uuid);
            this.socket = soc;
            //this.socket.setSoTimeout(50000);
            this.din = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.dout = new DataOutputStream(this.socket.getOutputStream());
            this.isConnection = true;
            System.out.println(toBinary("dungnt889999999".getBytes("UTF-8")));
            byte[] compress = compress("dungnt889999999".getBytes("UTF-8"));
            System.out.println(compress.length);
            this.dout.write(compress);
            
            
        } catch (IOException ex) {
            this.isConnection = false;
            Logger.getLogger(SUM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SUM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String messageString) {
        try {
            this.dout.write(messageString.getBytes());
        } catch (IOException ex) {
            this.isConnection = false;
            AppShare.getInstance().getListClient().remove(this);
        }
    }

    public void sendMessageToAll(String messString) {
        for (int i = 0; i < AppShare.getInstance().getListClient().size(); i++) {
            if (!AppShare.getInstance().getListClient().get(i).getClientName().equals(this.getClientName())) {
                System.out.println("Send " + AppShare.getInstance().getListClient().get(i).getClientName());
                AppShare.getInstance().getListClient().get(i).sendMessage(messString);
            }
        }
    }

    @Override
    public void run() {
        while (isConnection) {
            if (!this.socket.isConnected()) {
                isConnection = false;
            }
            try {
                String line = din.readLine();
                if (line != null) {
                    if ("Ping".equals(line)) {
                        this.sendMessage("Pong\n");
                    } else {
                        System.out.println(line);
                        this.sendMessageToAll(line);
                    }

                }
            } catch (IOException ex) {
                isConnection = false;
            }
        }

        if (!isConnection) {
            try {
                this.din.close();
                this.dout.close();
                this.socket.close();

                // Gửi tin nhắn cho những người còn lại
                System.out.println("DISCONECT");
                AppShare.getInstance().getListClient().remove(this);
            } catch (IOException ex) {
                Logger.getLogger(SUM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
