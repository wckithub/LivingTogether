package com.example.livingtogether;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.livingtogether.databinding.ActivityRoomDashboardBinding;

public class RoomDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRoomDashboardBinding binding = ActivityRoomDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigation.bottomNavigation.setSelectedItemId(R.id.nav_home);
        
        binding.bottomNavigation.bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_profile) {
                startActivity(new Intent(this, UserProfileActivity.class));
                return true;
            }
            return id == R.id.nav_home;
        });

        binding.btnMenu.setOnClickListener(v -> {
            // Open navigation drawer or menu logic
        });

        binding.btnViewListing.setOnClickListener(v -> {
            // Navigate to full room details
        });
    }
}