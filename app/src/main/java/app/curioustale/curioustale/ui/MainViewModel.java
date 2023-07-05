package app.curioustale.curioustale.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.curioustale.curioustale.config.Constants;
import app.curioustale.curioustale.models.Question;
import app.curioustale.curioustale.repo.questions.FirebaseQuestionRepository;
import app.curioustale.curioustale.repo.questions.QuestionRepository;

public class MainViewModel extends ViewModel {
    private final QuestionRepository questionRepository;
    private MutableLiveData<Question> question;

    public MainViewModel() {
        questionRepository = new FirebaseQuestionRepository();
    }

    public MutableLiveData<Question> getQuestionForTheDay(String today) {
        if (question != null) return question;
        Log.d(Constants.DEBUG_TAG, "getting the question for the day");

        question = new MutableLiveData<>();
        questionRepository.getQuestionForTheDay(today, new QuestionRepository.QuestionForTheDayResultListener() {
            @Override
            public void onQuestionResult(Question q) {
                question.setValue(q);
            }

            @Override
            public void onQuestionError(Exception e) {
                question.setValue(null);
            }
        });
        return question;
    }
}
