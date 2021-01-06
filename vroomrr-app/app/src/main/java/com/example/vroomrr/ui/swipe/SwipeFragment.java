package com.example.vroomrr.ui.swipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vroomrr.R;
import com.example.vroomrr.ServerConnection;

public class SwipeFragment extends Fragment {
    private View root;
    private Button btn;
    private ServerConnection connection;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_login, container, false);

        setupView();
        setOnclickListeners();

        return root;
    }

    public void setupView() {
        connection = new ServerConnection();
        btn = root.findViewById(R.id.button_login);
    }

    public void setOnclickListeners() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}