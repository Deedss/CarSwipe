package com.example.vroomrr.ui.car;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vroomrr.Car;
import com.example.vroomrr.R;

import java.io.IOException;

public class CarActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView car_imageView;
    private Car car;

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
        car_imageView = findViewById(R.id.car_carImage);
        car_imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == car_imageView){
            selectImage();
        }
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
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}