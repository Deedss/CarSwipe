package com.example.vroomrr.ui.car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.Car;
import com.example.vroomrr.R;

import java.util.ArrayList;

public class CarFragment extends Fragment {
    private View root;

    // Add RecyclerView member
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_car, container, false);

        // Build RecyclerView and set Adapter
        recyclerView = root.findViewById(R.id.car_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(new CarListViewAdapter(new ArrayList<Car>()));

        return root;
    }
}
