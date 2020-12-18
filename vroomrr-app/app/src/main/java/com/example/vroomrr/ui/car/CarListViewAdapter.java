package com.example.vroomrr.ui.car;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.Car;
import com.example.vroomrr.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CarListViewAdapter extends RecyclerView.Adapter<CarListViewAdapter.CarListViewHolder>{
    ArrayList<Car> cars;

    public CarListViewAdapter(ArrayList<Car> cars) {
        this.cars = cars;

        //todo To remove later on
        cars.add(new Car("1","1", "1",0,0));
        cars.add(new Car("2","2", "2",0,0));
        cars.add(new Car("3","3", "3",0,0));
        cars.add(new Car("4","4", "4",0,0));
    }

    @NonNull
    @Override
    public CarListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.car_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CarListViewHolder holder, final int position) {
        holder.car_image.setImageResource(cars.get(position).getImageResource());
        holder.car_name.setText(cars.get(position).getName());
        holder.car_license.setText(cars.get(position).getLicense());
        holder.car_deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cars.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class CarListViewHolder extends RecyclerView.ViewHolder{
        // Variables
        ImageView car_image;
        TextView car_name;
        TextView car_license;
        ImageButton car_deletebtn;

        public CarListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.car_image = itemView.findViewById(R.id.car_image);
            this.car_name = itemView.findViewById(R.id.car_name);
            this.car_license = itemView.findViewById(R.id.car_license);
            this.car_deletebtn = itemView.findViewById(R.id.car_deletebtn);
        }
    }
}
