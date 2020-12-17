package com.example.vroomrr.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.Car;
import com.example.vroomrr.Chat;
import com.example.vroomrr.R;
import com.example.vroomrr.ui.car.CarListViewAdapter;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private ChatViewModel chatViewModel;
    private View root;

    // Add RecyclerView member
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_chat, container, false);
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        // Build RecyclerView and set Adapter
        recyclerView = root.findViewById(R.id.chat_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(new ChatListViewAdapter(new ArrayList<Chat>()));

        return root;
    }
}