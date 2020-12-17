package com.example.vroomrr;

public class Chat {

    private int chat_imageResource;
    private int chatId;

    /**
     * Constructor
     */
    public Chat(int chat_imageResource, int chatId) {
        this.chat_imageResource = chat_imageResource;
        this.chatId = chatId;
    }

    public int getChat_imageResource() {
        return chat_imageResource;
    }

    public int getChatId() {
        return chatId;
    }
}
