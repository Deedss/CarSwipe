package com.example.vroomrr;

import java.util.ArrayList;

public class Candidate {

    private User user;
    private Car car;
    private ArrayList<CarImage> car_images;
    /**
     * Constructor
     */
    public User getUser() {
        return user;
    }
    public void setUser(User u) {
        user = u;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car c) {
        car = c;
    }
    public ArrayList<CarImage> getCarImages() {
        return car_images;
    }
    public void setCarImages(ArrayList<CarImage> c) {
        car_images = c;
    }

}
