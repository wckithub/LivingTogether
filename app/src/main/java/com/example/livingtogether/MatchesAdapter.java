package com.example.livingtogether;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.livingtogether.databinding.ItemMatchBinding;
import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchViewHolder> {

    private final List<MatchModel> matchItems;

    public MatchesAdapter(List<MatchModel> matchItems) {
        this.matchItems = matchItems;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMatchBinding binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MatchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        MatchModel match = matchItems.get(position);
        holder.binding.tvMatchName.setText(match.getName());
        holder.binding.tvMatchRoom.setText(match.getRoomName());
    }

    @Override
    public int getItemCount() {
        return matchItems.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        final ItemMatchBinding binding;
        public MatchViewHolder(ItemMatchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}