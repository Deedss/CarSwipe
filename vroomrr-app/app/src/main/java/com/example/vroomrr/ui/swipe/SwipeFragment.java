package com.example.vroomrr.ui.swipe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;

public class SwipeFragment extends Fragment implements ServerCallback {
    private View root;
    private ServerConnection connection;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_swipe, container, false);

        setupView();

        return root;
    }

    public void setupView() {

    }

    @Override
    public void completionHandler(String object) {
        connection = new ServerConnection();
    }
}