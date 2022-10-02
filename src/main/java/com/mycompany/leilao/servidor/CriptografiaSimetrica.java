package com.mycompany.leilao.servidor;

import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

public class CriptografiaSimetrica {
    public static final String AES
        = "AES";
 
    // Function to create a secret key
    public static SecretKey createAESKey()
        throws Exception
    {
 
        // Creating a new instance of
        // SecureRandom class.
        SecureRandom securerandom
            = new SecureRandom();
 
        // Passing the string to
        // KeyGenerator
        KeyGenerator keygenerator
            = KeyGenerator.getInstance(AES);
 
        // Initializing the KeyGenerator
        // with 256 bits.
        keygenerator.init(256, securerandom);
        SecretKey key = keygenerator.generateKey();
        return key;
    }
 
    // Driver code
    public static void main(String args[])
        throws Exception
    {
        SecretKey Symmetrickey
            = createAESKey();
        System.out.println("Output");
        System.out.print("The Symmetric Key is :"
                         + DatatypeConverter.printHexBinary(
                               Symmetrickey.getEncoded()));
    }
}
