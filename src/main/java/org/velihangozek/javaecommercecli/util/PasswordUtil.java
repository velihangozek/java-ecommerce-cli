package org.velihangozek.javaecommercecli.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private PasswordUtil() {
    }

    public static String hash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }

    private static String bytesToHex(byte[] encodedHash) {
        StringBuilder stringBuilder = new StringBuilder(2 * encodedHash.length);

        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }
}
