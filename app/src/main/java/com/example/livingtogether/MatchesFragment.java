package com.example.livingtogether;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.livingtogether.databinding.FragmentMatchesBinding;
import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {

    private FragmentMatchesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMatchesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvMatches.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initializing with sample data
        List<MatchModel> matches = getSampleMatches();

        if (matches.isEmpty()) {
            binding.rvMatches.setVisibility(View.GONE);
            binding.tvEmptyMatches.setVisibility(View.VISIBLE);
        } else {
            binding.rvMatches.setVisibility(View.VISIBLE);
            binding.tvEmptyMatches.setVisibility(View.GONE);
            MatchesAdapter adapter = new MatchesAdapter(matches);
            binding.rvMatches.setAdapter(adapter);
        }
    }

    private List<MatchModel> getSampleMatches() {
        List<MatchModel> matches = new ArrayList<>();
        matches.add(new MatchModel("Suman Thapa", "", "Quiet room in Baneshwor"));
        matches.add(new MatchModel("Anjali Rai", "", "Shared apartment in Patan"));
        matches.add(new MatchModel("Kiran KC", "", "Single room near Thamel"));
        return matches;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}