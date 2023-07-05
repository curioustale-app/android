package app.curioustale.curioustale.ui.question;

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
import app.curioustale.curioustale.databinding.FragmentQuestionBinding;
import app.curioustale.curioustale.models.Question;
import app.curioustale.curioustale.ui.MainViewModel;

public class QuestionFragment extends Fragment {
    private FragmentQuestionBinding binding;
    private MainViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentQuestionBinding.inflate(inflater, container, false);
        String today = PreferenceUtils.getToday(requireContext());
        viewModel.getQuestionForTheDay(today).observe(getViewLifecycleOwner(), this::setQuestionForTheDay);
        binding.btnAnswer.setOnClickListener(v -> navigateToAnswerPage());
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void navigateToAnswerPage() {
        NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        controller.navigate(R.id.from_question_to_answer);
    }

    private void setQuestionForTheDay(Question question) {
        binding.progress.hide();
        if (question == null) {
            handleNoQuestionForDay();
            return;
        }
        binding.tvQuestion.setText(question.getTitle());
    }

    private void handleNoQuestionForDay() {
        Snackbar.make(binding.getRoot(), "No Question for today!", BaseTransientBottomBar.LENGTH_SHORT).show();
    }
}
