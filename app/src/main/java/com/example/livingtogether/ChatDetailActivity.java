package com.example.livingtogether;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.livingtogether.databinding.ActivityChatDetailBinding;
import java.util.ArrayList;
import java.util.List;

public class ChatDetailActivity extends AppCompatActivity {

    private ActivityChatDetailBinding binding;
    private ChatAdapter adapter;
    private List<ChatMessage> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 1. Initialize List and Adapter
        messageList = new ArrayList<>();
        adapter = new ChatAdapter(messageList);

        // 2. Setup RecyclerView with LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true); // Starts list from bottom
        binding.rvChatMessages.setLayoutManager(layoutManager);
        binding.rvChatMessages.setAdapter(adapter);

        // Header logic
        String senderName = getIntent().getStringExtra("SENDER_NAME");
        if (senderName != null) {
            binding.tvChatName.setText(senderName);
        }

        binding.btnBack.setOnClickListener(v -> finish());

        // 3. Send Button Logic
        binding.btnSend.setOnClickListener(v -> {
            String text = binding.etMessage.getText().toString().trim();
            if (!text.isEmpty()) {
                // Add message to list
                messageList.add(new ChatMessage(text, true));
                
                // Notify adapter and scroll to bottom
                adapter.notifyItemInserted(messageList.size() - 1);
                binding.rvChatMessages.scrollToPosition(messageList.size() - 1);
                
                // Clear input
                binding.etMessage.setText("");
            }
        });
    }
}