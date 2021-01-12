package com.example.vroomrr.ui.car;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vroomrr.Car;
import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.google.gson.Gson;

import java.io.IOException;

public class CarActivity extends AppCompatActivity implements View.OnClickListener {
    // Views
    private ImageView car_imageView;
    private TextView car_licenseplate;
    private TextView car_brand;
    private TextView car_model;
    private TextView car_mileage;
    private ImageButton car_save;

    // other variables
    private Car car;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        setupViews();
    }

    /**
     * Setup the Views and onClickListeners
     */
    private void setupViews(){
        // find views
        car_imageView = findViewById(R.id.car_carImage);
        car_licenseplate = findViewById(R.id.car_licenseplate);
        car_brand = findViewById(R.id.car_brand);
        car_model = findViewById(R.id.car_model);
        car_mileage = findViewById(R.id.car_mileage);
        car_save = findViewById(R.id.car_save);

        car_imageView.setOnClickListener(this);

        // Get data from intent extra and convert to car object.
        String extra = getIntent().getStringExtra("car_info");
        car = gson.fromJson(extra, Car.class);

        insertCarData();
    }

    /**
     * This function is used to easily update all items in the Car object.
     */
    private void insertCarData() {
        car_licenseplate.setText(car.getLicense_plate());
        car_brand.setText(car.getBrand());
        car_model.setText(car.getType());
        car_mileage.setText(car.getBuild_year());
    }

    @Override
    public void onClick(View v) {
        if(v == car_imageView){
            selectImage();
        }
        if(v == car_save){
            updateCar();
        }
    }

    private void updateCar() {
        ServerConnection.updateCar(car, new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
            }
        }, this);
    }

    /**
     * Select an Image from the gallery and add it in the
     */
    private void selectImage() {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (cameraIntent.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    /**
     * Handle the result of the activity.
     * @param requestCode the RequestCode
     * @param resultCode the ResultCode
     * @param data the data to process
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                Uri returnUri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                car_imageView.setImageBitmap(bitmapImage);
                //TODO upload image.
//                addImageToServer();
            }
        }
    }
}