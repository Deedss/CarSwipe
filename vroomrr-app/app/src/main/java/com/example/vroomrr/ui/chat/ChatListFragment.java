package com.example.vroomrr.ui.chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.Car;
import com.example.vroomrr.Chat;
import com.example.vroomrr.Cryptography;
import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.example.vroomrr.User;
import com.example.vroomrr.ui.car.CarActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ChatListFragment extends Fragment implements ChatListViewAdapter.OnActionListener, ServerCallback {
    private View root;
    // Add RecyclerView member
    private RecyclerView recyclerView;
    private ArrayList<Chat> chats = new ArrayList<>();
    private ChatListViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: Add actual logged in user here from main

        SharedPreferences SP = Cryptography.getEncryptedSharedPreferences(this.getActivity());
        SharedPreferences.Editor editor = SP.edit();
        editor.putString("SessionID","cbd229d6-3764-4897-8882-8d017b0ec6c6");
        editor.apply();
        //TODO: Remove this garbage

        ServerConnection.getChats(this, this.getActivity());

        root = inflater.inflate(R.layout.fragment_car_list, container, false);

        //chats.add(new Chat("1","1", "1","2020"));

        // Build RecyclerView and set Adapter
        recyclerView = root.findViewById(R.id.car_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        this.adapter = new ChatListViewAdapter(this.getContext(), this, chats);
        recyclerView.setAdapter(this.adapter);

        return root;
    }

    @Override
    public void openChat(int adapterPosition) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("chat_info", new Gson().toJson(chats.get(adapterPosition)));
        getActivity().startActivity(intent);
    }

    @Override
    public void completionHandler(Boolean success, String object) {
        this.chats = new Gson().fromJson(object, new TypeToken<ArrayList<Chat>>(){}.getType());
        Log.e("Oh lordy", this.chats.get(0).getChatId());
        this.adapter.updateData(this.chats);
    }
}
