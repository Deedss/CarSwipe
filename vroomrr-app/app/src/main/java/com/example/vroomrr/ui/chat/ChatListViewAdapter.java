package com.example.vroomrr.ui.chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.Car;
import com.example.vroomrr.Chat;
import com.example.vroomrr.R;

import java.util.ArrayList;

public class ChatListViewAdapter extends RecyclerView.Adapter<ChatListViewAdapter.ChatListViewHolder>{
    private ArrayList<Chat> Chats;
    private Context context;
    private static OnActionListener mListener;

    public ChatListViewAdapter(Context context, OnActionListener listener, ArrayList<Chat> Chats) {
        this.context = context;
        this.mListener = listener;
        this.Chats = Chats;
    }

    public void updateData(ArrayList<Chat> chats){
        this.Chats = chats;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatListViewHolder holder, final int position) {
       // holder.Chat_image.setImageResource(Chats.get(position).getImageResource());
        holder.Chat_name.setText(Chats.get(position).getChatId());
    }

    @Override
    public int getItemCount() {
        return Chats.size();
    }

    /**
     * Returns the ArrayList with Chats
     * @return ArrayList
     */
    public ArrayList<Chat> getChats() {
        return Chats;
    }

    interface OnActionListener{
        void openChat(int adapterPosition);
    }


    public static class ChatListViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        // Variables
        ImageView Chat_image;
        TextView Chat_name;

        public ChatListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.Chat_image = itemView.findViewById(R.id.chat_image);
            this.Chat_name = itemView.findViewById(R.id.chat_text);

            itemView.findViewById(R.id.chat_row).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.openChat(getAdapterPosition());
        }
    }




}
