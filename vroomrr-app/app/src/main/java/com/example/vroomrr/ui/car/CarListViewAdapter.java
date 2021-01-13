package com.example.vroomrr.ui.car;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.Car;
import com.example.vroomrr.R;

import java.util.ArrayList;

public class CarListViewAdapter extends RecyclerView.Adapter<CarListViewAdapter.CarListViewHolder>{
    private ArrayList<Car> cars;
    private ArrayList<Bitmap> images;
    private Context context;
    private OnActionListener mListener;

    public CarListViewAdapter(Context context, OnActionListener listener, ArrayList<Car> cars, ArrayList<Bitmap> images) {
        this.context = context;
        this.mListener = listener;
        this.cars = cars;
        this.images = images;
    }

    @NonNull
    @Override
    public CarListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.car_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CarListViewHolder holder, final int position) {
        if(images.size() != 0){
            if(images.size() == 1){
                holder.car_image.setImageBitmap(images.get(0));
            } else {
                holder.car_image.setImageBitmap(images.get(position));
            }
        }
        holder.car_name.setText(cars.get(position).getBrand() + " " + cars.get(position).getType());
        holder.car_license.setText(cars.get(position).getLicense_plate());
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    /**
     * Update the car ArrayList
     * @param cars ArrayList to replace current one with.
     */
    public void updateData(ArrayList<Car> cars){
        this.cars = cars;
        notifyDataSetChanged();
    }

    /**
     * Returns the ArrayList with Cars
     * @return ArrayList
     */
    public ArrayList<Car> getCars() {
        return cars;
    }

    public void updateImages(ArrayList<Bitmap> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    interface OnActionListener{
        void deleteCar(int adapterPosition);
        void openCar(int adapterPosition);
    }


    public class CarListViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        // Variables
        ImageView car_image;
        TextView car_name;
        TextView car_license;
        LinearLayout car_row_clickable;

        public CarListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.car_image = itemView.findViewById(R.id.car_image);
            this.car_name = itemView.findViewById(R.id.car_name);
            this.car_license = itemView.findViewById(R.id.car_license);
            this.car_row_clickable = itemView.findViewById(R.id.car_row_clickable);

            car_row_clickable.setOnClickListener(this);
            car_image.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mListener.openCar(getAdapterPosition());
//            if (v.getId() == car_deletebtn.getId()){
//                mListener.deleteCar(getAdapterPosition());
//            } else {
//                mListener.openCar(getAdapterPosition());
//            }
        }
    }




}
