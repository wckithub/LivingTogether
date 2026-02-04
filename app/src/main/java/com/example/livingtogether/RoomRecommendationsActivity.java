package com.example.livingtogether;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.livingtogether.databinding.ActivityRoomRecommendationsBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RoomRecommendationsActivity extends AppCompatActivity {

    private ActivityRoomRecommendationsBinding binding;
    private List<Room> rooms;
    private int currentRoomIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomRecommendationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 1. Receive data from Intent
        float minBudget = getIntent().getFloatExtra("MIN_BUDGET", 0);
        float maxBudget = getIntent().getFloatExtra("MAX_BUDGET", Float.MAX_VALUE);
        String preferredLocation = getIntent().getStringExtra("LOCATION");
        String preferredRoomType = getIntent().getStringExtra("ROOM_TYPE");

        // 2. Initialize and Filter Room List
        initRoomList();
        filterRooms(minBudget, maxBudget, preferredLocation, preferredRoomType);

        // 3. Populate UI
        displayCurrentRoom();

        binding.btnBack.setOnClickListener(v -> finish());
        
        binding.btnDislike.setOnClickListener(v -> showNextRoom());

        binding.btnChat.setOnClickListener(v -> {
            if (!rooms.isEmpty()) {
                Room currentRoom = rooms.get(currentRoomIndex);
                Toast.makeText(this, "Opening chat with " + currentRoom.ownerName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRoomList() {
        rooms = new ArrayList<>();
        // Mock data
        rooms.add(new Room("Private Room", 12000, "Patan", "Single Room", "Alex, 28", "Looking for a tidy and respectful roommate. I work in tech and enjoy hiking on weekends..."));
        rooms.add(new Room("Cozy Studio", 18000, "Baneshwor", "Apartment", "Sarah, 24", "Modern studio apartment in a quiet residential area. Full amenities included."));
        rooms.add(new Room("Shared Dorm", 7000, "Kirtipur", "Shared Room", "Rohan, 21", "Cheap and clean shared space for students near TU. No smokers allowed."));
    }

    private void filterRooms(float min, float max, String location, String type) {
        List<Room> filtered = new ArrayList<>();
        for (Room room : rooms) {
            // Apply filtering logic based on preferences
            if (room.price >= min && room.price <= max) {
                filtered.add(room);
            }
        }
        rooms = filtered;
    }

    private void displayCurrentRoom() {
        if (rooms.isEmpty()) {
            Toast.makeText(this, "No matching rooms found.", Toast.LENGTH_LONG).show();
            binding.recommendationCard.setVisibility(android.view.View.GONE);
            return;
        }
        
        Room room = rooms.get(currentRoomIndex);
        binding.recommendationCard.setVisibility(android.view.View.VISIBLE);
        
        // Populate UI with real data
        // For title, we use the room type as title in this view
        // price, location, name etc.
    }

    private void showNextRoom() {
        if (currentRoomIndex < rooms.size() - 1) {
            currentRoomIndex++;
            displayCurrentRoom();
        } else {
            Toast.makeText(this, "No more rooms!", Toast.LENGTH_SHORT).show();
        }
    }

    // Basic Room model class
    private static class Room {
        String title, location, type, ownerName, desc;
        int price;

        Room(String title, int price, String location, String type, String ownerName, String desc) {
            this.title = title; this.price = price; this.location = location;
            this.type = type; this.ownerName = ownerName; this.desc = desc;
        }
    }
}