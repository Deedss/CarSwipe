package com.example.vroomrr.ui.swipe;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vroomrr.Candidate;
import com.example.vroomrr.Car;
import com.example.vroomrr.CarImage;
import com.example.vroomrr.Chat;
import com.example.vroomrr.Cryptography;
import com.example.vroomrr.Opinion;
import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.example.vroomrr.Session;
import com.example.vroomrr.User;
import com.example.vroomrr.ui.chat.ChatActivity;
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
    private ImageButton red;
    private ImageButton yellow;
    private ImageButton green;

    private ArrayList<Candidate> candidates;
    private int current_candidate = 0;
    private int current_image = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_swipe, container, false);
        currentimage = root.findViewById(R.id.car_preview);
        title = root.findViewById(R.id.title);
        subtitle = root.findViewById(R.id.subtitle);
        description = root.findViewById(R.id.description);
        red = root.findViewById(R.id.red);
        yellow = root.findViewById(R.id.yellow);
        green = root.findViewById(R.id.green);

        red.setVisibility(View.GONE);
        green.setVisibility(View.GONE);
        yellow.setVisibility(View.GONE);

        User u = new User();
        u.setUserId(Cryptography.getFromSharedPreferences(this.getContext(), String.valueOf(R.string.UserId)));

        ServerConnection.getCars(u, new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                ArrayList<Car> cars = new Gson().fromJson(object, new TypeToken<ArrayList<Car>>(){}.getType());
                if(cars.size() == 0){
                    loadError("You need to add a car before you can find others");
                }else{
                    loadCandidates();
                }

            }}, getActivity());

        currentimage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imageTouched((int)event.getX(), (int)event.getY());
                }
                return true;
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opinion o = new Opinion();
                o.setUserIdMatch(candidates.get(current_candidate).getUser().getUserId());
                o.setOpinion("red");

                ServerConnection.rateCandidate(o, new ServerCallback() {
                    @Override
                    public void completionHandler(String object, String url) {

                    }
                }, getActivity());
                loadNextCandidate();
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opinion o = new Opinion();
                o.setUserIdMatch(candidates.get(current_candidate).getUser().getUserId());
                o.setOpinion("yellow");

                ServerConnection.rateCandidate(o, new ServerCallback() {
                    @Override
                    public void completionHandler(String object, String url) {

                    }
                }, getActivity());
                loadNextCandidate();
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opinion o = new Opinion();
                o.setUserIdMatch(candidates.get(current_candidate).getUser().getUserId());
                o.setOpinion("green");

                ServerConnection.rateCandidate(o, new ServerCallback() {
                    @Override
                    public void completionHandler(String object, String url) {
                        Chat tmpchat = new Gson().fromJson(object, Chat.class);
                        tmpchat.setName(candidates.get(current_candidate).getCar().getBrand() + " " +candidates.get(current_candidate).getCar().getType()
                                + " - " + candidates.get(current_candidate).getCar().getLicense_plate());
                        tmpchat.setDescription("Matched on " + tmpchat.getStart());
                        if(tmpchat.getChatId() != null){
                            showMatch(tmpchat);
                        }

                    }
                }, getActivity());
                loadNextCandidate();
            }
        });

        return root;
    }

    private void loadCandidates(){
        ServerConnection.getCandidates(new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                candidates = new Gson().fromJson(object, new TypeToken<ArrayList<Candidate>>(){}.getType());
                Collections.shuffle(candidates);
                if(candidates.size() > 0) {
                    loadCandidate(candidates.get(current_candidate));
                    red.setVisibility(View.VISIBLE);
                    green.setVisibility(View.VISIBLE);
                    yellow.setVisibility(View.VISIBLE);
                }else{
                    loadError("No users found matching your criteria, check back later");
                }
            }
        }, getActivity());
    }

    private void imageTouched(int x, int y){
        if(x > currentimage.getWidth() / 2){
            if(candidates.size() != 0) {
                if (current_image < candidates.get(current_candidate).getCarImages().size() - 1) {
                    current_image++;
                    loadCandidate(candidates.get(current_candidate));
                }
            }
        }else{
            if(current_image > 0){
                current_image--;
                loadCandidate(candidates.get(current_candidate));
            }
        }
    }

    private void loadNextCandidate(){
        if(current_candidate < candidates.size() - 1){
            current_candidate++;
            current_image = 0;
            loadCandidate(candidates.get(current_candidate));
        }else{
            loadError("You're all done");
        }
    }

    @SuppressLint("SetTextI18n")
    private void loadCandidate(Candidate candidate){
        title.setText(candidate.getUser().getName());
        subtitle.setText(candidate.getCar().getBuild_year() + " " + candidate.getCar().getBrand() + " " + candidate.getCar().getType()
                + " - " + candidate.getCar().getLicense_plate());

        description.setText(
                 candidate.getCar().getHorsepower() +" HP, "+
                        candidate.getCar().getFuel_type() +", "+
                                candidate.getCar().getColor() +"\n\n"+
                candidate.getCar().getDescription());
        currentimage.setImageBitmap(null);

        try {
            if(candidate.getCarImages().size() > 0) {
                Bitmap image = new ServerConnection.GetImageFromUrl().execute(candidate.getCarImages().get(current_image).getCar_images_id()).get();
                currentimage.setImageBitmap(image);
            }
        }catch (Exception e){
            Log.e("Error", e.toString());
        }
    }

    private void loadError(String e){
        red.setVisibility(View.GONE);
        green.setVisibility(View.GONE);
        yellow.setVisibility(View.GONE);
        title.setText("");
        description.setText("");
        subtitle.setText(e);
    }


    @Override
    public void completionHandler(String object, String url) {
    }

    public void showMatch(final Chat c){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this.getContext());
        builder1.setMessage("It's a match! do you want to chat with this user?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Open Chat",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getActivity(), ChatActivity.class);
                        intent.putExtra("chat", new Gson().toJson(c));
                        getActivity().startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                "Back",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}