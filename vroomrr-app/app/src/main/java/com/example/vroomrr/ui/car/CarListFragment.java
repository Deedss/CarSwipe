package com.example.vroomrr.ui.car;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

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
import java.util.concurrent.ExecutionException;

public class CarListFragment extends Fragment implements CarListViewAdapter.OnActionListener{
    private View root;
    // Add RecyclerView member
    private RecyclerView recyclerView;
    private CarListViewAdapter adapter;
    private ImageButton btn_addCar;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Bitmap> images;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_car_list, container, false);
        getCars();
        setupViews();
        return root;
    }

    /**
     * Setup all the views
     */
    private void setupViews(){
        // Build RecyclerView and set Adapter
        recyclerView = root.findViewById(R.id.car_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(new CarListViewAdapter(this.getContext(), this, cars, images));
        this.adapter = (CarListViewAdapter) recyclerView.getAdapter();
        btn_addCar = root.findViewById(R.id.car_add);
        setBtnOnClick();
    }

    /**
     * Set on click listener for btn with dialog
     */
    private void setBtnOnClick(){
        btn_addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Enter licenseplate");

                // Set up the input
                final EditText input = new EditText(v.getContext());
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                int maxLength = 6;
                input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addCar(input.getText().toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    /**
     * Delete car at specific position
     * @param adapterPosition , the position of the car.
     */
    @Override
    public void deleteCar(int adapterPosition) {
        ServerConnection.deleteCar(cars.get(adapterPosition), new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                getCars();
            }
        }, getActivity());
    }

    /**
     * Add a new Car to the current User.
     * @param licenseplate licenseplate of the car to add.
     */
    private void addCar(String licenseplate){
        ServerConnection.addCar(licenseplate, new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                System.out.println(object);
                getCars();
            }
        }, getActivity());
    }

    /**
     * Get all cars for the current User.
     */
    private void getCars(){
        ServerConnection.getCars(new User(), new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                cars = new Gson().fromJson(object, new TypeToken<ArrayList<Car>>(){}.getType());
//                TODO not working yet
//                getCarImages(cars);
                adapter.updateData(cars);
            }
        }, getActivity());
    }

    private void getCarImages(ArrayList<Car> cars){
        for(final Car car : this.cars){
            ServerConnection.getCarImage(car, new ServerCallback() {
                @Override
                public void completionHandler(String object, String url) {
                    try {
                        Bitmap bitmap = new ServerConnection.GetImageFromUrl().execute("https://grolink.nl/" + new Gson().toJson(car)).get();
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, getActivity());
        }
    }

    /**
     * Open a specific car from the list of available cars.
     * @param adapterPosition the position on the recyclerview
     */
    @Override
    public void openCar(int adapterPosition) {
        Intent intent = new Intent(getActivity(), CarActivity.class);
        intent.putExtra("car_info", new Gson().toJson(cars.get(adapterPosition)));
        requireActivity().startActivity(intent);
    }
}
