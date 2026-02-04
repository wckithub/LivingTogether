package com.example.livingtogether;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.example.livingtogether.databinding.ActivitySeekerPreferencesBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SeekerPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySeekerPreferencesBinding binding = ActivitySeekerPreferencesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup dropdowns
        String[] roomTypes = new String[]{"Single Room", "Shared Room", "Apartment"};
        binding.actvRoomType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, roomTypes));

        String[] smokingPref = new String[]{"No", "Yes", "Occasionally"};
        binding.actvSmoking.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, smokingPref));

        String[] drinkingPref = new String[]{"No", "Yes", "Socially"};
        binding.actvDrinking.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, drinkingPref));

        String[] petsPref = new String[]{"Not allowed", "Allowed", "Depends on the pet"};
        binding.actvPets.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, petsPref));

        // Slider listeners to update text labels
        binding.sliderCleanliness.addOnChangeListener((slider, value, fromUser) -> 
                binding.tvValCleanliness.setText(String.valueOf((int) value)));
        binding.sliderSocial.addOnChangeListener((slider, value, fromUser) -> 
                binding.tvValSocial.setText(String.valueOf((int) value)));
        binding.sliderNoise.addOnChangeListener((slider, value, fromUser) -> 
                binding.tvValNoise.setText(String.valueOf((int) value)));

        // RangeSlider for budget
        binding.sliderBudget.addOnChangeListener((slider, value, fromUser) -> {
            List<Float> values = slider.getValues();
            binding.tvBudgetMin.setText(String.format(Locale.getDefault(), "NPR %,.0f", values.get(0)));
            binding.tvBudgetMax.setText(String.format(Locale.getDefault(), "NPR %,.0f", values.get(1)));
        });

        // Date Picker
        binding.etMoveInDate.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select Move-in Date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                binding.etMoveInDate.setText(sdf.format(new Date(selection)));
            });

            datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
        });

        binding.btnBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        binding.btnSavePreferences.setOnClickListener(v -> {
            // Collect Data Safely
            List<Float> budgetRange = binding.sliderBudget.getValues();
            float minBudget = budgetRange.get(0);
            float maxBudget = budgetRange.get(1);
            
            String location = "";
            if (binding.etLocationSearch.getText() != null) {
                location = binding.etLocationSearch.getText().toString();
            }
            
            String roomType = binding.actvRoomType.getText().toString();
            int cleanliness = (int) binding.sliderCleanliness.getValue();
            int social = (int) binding.sliderSocial.getValue();
            int noise = (int) binding.sliderNoise.getValue();

            // Pass to Recommendations Activity
            Intent intent = new Intent(this, RoomRecommendationsActivity.class);
            intent.putExtra("MIN_BUDGET", minBudget);
            intent.putExtra("MAX_BUDGET", maxBudget);
            intent.putExtra("LOCATION", location);
            intent.putExtra("ROOM_TYPE", roomType);
            intent.putExtra("CLEANLINESS", cleanliness);
            intent.putExtra("SOCIAL", social);
            intent.putExtra("NOISE", noise);
            
            startActivity(intent);
        });
    }
}