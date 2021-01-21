package com.example.vroomrr;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.vroomrr.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 7;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!Cryptography.getEncryptedSharedPreferences(this).contains(String.valueOf(R.string.SessionId))){
            logout();
        }

        setupToolbar();
        setupNavigationDrawer();
        checkAndRequestPermissions();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setupNavigationDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                logout();
                return true;
            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_swipe, R.id.nav_chat, R.id.nav_car, R.id.nav_settings)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        final User u = new User();
        final View headerView = navigationView.getHeaderView(0);

        ServerConnection.getPublicKey(u, new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                User user = new Gson().fromJson(object, User.class);
                TextView navUsername = headerView.findViewById(R.id.text1);
                navUsername.setText(user.getName());
            }
        }, this);

        ServerConnection.getCars(u, new ServerCallback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void completionHandler(String object, String url) {
                ArrayList<Car> cars = new Gson().fromJson(object, new TypeToken<ArrayList<Car>>(){}.getType());
                TextView underText = headerView.findViewById(R.id.text2);
                if(cars.size() == 0){
                    underText.setText("No car found");

                }
                for(Car car : cars){
                    if(car.isSelected()){
                        underText.setText(car.getBrand() + " " + car.getType() + " - " + car.getLicense_plate());
                        loadCarImage(car, headerView);
                        return;
                    }
                }
            }
        },this);

    }

    private void loadCarImage(Car c, final View v){
        ServerConnection.getCarImages(c, new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                ArrayList<CarImage> carimages = new Gson().fromJson(object, new TypeToken<ArrayList<CarImage>>(){}.getType());
                try {
                    Bitmap bitmap = new ServerConnection.GetImageFromUrl().execute(carimages.get(0).getCar_images_id()).get();
                    ImageView i = v.findViewById(R.id.imageView);
                    i.setImageBitmap(getResizedBitmap(bitmap, 500));
                }catch (Exception e){

                }

            }
        },this);
    }
    private void logout() {
        SharedPreferences SP = Cryptography.getEncryptedSharedPreferences(this);
        SharedPreferences.Editor editor = SP.edit();
        editor.clear().apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Check permissions for access.
     * @return returns true if permissions are granted.
     */
    private boolean checkAndRequestPermissions() {
        int internet = ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET);
        int write = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int network = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (write != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (internet != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (read != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (network != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

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

}