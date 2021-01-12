package com.example.vroomrr.ui.car;

import android.content.Intent;
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
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.example.vroomrr.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CarListFragment extends Fragment implements CarListViewAdapter.OnActionListener, ServerCallback {
    private View root;
    // Add RecyclerView member
    private RecyclerView recyclerView;
    private CarListViewAdapter adapter;
    private ArrayList<Car> cars = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_car_list, container, false);

        ServerConnection.getCars(new User(), this, getActivity());

        // Build RecyclerView and set Adapter
        recyclerView = root.findViewById(R.id.car_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(new CarListViewAdapter(this.getContext(), this, cars));
        this.adapter = (CarListViewAdapter) recyclerView.getAdapter();


        return root;
    }

    private ArrayList<Car> getCars(){
        return cars;
    }

    @Override
    public void deleteCar(int adapterPosition) {
        cars.remove(adapterPosition);
        this.adapter.updateData(cars);
    }

    /**
     * Open a specific car from the list of available cars.
     * @param adapterPosition the position on the recyclerview
     */
    @Override
    public void openCar(int adapterPosition) {
        Gson gson = new Gson();
        Intent intent = new Intent(getActivity(), CarActivity.class);
        System.out.println(gson.toJson(cars.get(adapterPosition)));
        intent.putExtra("car_info", gson.toJson(cars.get(adapterPosition)));
        getActivity().startActivity(intent);
    }

    @Override
    public void completionHandler(String object, String url) {
        this.cars = new Gson().fromJson(object, new TypeToken<ArrayList<Car>>(){}.getType());
        System.out.println("All cars:" + cars.get(0).toString());
        this.adapter.updateData(cars);
    }
}
