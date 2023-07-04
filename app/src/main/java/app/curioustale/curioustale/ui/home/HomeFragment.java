package app.curioustale.curioustale.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import app.curioustale.curioustale.R;
import app.curioustale.curioustale.config.PreferenceUtils;
import app.curioustale.curioustale.databinding.FragmentHomeBinding;
import app.curioustale.curioustale.models.Question;
import app.curioustale.curioustale.ui.MainViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MainViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        String today = PreferenceUtils.getToday(requireContext());
        viewModel.onError().observe(getViewLifecycleOwner(), this::toastError);
        viewModel.getQuestionForTheDay(today).observe(getViewLifecycleOwner(), this::setQuestionForTheDay);
        binding.btnAnswer.setOnClickListener(v -> navigateToAnswerPage());
        return binding.getRoot();
    }

    private void toastError(Exception error) {
        String message = "Something went wrong";
        if (error.getLocalizedMessage() != null) message = error.getLocalizedMessage();
        Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    private void setQuestionForTheDay(Question question) {
        if (question == null) {
            handleNoQuestionForDay();
            return;
        }
        binding.tvQuestion.setText(question.getTitle());
    }

    private void handleNoQuestionForDay() {
        Snackbar.make(binding.getRoot(), "No Question for today!", BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    private void navigateToAnswerPage() {
        NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        controller.navigate(R.id.from_home_to_answer);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}