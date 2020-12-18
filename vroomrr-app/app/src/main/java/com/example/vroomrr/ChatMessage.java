package com.example.vroomrr;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatMessage {
    private String message;
    private LocalDateTime dateTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ChatMessage(String message, String dateTime) {
        this.message = message;

        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
    }
}
