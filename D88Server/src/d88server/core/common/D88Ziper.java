/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d88server.core.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author dungnt
 */
public class D88Ziper {

    public static byte[] d88Compress(String string) throws IOException {
        byte[] compressed;
        try (ByteArrayOutputStream os = new ByteArrayOutputStream(string.length())) {
            GZIPOutputStream gos = new GZIPOutputStream(os);
            gos.write(string.getBytes());
            gos.close();
            compressed = os.toByteArray();
        }
        return compressed;
    }
    public static String d88Decompress(byte[] compressed) throws IOException {
        final int BUFFER_SIZE = 32;
        StringBuilder string;
        try (ByteArrayInputStream is = new ByteArrayInputStream(compressed); GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE)) {
            string = new StringBuilder();
            byte[] data = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = gis.read(data)) != -1) {
                string.append(new String(data, 0, bytesRead));
            }
        }
        return string.toString();
    }
}
