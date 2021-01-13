package com.example.vroomrr.ui.swipe;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vroomrr.Candidate;
import com.example.vroomrr.CarImage;
import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;

public class SwipeFragment extends Fragment implements ServerCallback {
    private View root;
    private ImageView currentimage;
    private TextView title;
    private TextView subtitle;
    private TextView description;

    private ArrayList<Candidate> candidates;
    private int current_candidate = 0;
    private int current_image = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_swipe, container, false);
        currentimage = root.findViewById(R.id.car_preview);
        title = root.findViewById(R.id.title);
        subtitle = root.findViewById(R.id.subtitle);
        description = root.findViewById(R.id.description);

        ServerConnection.getCandidates(new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                candidates = new Gson().fromJson(object, new TypeToken<ArrayList<Candidate>>(){}.getType());
                Collections.shuffle(candidates);
                loadCandidate(candidates.get(current_candidate));
            }
        }, getActivity());

        currentimage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imageTouched((int)event.getX(), (int)event.getY());
                }
                return true;
            }
        });

        return root;
    }

    private void imageTouched(int x, int y){
        if(x > currentimage.getWidth() / 2){
            if(current_candidate < candidates.size() - 1){
                current_candidate++;
                loadCandidate(candidates.get(current_candidate));
            }
        }else{
            if(current_candidate > 0){
                current_candidate--;
                loadCandidate(candidates.get(current_candidate));
            }
        }
    }

    private void loadCandidate(Candidate candidate){
        title.setText(candidate.getUser().getName());
        subtitle.setText(candidate.getCar().getBuild_year() + " " + candidate.getCar().getBrand() + " " + candidate.getCar().getType());
        description.setText(candidate.getCar().getDescription());

        try {
            Bitmap image = new ServerConnection.GetImageFromUrl().execute(candidate.getCarImages().get(current_image).getCar_images_id()).get();
            currentimage.setImageBitmap(image);
        }catch (Exception e){
            Log.e("Error", e.toString());
        }
    }


    @Override
    public void completionHandler(String object, String url) {
    }
}