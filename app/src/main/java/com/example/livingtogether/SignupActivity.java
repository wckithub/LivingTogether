package com.example.livingtogether;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.livingtogether.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignupBinding binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvGoToLogin.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        binding.btnSignup.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, ProfileSetupActivity.class);
            startActivity(intent);
            finish();
        });
    }
}