package app.curioustale.curioustale.ui.question;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import app.curioustale.curioustale.R;
import app.curioustale.curioustale.config.Constants;
import app.curioustale.curioustale.databinding.FragmentQuestionBinding;
import app.curioustale.curioustale.models.Question;
import app.curioustale.curioustale.ui.MainViewModel;
import app.curioustale.curioustale.utils.FirebaseUtils;
import app.curioustale.curioustale.utils.HashUtils;

public class QuestionFragment extends Fragment {
    private FragmentQuestionBinding binding;
    private MainViewModel mainViewModel;
    private QuestionViewModel viewModel;
    private Question question;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        viewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentQuestionBinding.inflate(inflater, container, false);
        String today = FirebaseUtils.getToday();
        Log.d(Constants.DEBUG_TAG, "getting question for today: " + today);
        mainViewModel.getQuestionForTheDay(today).observe(getViewLifecycleOwner(), this::setQuestionForTheDay);
        binding.btnAnswer.setOnClickListener(v -> navigateToAnswerPage());
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void navigateToAnswerPage() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_QUESTION_TITLE, question.getTitle());
        NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        controller.navigate(R.id.from_question_to_answer, bundle);
    }

    private void setQuestionForTheDay(Question question) {
        this.question = question;
        binding.progress.hide();
        if (question == null) {
            handleNoQuestionForDay();
            return;
        }
        binding.tvQuestion.setText(question.getTitle());
        checkIfQuestionIsAnswered(question);
    }

    private void checkIfQuestionIsAnswered(Question question) {
        String questionId = HashUtils.md5(question.getTitle());
        viewModel.getAnswerForQuestion(questionId).observe(getViewLifecycleOwner(), answer -> {
            if (answer.isRight() && answer.getRight().isPresent()) {
                handleQuestionAlreadyAnswered();
            }
        });
    }

    private void handleQuestionAlreadyAnswered() {
        binding.btnAnswer.setEnabled(false);
        binding.btnAnswer.setText(R.string.answered_btn_text);
    }

    private void handleNoQuestionForDay() {
        binding.btnAnswer.setEnabled(false);
        binding.tvQuestion.setText(R.string.no_question_for_today_text);
    }
}
