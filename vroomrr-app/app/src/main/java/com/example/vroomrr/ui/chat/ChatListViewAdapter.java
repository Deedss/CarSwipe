package com.example.vroomrr.ui.chat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.R;

public class ChatListViewAdapter extends RecyclerView.Adapter<ChatListViewAdapter.ChatListViewHolder> {


    public ChatListViewAdapter(Context context) {

    }

    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ChatListViewHolder extends RecyclerView.ViewHolder {
        // Variables in ChatRow
        ImageView chat_image;
        TextView chat_text;

        public ChatListViewHolder(@NonNull View itemView) {
            super(itemView);

            this.chat_image = itemView.findViewById(R.id.chat_image);
            this.chat_text = itemView.findViewById(R.id.chat_text);
        }

        private void setData(int imageResource, String chatText){
            chat_image.setImageResource(imageResource);
            chat_text.setText(chatText);
        }
    }
}
