package com.example.livingtogether;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.example.livingtogether.databinding.ActivityAddRoomBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class AddRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddRoomBinding binding = ActivityAddRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup dropdowns
        String[] locations = new String[]{"Baneshwor", "Patan", "Thamel", "Kirtipur"};
        binding.actvLocation.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, locations));

        String[] roomTypes = new String[]{"Single Room", "Shared Room", "Full Apartment"};
        binding.actvRoomType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, roomTypes));

        String[] amenities = new String[]{"Wi-Fi", "Kitchen", "Parking", "Attached Bathroom"};
        binding.actvAmenities.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, amenities));

        // Modern Date Picker implementation
        binding.etAvailableFrom.setOnClickListener(v -> {
            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select Availability Date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                // Adjust for time zone to ensure the date shown is the date selected
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                String dateString = sdf.format(new Date(selection));
                binding.etAvailableFrom.setText(dateString);
            });

            datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
        });

        binding.btnBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        binding.btnSubmit.setOnClickListener(v -> {
            // Logic for submitting room details
            finish();
        });
    }
}