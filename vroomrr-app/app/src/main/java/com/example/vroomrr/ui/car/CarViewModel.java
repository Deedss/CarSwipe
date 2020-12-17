package com.example.vroomrr.ui.car;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vroomrr.Car;

import java.util.ArrayList;

public class CarViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    private ArrayList<Car> cars;

    public CarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is car fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
