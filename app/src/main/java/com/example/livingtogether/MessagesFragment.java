package com.example.livingtogether;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.livingtogether.databinding.FragmentMessagesBinding;
import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment {

    private FragmentMessagesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMessagesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvMessages.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample Data
        List<ConversationModel> conversations = new ArrayList<>();
        conversations.add(new ConversationModel("Alex", "Hey, when can I visit?", "10:30 AM", ""));
        conversations.add(new ConversationModel("Sarah", "The deposit is $500.", "Yesterday", ""));
        conversations.add(new ConversationModel("John", "Is the room still available?", "Monday", ""));

        MessagesAdapter adapter = new MessagesAdapter(conversations);
        binding.rvMessages.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}