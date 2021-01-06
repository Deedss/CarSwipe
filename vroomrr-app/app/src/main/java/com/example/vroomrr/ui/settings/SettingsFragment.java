package com.example.vroomrr.ui.settings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;

import org.json.JSONObject;

public class SettingsFragment extends Fragment implements ServerCallback {
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_settings, container, false);

        login();
        return root;
    }

    // todo AS EXAMPLE On HOW TO CALL POST.
    public void login(){
        String username = "luuk";
        String password = "cum";
        ServerConnection.login(username, password, this);
    }

    @Override
    public void completionHandler(Boolean success, JSONObject object) {
        Log.d("succesfull login:", object.toString());
    }
}