/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep9_socket_client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author dungnt
 */
public class EP9_SOCKET_CLIENT {

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

        System.out.println("Original: " + data.length);
        System.out.println("Compressed: " + output.length);
        return output;
    }

    public static void main(String[] args) throws Exception {
        Socket socket = null;
        try {

            socket = new Socket("localhost", 1234);
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
           

            byte[] message = "{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}1{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}1{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}1{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}1{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}1{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}1{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}1{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}1{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}1{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}1{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}{\"asd\": \"as\"\"test1\": \"a\"}1".getBytes();
            System.out.println("message leng" + message.length);
            System.out.println(compress(message).length);
            // nén file > mã hoá
            byte[] encrypt = EP9_AESencrp.encrypt(compress(message));
            System.out.println("encrypt "+encrypt.length);
            dOut.write(encrypt);

        } catch (IOException ex) {
            Logger.getLogger(EP9_SOCKET_CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
//        SOCKET_CLIENT sClient = new SOCKET_CLIENT("localhost", 1234);
//        sClient.startClient();
//        byte[] message = "Dungnt".getBytes();

    }

}
