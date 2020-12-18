package com.example.vroomrr;

import java.util.ArrayList;

public class Chat {

    private int chat_imageResource;
    private String chatId;
    ArrayList<ChatMessage> messages;

    /**
     * Constructor
     */
    public Chat(int chat_imageResource, String chatId) {
        this.chat_imageResource = chat_imageResource;
        this.chatId = chatId;
    }

    public int getChat_imageResource() {
        return chat_imageResource;
    }

    public String getChatId() {
        return chatId;
    }
}
