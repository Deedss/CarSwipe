package com.example.vroomrr.ui.car;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.DirectedAcyclicGraph;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.Car;
import com.example.vroomrr.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CarListViewAdapter extends RecyclerView.Adapter<CarListViewAdapter.CarListViewHolder>{
    ArrayList<Car> cars;
    private Context context;

    public CarListViewAdapter(Context context, ArrayList<Car> cars) {
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    private void delete(int adapterPosition){
        cars.remove(adapterPosition);
        notifyDataSetChanged();
    }

    private void openCar(int adapterPosition) {
        CarFragment fragment = new CarFragment(cars.get(adapterPosition));
//        FragmentTransaction transaction = beginTransaction();
//        transaction.replace(R.id.car_viewCar, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
    }

    public class CarListViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        // Variables
        ImageView car_image;
        TextView car_name;
        TextView car_license;
        ImageButton car_deletebtn;
        LinearLayout car_row_clickable;

        public CarListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.car_image = itemView.findViewById(R.id.car_image);
            this.car_name = itemView.findViewById(R.id.car_name);
            this.car_license = itemView.findViewById(R.id.car_license);
            this.car_deletebtn = itemView.findViewById(R.id.car_deletebtn);
            this.car_row_clickable = itemView.findViewById(R.id.car_row_clickable);

            car_deletebtn.setOnClickListener(this);
            car_row_clickable.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == car_deletebtn.getId()){
                delete(getAdapterPosition());
            } else {
                openCar(getAdapterPosition());
            }
        }
    }




}
