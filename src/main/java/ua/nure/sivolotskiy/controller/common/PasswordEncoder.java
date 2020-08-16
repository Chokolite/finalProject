package ua.nure.sivolotskiy.controller.common;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static final String algorithm = "MD5";

    public static String encode(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(password.getBytes());
            byte[] hash = digest.digest();
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}

