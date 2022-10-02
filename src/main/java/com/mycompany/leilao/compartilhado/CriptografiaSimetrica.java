package com.mycompany.leilao.compartilhado;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class CriptografiaSimetrica {
    public static final String AES
        = "AES";
 
    public static SecretKey createAESKey()
        throws Exception
    {

        SecureRandom securerandom
            = new SecureRandom();

        KeyGenerator keygenerator
            = KeyGenerator.getInstance(AES);

        keygenerator.init(256, securerandom);
        SecretKey key = keygenerator.generateKey();
        return key;
    }

    public static byte[] createInitializationVector()
    {
 
        byte[] initializationVector
            = new byte[16];
        SecureRandom secureRandom
            = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return initializationVector;
    }

    public static byte[] do_AESEncryption(
        String plainText,
        SecretKey secretKey,
        byte[] initializationVector)
        throws Exception
    {
        Cipher cipher
            = Cipher.getInstance(
                    "AES/CBC/PKCS5Padding");
        
        IvParameterSpec ivParameterSpec
            = new IvParameterSpec(
                initializationVector);
 
        cipher.init(Cipher.ENCRYPT_MODE,
                    secretKey,
                    ivParameterSpec);
 
        return cipher.doFinal(
            plainText.getBytes());
    }

    public static String do_AESDecryption(
        byte[] cipherText,
        SecretKey secretKey,
        byte[] initializationVector)
        throws Exception
    {
        Cipher cipher
            = Cipher.getInstance(
                "AES/CBC/PKCS5Padding");
        
        IvParameterSpec ivParameterSpec
            = new IvParameterSpec(
                initializationVector);
 
        cipher.init(
            Cipher.DECRYPT_MODE,
            secretKey,
            ivParameterSpec);
 
        byte[] result
            = cipher.doFinal(cipherText);
 
        return new String(result);
    }
}
