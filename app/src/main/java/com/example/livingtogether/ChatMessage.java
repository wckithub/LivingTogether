package com.example.livingtogether;

public class ChatMessage {
    private final String text;
    private final boolean isSentByMe;

    public ChatMessage(String text, boolean isSentByMe) {
        this.text = text;
        this.isSentByMe = isSentByMe;
    }

    public String getText() {
        return text;
    }

    public boolean isSentByMe() {
        return isSentByMe;
    }
}