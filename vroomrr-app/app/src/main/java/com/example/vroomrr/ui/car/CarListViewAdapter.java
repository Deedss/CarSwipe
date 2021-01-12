package com.example.vroomrr.ui.car;

import android.content.Context;
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
    private Context context;
    private OnActionListener mListener;

    public CarListViewAdapter(Context context, OnActionListener listener, ArrayList<Car> cars) {
        this.context = context;
        this.mListener = listener;
        this.cars = cars;
    }

    @NonNull
    @Override
    public CarListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.car_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CarListViewHolder holder, final int position) {
//        holder.car_image.setImageResource(cars.get(position).getImageResource());
//        holder.car_name.setText(cars.get(position).getName());
//        holder.car_license.setText(cars.get(position).getLicense());
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    /**
     * Returns the ArrayList with Cars
     * @return ArrayList
     */
    public ArrayList<Car> getCars() {
        return cars;
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
            car_image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == car_deletebtn.getId()){
                mListener.deleteCar(getAdapterPosition());
            } else {
                mListener.openCar(getAdapterPosition());
            }
        }
    }




}
