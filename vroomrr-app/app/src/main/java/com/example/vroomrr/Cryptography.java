package com.example.vroomrr;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

final public class Cryptography {
    // Variables to use
    final static private int keySize = 2048;

    public Cryptography() {
    }

    /**
     * Generate a new KeyPair when registering a User.
     * @throws Exception
     */
    public static void generateKeyPair(String alias) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);

        if (!keyStore.containsAlias(alias)) {
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
    }

    /**
     * Encrypt a String with the Public RSA Key
     * @param data The String data to encrypt.
     * @return returns the encrypted String.
     * @throws Exception Throws Exceptions for Cipher.
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(data.getBytes());
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * Decrypt a given String with Private RSA Key from AndroidKeyStore.
     * @param data the String to decrypt.
     * @return Returns decrypted String
     * @throws Exception Throws Exceptions for Cipher.
     */
    public static String decrypt(String data, String alias) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(alias));
        byte[] encryptedData = Base64.decode(data, Base64.DEFAULT);
        byte[] decodedData = cipher.doFinal(encryptedData);
        return new String(decodedData, StandardCharsets.UTF_8); // for UTF-8 encoding;
    }

    /**
     * Get the public Key from Keystore
     * @return PublicKey
     */
    public static PublicKey getPublicKey(String alias) {
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
    private static PrivateKey getPrivateKey(String alias) {
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

    /**
     * Return the SharedPreferences of the application, normally it is stored encrypted hence this function.
     * @param context , the context on where to call the SharedPreferences.
     * @return returns the SharedPreferences object
     */
    public static SharedPreferences getEncryptedSharedPreferences(Context context) {
        // Initialize the SharedPreferences and create Key Specifications.
        SharedPreferences prf = null;
        KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
                MasterKey.DEFAULT_MASTER_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
                .build();

        try {
            // Build a new MasterKey with the specifications.
            MasterKey masterKey = new MasterKey.Builder(context).setKeyGenParameterSpec(spec).build();
            prf = EncryptedSharedPreferences.create(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS, masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return prf;
    }

    /**
     * Insert a new key value pair to the SharedPreferences
     * @param context the Context of the SharedPreferences
     * @param key The key for the SharedPreferences
     * @param value The value to insert
     */
    public static void addToSharedPreferences(Context context, String key, String value){
        SharedPreferences SP = Cryptography.getEncryptedSharedPreferences( context);
        if (!SP.contains(key)){
            SharedPreferences.Editor editor = SP.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    /**
     * Get the value with the specific Key.
     * @param context the Context of the SharedPreferences
     * @param key The key for the SharedPreferences
     * @return The value to return
     */
    public static String getFromSharedPreferences(Context context, String key){
        SharedPreferences SP = Cryptography.getEncryptedSharedPreferences( context);
        if (SP.contains(key)){
            return SP.getString(key, "");
        }
        return "No such key is found";
    }

    /**
     * Update the existing value to a key-pair
     * @param context the Context of the SharedPreferences
     * @param key The key for the SharedPreferences
     * @param value The value to insert
     */
    public static void updateSharedPreferences(Context context, String key, String value){
        SharedPreferences SP = Cryptography.getEncryptedSharedPreferences( context);
        if(SP.contains(key)){
            SharedPreferences.Editor editor = SP.edit();
            editor.remove(key);
            editor.commit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    /**
     * Return PublicKey of a String value
     * @param publStr String to decode
     * @return PublicKey
     */
    public static PublicKey stringToPublicKey(String publStr)  {
        PublicKey publicKey = null;
        try {
            byte[] data = Base64.decode(publStr, Base64.DEFAULT);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            publicKey = fact.generatePublic(spec);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * Return a String from a PublicKey
     * @param publ Public Key to encode
     * @return String
     */
    public static String publicKeyToString(PublicKey publ) {
        String publicKeyString = null;
        try {
            KeyFactory fact = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec spec = fact.getKeySpec(publ,
                    X509EncodedKeySpec.class);
            publicKeyString = Base64.encodeToString(spec.getEncoded(), Base64.DEFAULT);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return publicKeyString;
    }
}