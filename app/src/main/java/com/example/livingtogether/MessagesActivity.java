package com.example.livingtogether;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // The activity is kept for build consistency but we just show a message and finish
        // if it's ever accidentally launched.
        Toast.makeText(this, "Messages feature coming soon!", Toast.LENGTH_SHORT).show();
        finish();
    }
}