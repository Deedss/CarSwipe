package com.example.vroomrr;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import java.io.IOException;
import java.security.GeneralSecurityException;

// THANKS TO Group of SocialReader for this usage of the Encrypted SharedPreferences.
public class GlobalApplication extends Application {
    // global context
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }

    /**
     * Get the SharedPreferences from an encrypted file.
     * @return SharedPreferences.
     */
    public static SharedPreferences getEncryptedSharedPreferences() {
        SharedPreferences prf = null;
        KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
                MasterKey.DEFAULT_MASTER_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
                .build();

        try {
            MasterKey masterKey = new MasterKey.Builder(GlobalApplication.getAppContext()).setKeyGenParameterSpec(spec).build();
            prf = EncryptedSharedPreferences.create(GlobalApplication.getAppContext(), MasterKey.DEFAULT_MASTER_KEY_ALIAS, masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return prf;
    }
}
