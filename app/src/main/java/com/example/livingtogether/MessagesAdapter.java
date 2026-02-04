package com.example.livingtogether;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.livingtogether.databinding.ItemConversationBinding;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ConversationViewHolder> {

    private final List<ConversationModel> conversations;

    public MessagesAdapter(List<ConversationModel> conversations) {
        this.conversations = conversations;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemConversationBinding binding = ItemConversationBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ConversationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        ConversationModel model = conversations.get(position);
        holder.binding.tvSenderName.setText(model.getSenderName());
        holder.binding.tvLastMessage.setText(model.getLastMessage());
        holder.binding.tvTimestamp.setText(model.getTimestamp());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ChatDetailActivity.class);
            intent.putExtra("SENDER_NAME", model.getSenderName());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    public static class ConversationViewHolder extends RecyclerView.ViewHolder {
        final ItemConversationBinding binding;
        public ConversationViewHolder(ItemConversationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}