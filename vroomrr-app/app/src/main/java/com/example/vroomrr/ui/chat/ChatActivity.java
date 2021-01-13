package com.example.vroomrr.ui.chat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vroomrr.Chat;
import com.example.vroomrr.ChatMessage;
import com.example.vroomrr.Cryptography;
import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.example.vroomrr.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity implements ChatMessagesListViewAdapter.OnActionListener {
    private RecyclerView recyclerView;
    private ArrayList<ChatMessage> messages = new ArrayList<>();
    private ChatMessagesListViewAdapter adapter;
    private Chat chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chat = new Gson().fromJson(getIntent().getStringExtra("chat"), Chat.class);
        getSupportActionBar().setTitle(chat.getName());
        getSupportActionBar().setSubtitle(chat.getDescription());

        recyclerView = findViewById(R.id.chatmessages_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //TODO: Get actual logged in user here!
        User u = new User();
        u.setUserId(Cryptography.getFromSharedPreferences(this, String.valueOf(R.string.UserId)));
        adapter = new ChatMessagesListViewAdapter(getApplicationContext(),this ,messages, u);
        recyclerView.setAdapter(adapter);

        updateList();
        //Update list with messages every 1 second
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                updateList();
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    public void updateList(){
        ServerConnection.getChatMsgs(new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                messages = new Gson().fromJson(object, new TypeToken<ArrayList<ChatMessage>>(){}.getType());
                //Sort messages
                messages.sort(new Comparator<ChatMessage>() {
                    @Override
                    public int compare(ChatMessage o1, ChatMessage o2) {
                        return o1.getTime().compareTo(o2.getTime());
                    }
                });
                adapter.updateData(messages);
            }
        }, this, chat);
    }

    @Override
    public void clickMessage(int adapterPosition) {

    }

    public void sendClick(final View view) {
        final EditText text = findViewById(R.id.editText);
        if(text.getText().length() == 0){
            return;
        }
        ChatMessage message = new ChatMessage();
        message.setChat_id(chat.getChatId());
        message.setContent(text.getText().toString());
        message.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
        view.setEnabled(false);
        ServerConnection.sendMessageTMP(new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                text.setText("");
                view.setEnabled(true);
                recyclerView.scrollToPosition(messages.size() - 1);
            }
        },this,message);
    }
}