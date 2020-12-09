package com.example.vroomrr.ui.swipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SwipeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SwipeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home/swipe fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}