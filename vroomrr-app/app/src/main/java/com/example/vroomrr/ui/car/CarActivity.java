package com.example.vroomrr.ui.car;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.vroomrr.Car;
import com.example.vroomrr.CarImage;
import com.example.vroomrr.Image64;
import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CarActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    // Views
    private ImageView car_imageView;
    private TextView car_license_plate;
    private TextView car_color;
    private TextView car_horsepower;
    private TextView car_build_year;
    private TextView car_fuel_types;
    private EditText car_description;
    private CheckBox car_selected;
    private Button car_save_button;
    private Button car_delete_button;
    private FloatingActionButton car_image_add;
    private FloatingActionButton car_image_delete;

    // Other variables
    private Car car;
    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private ArrayList<CarImage> carImages = new ArrayList<>();
    private int current_image = 0;
    private final int REQUEST_STORAGE_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        setupViews();
        setupToolbar();
    }

    /**
     * Setups the toolbar within the application
     */
    public void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.car_toolbar);
        toolbar.setTitle(car.getBrand() + " " + car.getType());
        setActionBar(toolbar);
    }

    /**
     * Setup the Views and onClickListeners
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setupViews(){
        // find views
        car_imageView = findViewById(R.id.car_carImage);
        car_imageView.setOnTouchListener(this);

        car_license_plate = findViewById(R.id.car_license_plate);
        car_color = findViewById(R.id.car_color);
        car_horsepower = findViewById(R.id.car_horsepower);
        car_build_year = findViewById(R.id.car_build_year);
        car_fuel_types = findViewById(R.id.car_fuel_types);
        car_description = findViewById(R.id.car_description);
        car_selected = findViewById(R.id.car_selected);
        
        car_delete_button = findViewById(R.id.car_delete_button);
        car_delete_button.setOnClickListener(this);
        car_save_button = findViewById(R.id.car_save_button);
        car_save_button.setOnClickListener(this);

        car_image_add = findViewById(R.id.car_image_add);
        car_image_delete = findViewById(R.id.car_image_delete);
        car_image_add.setOnClickListener(this);
        car_image_delete.setOnClickListener(this);

        // Get data from intent extra and convert to car object.
        String car_info = getIntent().getStringExtra("car_info");
        car = new Gson().fromJson(car_info, Car.class);

        getCarImages(car);
    }

    /**
     * Handle the onClickListener for any views.
     * @param v view
     */
    @Override
    public void onClick(View v) {
        if(v == car_delete_button){
            deleteCarDialog();
        }
        if(v == car_save_button){
            updateCar();
        }
        if(v == car_image_add){
            checkAndRequestPermissions();
        }
        if(v == car_image_delete){
            deleteCarImageDialog();
        }
    }

    private void deleteCar(Car car) {
        ServerConnection.deleteCar(car, new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                Toast.makeText(getApplicationContext(), "Car has been deleted", Toast.LENGTH_LONG).show();
                finish();
            }
        }, this);
    }

    /**
     * Handle the on Touch event for the ImageView
     * @param v View
     * @param event The event that takes places
     * @return boolean
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            imageTouched((int)event.getX(), (int)event.getY());
        }
        return true;
    }

    /**
     * This function is used to easily update all items in the Car object.
     */
    private void insertCarData() {
        car_imageView.setImageBitmap(bitmaps.get(current_image));
        car_license_plate.setText(car.getLicense_plate());
        car_color.setText(car.getColor());
        car_horsepower.setText(car.getHorsepower());
        car_build_year.setText(car.getBuild_year());
        car_fuel_types.setText(car.getFuel_type());
        car_description.setText(car.getDescription());
        car_selected.setSelected(car.isSelected());
    }

    /**
     * Get all images from the selected car
     * @param car Car
     */
    private void getCarImages(Car car){
        ServerConnection.getCarImages(car, new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
            // All car images
            carImages = new Gson().fromJson(object, new TypeToken<ArrayList<CarImage>>(){}.getType());
            try {
                bitmaps.clear();
                current_image = 0;
                // Get bitmaps from each carImage
                for(CarImage carImage : carImages){
                    Bitmap bitmap = new ServerConnection.GetImageFromUrl().execute(carImage.getCar_images_id()).get();
                    bitmaps.add(getResizedBitmap(bitmap,500));
                }
                current_image = bitmaps.size() - 1;
                // Fill in car data
                insertCarData();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            }
        }, this);
    }

    /**
     * Update info on current car.
     */
    private void updateCar() {
        car.setDescription(car_description.getText().toString());
        car.setSelected(car_selected.isSelected());
        ServerConnection.updateCar(car, new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                Toast.makeText(getApplicationContext(), "Updated " + car.getBrand() + " " + car.getType() , Toast.LENGTH_SHORT).show();
            }
        }, this);
        this.finish();
    }

    /**
     * Convert a bitmap to a base64 string.
     * @param bitmap bitmap to convert
     * @return returns Base64 string.
     */
    private Image64 convertToImage64(Bitmap bitmap){
        String base64 = ServerConnection.encodeToBase64(bitmap, this);
        Image64 image64 = new Image64();
        image64.setImage64(base64);
        image64.setLicense_plate(car.getLicense_plate());
        return image64;
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
     * Delete a carImage.
     * @param carImage delete a carImage from the server.
     */
    private void deleteCarImage(CarImage carImage){
        if(carImages.size() > 1){
            ServerConnection.deleteCarImage(carImage, new ServerCallback() {
                @Override
                public void completionHandler(String object, String url) {
                    Log.e("DeleteCarImage:", object);
                    Toast.makeText(getApplicationContext(), "Removed image", Toast.LENGTH_LONG).show();
                    getCarImages(car);
                }
            }, this);
        } else {
            Toast.makeText(getApplicationContext(), "Can't remove final image", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Resize a bitmap
     * @param image bitmap to resize
     * @param maxSize the size
     * @return bitmap
     */
    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
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
                bitmaps.add(bitmapImage);
                current_image = bitmaps.indexOf(bitmapImage);

                ServerConnection.addCarImage(convertToImage64(bitmaps.get(current_image)), new ServerCallback() {
                    @Override
                    public void completionHandler(String object, String url) {
                        Toast.makeText(getApplicationContext(), "Added Image", Toast.LENGTH_SHORT).show();
                    }
                }, this);

                getCarImages(car);
            }
        }
    }

    /**
     * Set the image of the imageview by typing right or left on the image.
     * @param x X coordinates
     * @param y Y coordinates.
     */
    private void imageTouched(int x, int y){
        if(x > car_imageView.getWidth() / 2){
            if(carImages.size() != 0) {
                if (current_image < carImages.size() - 1) {
                    current_image++;
                    car_imageView.setImageBitmap(bitmaps.get(current_image));
                }
            }
        }else{
            if(current_image > 0){
                current_image--;
                car_imageView.setImageBitmap(bitmaps.get(current_image));
            }
        }
    }

    /**
     * Delete Car Dialog
     */
    private void deleteCarDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to delete this car?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteCar(car);
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    /**
     * Delete Car Image Dialog
     */
    private void deleteCarImageDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to delete the current image?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteCarImage(carImages.get(current_image));
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    /**
     * Check permissions for access.
     */
    private void checkAndRequestPermissions() {
        int read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (read == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, REQUEST_STORAGE_PERMISSION);
        } else {
            selectImage();
        }

    }

    /**
     * Asks for permissions when first opened.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_STORAGE_PERMISSION) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImage();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Can't upload images from Gallery", Toast.LENGTH_LONG).show();
        }
    }
}
