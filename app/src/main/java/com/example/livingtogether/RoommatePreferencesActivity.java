package com.example.livingtogether;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.example.livingtogether.databinding.ActivityRoommatePreferencesBinding;

public class RoommatePreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRoommatePreferencesBinding binding = ActivityRoommatePreferencesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup Dropdowns
        String[] ageRanges = new String[]{"18-25", "26-30", "31-35", "35+"};
        binding.actvAgeRange.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ageRanges));

        String[] genders = new String[]{"No Preference", "Male", "Female"};
        binding.actvGenderPref.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, genders));

        String[] occupations = new String[]{"No Preference", "Student", "Working Professional"};
        binding.actvOccupationPref.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, occupations));

        binding.btnBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        binding.btnSavePreferences.setOnClickListener(v -> {
            // Handle saving preferences logic here
            finish();
        });
    }
}