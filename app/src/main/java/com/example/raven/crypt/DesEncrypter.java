package com.example.raven.crypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

class DesEncrypter {
    private Cipher ecipher;

    private Cipher dcipher;

    DesEncrypter(SecretKey key) throws Exception {
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }

    String encrypt(String str) throws Exception {
        // Encode the string into bytes using utf-8
        byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);

        // Encrypt
        byte[] enc = ecipher.doFinal(utf8);

        // Encode bytes to base64 to get a string
        return toStr(enc);
    }

    String decrypt(String str) throws Exception {
        // Decode base64 to get bytes
        byte[] dec = fromStr(str);

        byte[] utf8 = dcipher.doFinal(dec);

        // Decode using utf-8
        return new String(utf8, StandardCharsets.UTF_8);
    }

    private static String toStr(byte[] enc){
        char[] chars = new char[enc.length];
        for (int i = 0; i < enc.length; i++) {
            chars[i] = (char) enc[i];
        }
        return new String(chars);
    }

    private static byte[] fromStr(String enc){
        byte[] out = new byte[enc.length()];
        for (int i = 0; i < enc.length(); i++) {
            out[i] = (byte) enc.charAt(i);
        }
        return out;
    }
}