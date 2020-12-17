package com.example.vroomrr.ui.car;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.Car;
import com.example.vroomrr.R;
import com.example.vroomrr.ui.login.LoginViewModel;

import java.util.ArrayList;

public class CarFragment extends Fragment {
    private CarViewModel carViewModel;
    private TextView textView;
    private View root;

    private ArrayList<Car> cars;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("cars: ", "onCreateView: did it work?");
        root = inflater.inflate(R.layout.fragment_car, container, false);
        carViewModel = new ViewModelProvider(this).get(CarViewModel.class);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.car_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new CarListViewAdapter(cars));
    }
}
