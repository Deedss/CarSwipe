package com.example.vroomrr;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatMessage {
    private String message_id;
    private String chat_id;
    private String user_id;
    private String time;
    private String content;
    private String content_self;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String s) {
        this.message_id = s;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String s) {
        this.chat_id = s;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String s) {
        this.user_id = s;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String s) {
        this.time = s;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String s) {
        this.content = s;
    }

    public String getContentSelf() {
        return content_self;
    }

    public void setContentSelf(String s) {
        this.content_self = s;
    }
}
