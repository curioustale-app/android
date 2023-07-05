package app.curioustale.curioustale.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
    private HashUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String md5(String message) {
        return hash(message);
    }

    private static String hash(String message) {
        try {
            String lower = message.toLowerCase().trim();
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(lower.getBytes(), 0, lower.length());
            BigInteger i = new BigInteger(1, digest.digest());
            return String.format("%1$032x", i);
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(message.hashCode());
        }
    }
}
