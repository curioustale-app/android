package app.curioustale.curioustale.ui.insights;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import app.curioustale.curioustale.databinding.FragmentInsightsBinding;
import app.curioustale.curioustale.models.Insight;
import app.curioustale.curioustale.ui.insights.adapter.InsightsAdapter;

public class InsightsFragment extends Fragment implements InsightsAdapter.OnInsightClickListener {
    private FragmentInsightsBinding binding;
    private InsightsViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(InsightsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInsightsBinding.inflate(inflater, container, false);
        viewModel.getInsights().observe(getViewLifecycleOwner(), this::handleInsights);
        return binding.getRoot();
    }

    private void handleInsights(List<Insight> insights) {
        binding.progress.hide();
        binding.rvMyInsights.setAdapter(new InsightsAdapter(insights, this));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onInsightClicked(Insight insight) {
        if (insight.getUrl().length() == 0) return;
        startActivity(getIntentForUrl(insight.getUrl()));
    }

    private Intent getIntentForUrl(String url) {
        return new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
    }
}