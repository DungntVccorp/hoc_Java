/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.d88.core.Common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

/**
 *
 * @author dungnt
 */
public class D88Ziper {

    public static byte[] d88Compress(byte[] data) throws Exception {
        byte[] compressed;
        ByteArrayOutputStream os = new ByteArrayOutputStream(data.length);
        GZIPOutputStream gos = new GZIPOutputStream(os);
        gos.write(data);
        gos.close();
        compressed = os.toByteArray();
        return compressed;
    }

    public static byte[] d88Decompress(byte[] data) throws Exception {
        final int BUFFER_SIZE = 32;
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE);
        StringBuilder string = new StringBuilder();
        byte[] _data = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = gis.read(_data)) != -1) {
            string.append(new String(_data, 0, bytesRead));
        }
        gis.close();
        is.close();
        return string.toString().getBytes();
    }
}
