package app.curioustale.curioustale.ui.answer;

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
import com.google.firebase.Timestamp;

import app.curioustale.curioustale.R;
import app.curioustale.curioustale.config.Constants;
import app.curioustale.curioustale.databinding.FragmentAnswerBinding;
import app.curioustale.curioustale.models.Answer;
import app.curioustale.curioustale.utils.Either;
import app.curioustale.curioustale.utils.HashUtils;

public class AnswerFragment extends Fragment {
    private String questionTitle;
    private FragmentAnswerBinding binding;
    private AnswerViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(AnswerViewModel.class);
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
        return binding.getRoot();
    }

    private void submitAnswer() {
        String story = checkInputField();
        if (story == null) return;

        Answer answer = new Answer();
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

        if(either.isRight()) {
            displayToast(getString(R.string.answer_submit_success));
        } else {
            displayToast(getString(R.string.answer_submit_error));
        }
    }

    private void displayToast(String message) {
        Snackbar.make(binding.getRoot(), message, BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    private String checkInputField() {
        if (binding.etAnswer.getText() == null || binding.etAnswer.getText().toString().length() == 0) {
            binding.etlAnswer.setError(getString(R.string.please_enter_answer_prompt));
            return null;
        }
        return binding.etAnswer.getText().toString();
    }
}