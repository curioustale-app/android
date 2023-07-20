package app.curioustale.curioustale.ui.settings.sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import app.curioustale.curioustale.R;
import app.curioustale.curioustale.databinding.SheetSuggestionBinding;
import app.curioustale.curioustale.ui.settings.viewmodel.SuggestionViewModel;

public class SuggestionSheet extends BottomSheetDialogFragment {
    public static final String TAG = "suggestion-sheet";

    private SheetSuggestionBinding binding;
    private SuggestionViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SuggestionViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SheetSuggestionBinding.inflate(inflater, container, false);
        binding.btnSubmit.setOnClickListener(v -> this.handleSubmit());
        return binding.getRoot();
    }

    private void handleSubmit() {
        if (binding.etSuggestion.getText() == null) {
            binding.etlSuggestion.setError(getString(R.string.empty_suggestion_error));
            return;
        }
        if (binding.etSuggestion.getText().toString().length() == 0) {
            binding.etlSuggestion.setError(getString(R.string.empty_suggestion_error));
            return;
        }

        binding.btnSubmit.setEnabled(false);
        String suggestion = binding.etSuggestion.getText().toString();
        viewModel.submitSuggestion(suggestion).observe(getViewLifecycleOwner(), this::handleSubmissionResult);
    }

    private void handleSubmissionResult(Boolean isSuccess) {
        binding.btnSubmit.setEnabled(true);
        if (Boolean.FALSE.equals(isSuccess)) {
            Toast.makeText(requireContext(), R.string.suggestion_submit_error, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), R.string.suggestion_success, Toast.LENGTH_SHORT).show();
        }
        this.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
