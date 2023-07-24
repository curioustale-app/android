package app.curioustale.curioustale.ui.answer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.Timestamp;

import app.curioustale.curioustale.R;
import app.curioustale.curioustale.config.Constants;
import app.curioustale.curioustale.databinding.FragmentAnswerBinding;
import app.curioustale.curioustale.models.Answer;
import app.curioustale.curioustale.repo.auth.FirebaseAuthRepository;
import app.curioustale.curioustale.ui.MainViewModel;
import app.curioustale.curioustale.utils.DayStreakUtils;
import app.curioustale.curioustale.utils.Either;
import app.curioustale.curioustale.utils.FirebaseUtils;
import app.curioustale.curioustale.utils.HashUtils;
import app.curioustale.curioustale.utils.PreferenceUtils;

public class AnswerFragment extends Fragment {

    private FragmentAnswerBinding binding;
    private AnswerViewModel viewModel;
    private MainViewModel mainViewModel;
    private PreferenceUtils preferenceUtils;

    private String questionTitle;
    private String currentUserId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(AnswerViewModel.class);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        preferenceUtils = new PreferenceUtils(requireContext());
        if (getArguments() != null) {
            questionTitle = getArguments().getString(Constants.KEY_QUESTION_TITLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAnswerBinding.inflate(inflater, container, false);
        binding.tvQuestion.setText(questionTitle);
        binding.btnAnswer.setOnClickListener(v -> submitAnswer());
        currentUserId = new FirebaseAuthRepository().getCurrentUser().getUid();
        return binding.getRoot();
    }

    private void submitAnswer() {
        String story = checkInputField();
        if (story == null) return;

        Answer answer = new Answer();
        answer.setUserId(currentUserId);
        answer.setAnswer(story);
        answer.setQuestionId(HashUtils.md5(questionTitle));
        answer.setQuestion(questionTitle);
        answer.setTimestamp(Timestamp.now());

        binding.btnAnswer.setEnabled(false);
        binding.progress.show();
        viewModel.submitAnswer(answer).observe(getViewLifecycleOwner(), this::handleSubmitResult);
    }

    private void handleSubmitResult(Either<Exception, Boolean> either) {
        binding.btnAnswer.setEnabled(true);
        binding.progress.hide();

        if (either.isRight()) {
            handleDayStreakUpdate();
            displayToast(getString(R.string.answer_submit_success));
            navigateBackToQuestion();
            preferenceUtils.storeRefreshStateOfStories(true);
        } else {
            displayToast(getString(R.string.answer_submit_error));
        }
    }

    private void handleDayStreakUpdate() {
        mainViewModel.getCurrentUserDetails(currentUserId).observe(getViewLifecycleOwner(), user
                -> mainViewModel.saveUserDetails(DayStreakUtils.updateStreak(user, FirebaseUtils.getTodayAsDate())));
    }

    private void displayToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void navigateBackToQuestion() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.popBackStack();
    }

    private String checkInputField() {
        if (binding.etAnswer.getText() == null || binding.etAnswer.getText().toString().length() == 0) {
            binding.etlAnswer.setError(getString(R.string.please_enter_answer_prompt));
            return null;
        }
        return binding.etAnswer.getText().toString();
    }
}