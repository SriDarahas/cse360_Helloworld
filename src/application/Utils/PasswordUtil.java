package application.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class PasswordUtil {
    public static byte[] hashPassword(String password, byte[] salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        return md.digest(password.getBytes("UTF-8"));
    }

    public static byte[] generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
