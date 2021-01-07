package com.example.vroomrr;

import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ProcessLifecycleOwner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

final public class Cryptography {
    // Variables to use
    final static private int keySize = 2048;
    final static private String alias = "VroomrrKey";

    public Cryptography() {
    }

    /**
     * Generate a new KeyPair when registering a User.
     * @throws Exception
     */
    public static void generateKeyPair() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);

        if(!keyStore.containsAlias(alias)){
            // Generate KeyPairGenerator with a 2048 bit integer.
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore");

            kpg.initialize(new KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_DECRYPT | KeyProperties.PURPOSE_ENCRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                    .setKeySize(keySize)
                    .build());

            kpg.generateKeyPair();
        } else {
            Log.d("generateKeyPair:", "Key already exists");
        }

        //todo Remove Testing function
        printKeysForTesting();
    }

    /**
     * TESTING FUNCTION ONLY
     * @throws Exception
     */
    private static void printKeysForTesting() throws Exception{
        String test = "Hello World";
        String encrypted = encrypt(test);
        Log.d("TestMessage Encrypted: ", encrypted);
        String decrypted = decrypt(encrypted);
        Log.d("TestMessage Decrypted: ", decrypted);
        Log.d("Finished", "Finished");
    }

    /**
     * Encrypt a String with the Public RSA Key
     * @param data The String data to encrypt.
     * @return returns the encrypted String.
     * @throws Exception Throws Exceptions for Cipher.
     */
    public static String encrypt(String data) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        //TODO get Public Key from server
//        cipher.init(Cipher.ENCRYPT_MODE, ServerConnection.getPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
        byte[] bytes = cipher.doFinal(data.getBytes());
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * Decrypt a given String with Private RSA Key from AndroidKeyStore.
     * @param data the String to decrypt.
     * @return Returns decrypted String
     * @throws Exception Throws Exceptions for Cipher.
     */
    public static String decrypt(String data) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
        byte[] encryptedData = Base64.decode(data, Base64.DEFAULT);
        byte[] decodedData = cipher.doFinal(encryptedData);
        return new String(decodedData, StandardCharsets.UTF_8); // for UTF-8 encoding;
    }


    /**
     * Get the public Key from Keystore
     * @return PublicKey
     */
    public static PublicKey getPublicKey() {
        KeyStore keyStore;
        try {
            // Open KeyStore and get Entry
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            KeyStore.Entry entry = keyStore.getEntry(alias, null);
            return ((KeyStore.PrivateKeyEntry) entry).getCertificate().getPublicKey();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // if fail return null
        return null;
    }

    /**
     * Get Private Key from AndroidKeyStore.
     * @return Returns Key from AndroidKeyStore.
     */
    private static PrivateKey getPrivateKey() {
        KeyStore keyStore;
        try {
            // Open KeyStore and get Entry
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            KeyStore.Entry entry = keyStore.getEntry(alias, null);
            return ((KeyStore.PrivateKeyEntry) entry).getPrivateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // if fail return null
        return null;
    }
}
