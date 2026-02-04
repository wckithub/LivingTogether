package com.example.livingtogether;

public class ConversationModel {
    private final String senderName;
    private final String lastMessage;
    private final String timestamp;
    private final String profileImageUrl;

    public ConversationModel(String senderName, String lastMessage, String timestamp, String profileImageUrl) {
        this.senderName = senderName;
        this.lastMessage = lastMessage;
        this.timestamp = timestamp;
        this.profileImageUrl = profileImageUrl;
    }

    public String getSenderName() { return senderName; }
    public String getLastMessage() { return lastMessage; }
    public String getTimestamp() { return timestamp; }
    public String getProfileImageUrl() { return profileImageUrl; }
}