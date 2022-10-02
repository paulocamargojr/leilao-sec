package com.mycompany.leilao.compartilhado;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Scanner;
import javax.crypto.Cipher;

public class CriptografiaAssimetrica {

    private static final String RSA
            = "RSA";
    private static Scanner sc;

    public static KeyPair generateRSAKkeyPair()
            throws Exception {
        SecureRandom secureRandom
                = new SecureRandom();
        KeyPairGenerator keyPairGenerator
                = KeyPairGenerator.getInstance(RSA);

        keyPairGenerator.initialize(
                2048, secureRandom);
        return keyPairGenerator
                .generateKeyPair();
    }

    public static byte[] do_RSAEncryption(
            String plainText,
            PublicKey publicKey)
            throws Exception {
        Cipher cipher
                = Cipher.getInstance(RSA);

        cipher.init(
                Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(
                plainText.getBytes());
    }

    public static String do_RSADecryption(
            byte[] cipherText,
            PrivateKey privateKey)
            throws Exception {
        Cipher cipher
                = Cipher.getInstance(RSA);

        cipher.init(Cipher.DECRYPT_MODE,
                privateKey);
        byte[] result
                = cipher.doFinal(cipherText);

        return new String(result);
    }
}
