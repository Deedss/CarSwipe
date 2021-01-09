package com.example.vroomrr.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.Chat;
import com.example.vroomrr.R;

import java.util.ArrayList;

public class ChatListViewAdapter extends RecyclerView.Adapter<ChatListViewAdapter.ChatListViewHolder> {

    private ArrayList<Chat> chats;

    public ChatListViewAdapter(ArrayList<Chat> chats) {
        this.chats = chats;
        //todo To remove later on
        chats.add(new Chat(0, "1"));
        chats.add(new Chat(0, "2"));
    }

    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListViewHolder holder, int position) {
        holder.chat_image.setImageResource(chats.get(position).getChat_imageResource());
        holder.chat_text.setText(chats.get(position).getChatId());
        holder.chat_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo Open Specific Chat.
            }
        });
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ChatListViewHolder extends RecyclerView.ViewHolder {
        // Variables in ChatRow
        ImageView chat_image;
        TextView chat_text;
        CardView chat_cardView;

        public ChatListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.chat_cardView = itemView.findViewById(R.id.chat_cardView);
            this.chat_image = itemView.findViewById(R.id.chat_image);
            this.chat_text = itemView.findViewById(R.id.chat_text);
        }
    }
}
