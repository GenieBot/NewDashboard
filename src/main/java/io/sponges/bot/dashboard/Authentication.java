package io.sponges.bot.dashboard;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public final class Authentication {

    private static final Random RANDOM = new SecureRandom();

    public static byte[] salt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    public static byte[] hash(char[] password, byte[] salt, int iterations, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
        SecretKey key = factory.generateSecret(spec);
        return key.getEncoded();
    }

    public static byte[] hash(String password, byte[] salt) {
        try {
            return hash(password.toCharArray(), salt, 100, 512);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return builder.toString();
    }

}
