package app.curioustale.curioustale.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import app.curioustale.curioustale.config.PreferenceUtils;
import app.curioustale.curioustale.databinding.FragmentHomeBinding;
import app.curioustale.curioustale.models.Question;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        String today = PreferenceUtils.getToday(requireContext());
        viewModel.onError().observe(this, this::toastError);
        viewModel.getQuestionForTheDay(today).observe(this, this::setQuestionForTheDay);
        return binding.getRoot();
    }

    private void toastError(Exception error) {
        String message = "Something went wrong";
        if (error.getLocalizedMessage() != null) message = error.getLocalizedMessage();
        Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    private void setQuestionForTheDay(Question question) {
        binding.textHome.setText(question.getTitle());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}