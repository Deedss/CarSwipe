package com.example.vroomrr;

import java.util.ArrayList;

public class Chat {

    private String chat_id;
    private String user_id1;
    private String user_id2;
    private String start;

    //Not filled by API but manually
    private String name;
    private String description;
    private String image;

    /**
     * Constructor
     */
    public Chat(String chat_id, String user_id1, String user_id2, String start) {
        this.chat_id = chat_id;
        this.user_id1 = user_id1;
        this.user_id2 = user_id2;
        this.start = start;
    }

    public String getChatId() {
        return chat_id;
    }
    public String getUserId1() {
        return user_id1;
    }
    public String getUserId2() {
        return user_id2;
    }
    public String getStart() {
        return start;
    }

    public String getImage() { return image;}
    public void setName(String n) { name = n;}
    public void setImage(String n) { image = n;}
    public String getName() { return name;}
    public void setDescription(String n) { description = n;}
    public String getDescription() { return description;}
}
