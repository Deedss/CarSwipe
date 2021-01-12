package com.example.vroomrr.ui.swipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;

public class SwipeFragment extends Fragment implements ServerCallback {
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_swipe, container, false);

        setupView();

        return root;
    }

    public void setupView() {

    }

    @Override
    public void completionHandler(String object, String url) {
    }
}