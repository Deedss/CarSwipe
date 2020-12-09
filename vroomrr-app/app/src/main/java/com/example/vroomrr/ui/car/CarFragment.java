package com.example.vroomrr.ui.car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.vroomrr.R;
import com.example.vroomrr.ui.login.LoginViewModel;

public class CarFragment extends Fragment {
    private CarViewModel carViewModel;
    private TextView textView;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_car, container, false);
        carViewModel = new ViewModelProvider(this).get(CarViewModel.class);
        textView = root.findViewById(R.id.text_car);

        carViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}
