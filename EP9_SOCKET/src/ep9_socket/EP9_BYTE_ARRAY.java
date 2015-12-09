/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 *
 * @author dungnt
 */
public class EP9_BYTE_ARRAY implements Runnable {

    private Socket client = null;
    private PrintWriter out = null;
    private DataInputStream dIn = null;
    private BufferedReader in = null;
    private boolean online = true;

    EP9_BYTE_ARRAY(Socket accept) {
        try {
            this.client = accept;
            out = new PrintWriter(this.client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            dIn = new DataInputStream(this.client.getInputStream());
            System.out.println("Thread Create");
        } catch (IOException ex) {
            System.out.println("Thread Create ERROR");
            Logger.getLogger(EP9_BYTE_ARRAY.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        System.out.println("Compressed: " + output.length + " Byte");
        return output;
    }

    @Override
    public void run() {
        try {
            while (online) {
                byte buffer[] = new byte[1024 * 512];
                int s = dIn.read(buffer);
                if (s != -1) {
                    System.out.println("Original: " + s + " Byte");
                    // giải mã -> giải nén 
                    ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
                    baos1.write(buffer, 0, s);
                    //giai ma
                    byte[] decrypt = EP9_AESencrp.decrypt(baos1.toByteArray());
                    System.out.println("decrypt "+decrypt.length);
                    
                    byte bufferDecom[] = decompress(decrypt);
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    baos.write(bufferDecom, 0, bufferDecom.length);
//                    byte result[] = baos.toByteArray();
                    System.out.println(new String(bufferDecom));
                    
                    //Gson gson = new Gson();
                    
                    
                    
                    online = false;
                }
//                System.out.println("Thread Start");
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                byte buffer[] = new byte[1024];
//                for (int s; (s = dIn.read(buffer)) != -1;) {
//                    baos.write(buffer, 0, s);
//                }
//                byte result[] = baos.toByteArray();
//                System.out.println("result " + result.length);
//                System.out.println(new String(result));
            }
            if (!online) {
                client.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(EP9_BYTE_ARRAY.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataFormatException ex) {
            Logger.getLogger(EP9_BYTE_ARRAY.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EP9_BYTE_ARRAY.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
