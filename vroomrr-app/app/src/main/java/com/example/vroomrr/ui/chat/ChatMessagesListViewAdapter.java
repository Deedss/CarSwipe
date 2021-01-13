package com.example.vroomrr.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.Chat;
import com.example.vroomrr.ChatMessage;
import com.example.vroomrr.R;
import com.example.vroomrr.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatMessagesListViewAdapter extends RecyclerView.Adapter<ChatMessagesListViewAdapter.ChatListViewHolder>{
    private ArrayList<ChatMessage> messages;
    private Context context;
    private static OnActionListener mListener;
    private User currentUser;

    public ChatMessagesListViewAdapter(Context context, OnActionListener listener, ArrayList<ChatMessage> msgs, User currentUser) {
        this.context = context;
        this.mListener = listener;
        this.messages = msgs;
        this.currentUser = currentUser;
    }

    public void updateData(ArrayList<ChatMessage> messages){
        this.messages = messages;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chatmessage_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatListViewHolder holder, final int position) {
       // holder.Chat_image.setImageResource(Chats.get(position).getImageResource());

        //If message is from current logged in user show on right side
        if(messages.get(position).getUser_id().equals(currentUser.getUserId())){
            holder.msg_sent.setText(messages.get(position).getContentSelf());
            //Show the correct bubble and hide other
            holder.msg_recv_body.setVisibility(View.GONE);
            holder.msg_sent_body.setVisibility(View.VISIBLE);
        }else{
            //Else show message on left side
            holder.msg_recv.setText(messages.get(position).getContent());
            //Show the correct bubble and hide other
            holder.msg_recv_body.setVisibility(View.VISIBLE);
            holder.msg_sent_body.setVisibility(View.GONE);
            String[] time = messages.get(position).getTime().split(" ");
            holder.name.setText(time[2] + " " + time[1] + " - " + time[4]);
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    /**
     * Returns the ArrayList with Chats
     * @return ArrayList
     */
    public ArrayList<ChatMessage> getMessages() {
        return messages;
    }

    interface OnActionListener{
        void clickMessage(int adapterPosition);
    }


    public static class ChatListViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        // Variables
        TextView msg_sent;
        TextView msg_recv;
        TextView name;

        RelativeLayout msg_sent_body;
        RelativeLayout msg_recv_body;

        public ChatListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.msg_sent = itemView.findViewById(R.id.msg_sent);
            this.msg_recv = itemView.findViewById(R.id.msg_recv);

            this.msg_sent_body = itemView.findViewById(R.id.msg_sent_body);
            this.msg_recv_body = itemView.findViewById(R.id.msg_recv_body);
            this.name = itemView.findViewById(R.id.name);

            itemView.findViewById(R.id.chatmessage_row).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.clickMessage(getAdapterPosition());
        }
    }




}
