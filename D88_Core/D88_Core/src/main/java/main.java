
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.BitSet;

public class main {

    public static BitSet fromByteArray(byte[] bytes) {
        BitSet bits = new BitSet();
        for (int i = 0; i < bytes.length * 8; i++) {
            if ((bytes[bytes.length - i / 8 - 1] & (1 << (i % 8))) > 0) {
                bits.set(i);
            }
        }
        return bits;
    }

    public static byte[] intToBytes(final int i) {
        ByteBuffer bb = ByteBuffer.allocate(3);
        bb.putInt(i);
        return bb.array();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static byte[] intToByteArray(int value) {
        return new byte[]{
            (byte) (value >>> 24),
            (byte) (value >>> 16),
            (byte) (value >>> 8),
            (byte) value};
    }

    public static String toBinary(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for (int i = 0; i < Byte.SIZE * bytes.length; i++) {
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Hello word");
//        String s2 = String.format("%2s", Integer.toBinaryString(3)).replace(' ', '0'); // 2 bit
//        String s3 = String.format("%2s", Integer.toBinaryString(3)).replace(' ', '0'); // 2 bit
//        String s4 = String.format("%8s", Integer.toBinaryString(255)).replace(' ', '0'); // 8 bit
//        String s5 = String.format("%12s", Integer.toBinaryString(4095)).replace(' ', '0'); // 12 bit
//
//        String ss = s4 + s5 + s3 + s2;
//        char[] toCharArray = ss.toCharArray();
//        byte[] bytes = new byte[3];
//        for (int i = 0; i < toCharArray.length; i++) {
//            char u = toCharArray[i];
//
//            System.out.println(i / 8);
//
        byte[] intToBytes = hexStringToByteArray("FFFFFF");
        System.out.println(intToBytes.length);
        System.out.println(toBinary(intToBytes));
        System.out.println(Integer.bitCount(16777215));
        System.out.println(Integer.toBinaryString(16777215));
        int foo = Integer.parseInt("000000000001", 2);
        System.out.println(foo);
        String toBinary = toBinary(intToBytes);
        String substring = toBinary.substring(toBinary.length() - 2, toBinary.length());
        int foo2 = Integer.parseInt(substring, 2);
        System.out.println(foo2);
        String substring2 = toBinary.substring(toBinary.length() - 24, toBinary.length() - 12);
        int foo3 = Integer.parseInt(substring2, 2);
        System.out.println(foo3);
    }

}
