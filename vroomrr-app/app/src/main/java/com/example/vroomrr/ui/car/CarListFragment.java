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
import com.google.gson.Gson;

import java.util.ArrayList;

public class CarListFragment extends Fragment implements CarListViewAdapter.OnActionListener {
    private View root;
    // Add RecyclerView member
    private RecyclerView recyclerView;
    private ArrayList<Car> cars = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_car_list, container, false);

        //todo To remove later on
        cars.add(new Car("1","1", "1",0,0, "volvo", "v70"));
        cars.add(new Car("2","2", "2",0,0,"volvo", "v70"));
        cars.add(new Car("3","3", "3",0,0,"volvo", "v70"));
        cars.add(new Car("4","4", "4",0,0,"volvo", "v70"));

        // Build RecyclerView and set Adapter
        recyclerView = root.findViewById(R.id.car_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(new CarListViewAdapter(this.getContext(), this, cars));

        return root;
    }

    private ArrayList<Car> getCars(){
        ArrayList<Car> cars = new ArrayList<>();

        return cars;
    }

    @Override
    public void deleteCar(int adapterPosition) {
        cars.remove(adapterPosition);
        recyclerView.getAdapter().notifyDataSetChanged();
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
}
