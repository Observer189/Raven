package com.example.raven.crypt;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Scanner;

public class Crypting {

    public static String encrypt(String msg, SecretKey key) throws Exception{
        DesEncrypter encrypter = new DesEncrypter(key);
        return encrypter.encrypt(msg);
    }

    public static String decrypt(String msg, SecretKey key) throws Exception{
        DesEncrypter encrypter = new DesEncrypter(key);
        return encrypter.decrypt(msg);
    }
    //SecretKey key = KeyGenerator.getInstance("DES").generateKey();
}
