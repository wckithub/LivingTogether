package com.example.livingtogether;

public class MatchModel {
    private final String name;
    private final String profilePicUrl;
    private final String roomName;

    public MatchModel(String name, String profilePicUrl, String roomName) {
        this.name = name;
        this.profilePicUrl = profilePicUrl;
        this.roomName = roomName;
    }

    public String getName() { return name; }
    public String getProfilePicUrl() { return profilePicUrl; }
    public String getRoomName() { return roomName; }
}