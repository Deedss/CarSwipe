package com.example.vroomrr.ui.chat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.Car;
import com.example.vroomrr.CarImage;
import com.example.vroomrr.Chat;
import com.example.vroomrr.Cryptography;
import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.example.vroomrr.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ChatListFragment extends Fragment implements ChatListViewAdapter.OnActionListener, ServerCallback {
    private View root;
    // Add RecyclerView member
    private RecyclerView recyclerView;
    private ArrayList<Chat> chats = new ArrayList<>();
    private ChatListViewAdapter adapter;

    private String currentUserID;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        currentUserID = Cryptography.getFromSharedPreferences(this.getContext(), String.valueOf(R.string.UserId));
        ServerConnection.getChats(this, this.getActivity());
        root = inflater.inflate(R.layout.fragment_chat, container, false);

        // Build RecyclerView and set Adapter
        recyclerView = root.findViewById(R.id.chat_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        this.adapter = new ChatListViewAdapter(this.getContext(), this, chats);
        recyclerView.setAdapter(this.adapter);

        return root;
    }

    @Override
    public void openChat(int adapterPosition) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("chat", new Gson().toJson(chats.get(adapterPosition)));
        getActivity().startActivity(intent);
    }

    @Override
    public void completionHandler(String object, String url) {
        this.chats = new Gson().fromJson(object, new TypeToken<ArrayList<Chat>>(){}.getType());
        for(final Chat c : this.chats){
            User u = new User();
            if(c.getUserId1().equals(currentUserID)){
                u.setUserId(c.getUserId2());
            }else{
                u.setUserId(c.getUserId1());
            }

            // Get default selected car for every chat user
            ServerConnection.getCars(u, new ServerCallback() {
                @Override
                public void completionHandler(String object, String url) {
                    ArrayList<Car> cars = new Gson().fromJson(object, new TypeToken<ArrayList<Car>>(){}.getType());
                    for(Car car : cars){
                        if(car.isSelected()){
                            c.setName(car.getBrand() + " " +car.getType() + " - " + car.getLicense_plate());
                            c.setDescription("Matched on " + c.getStart());
                            chats.set(chats.indexOf(c), c);
                            adapter.updateData(chats);

                            ServerConnection.getCarImages(car, new ServerCallback() {
                                @Override
                                public void completionHandler(String object, String url) {
                                    ArrayList<CarImage> carimages = new Gson().fromJson(object, new TypeToken<ArrayList<CarImage>>(){}.getType());
                                    try {
                                        Bitmap bitmap = new ServerConnection.GetImageFromUrl().execute(carimages.get(0).getCar_images_id()).get();
                                        c.setBitmap(getResizedBitmap(bitmap, 200));
                                        chats.set(chats.indexOf(c), c);
                                        adapter.updateData(chats);
                                    }catch (Exception e){
                                    }
                                }
                            }, getActivity());
                        }
                    }
                }
            }, this.getActivity());
        }
        this.adapter.updateData(this.chats);
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
