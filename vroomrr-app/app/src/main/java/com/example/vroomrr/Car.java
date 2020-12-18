package com.example.vroomrr;

import android.media.Image;

public class Car {
    private String name;
    private String license;
    private String construction_year;
    private int mileage;
    private int imageResource;

    public Car(String name, String license, String construction_year, int mileage, int imageResource) {
        this.name = name;
        this.license = license;
        this.construction_year = construction_year;
        this.mileage = mileage;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getLicense() {
        return license;
    }

    public String getConstruction_year() {
        return construction_year;
    }

    public int getMileage() {
        return mileage;
    }

    public int getImageResource() {
        return imageResource;
    }
}
