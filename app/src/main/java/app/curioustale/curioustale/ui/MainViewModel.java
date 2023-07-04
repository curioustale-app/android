package app.curioustale.curioustale.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.curioustale.curioustale.models.Question;
import app.curioustale.curioustale.repo.questions.FirebaseQuestionRepository;
import app.curioustale.curioustale.repo.questions.QuestionRepository;

public class MainViewModel extends ViewModel {
    private final QuestionRepository questionRepository;
    private final MutableLiveData<Exception> error;
    private MutableLiveData<Question> question;

    public MainViewModel() {
        error = new MutableLiveData<>();
        questionRepository = new FirebaseQuestionRepository();
    }

    public MutableLiveData<Exception> onError() {
        return error;
    }

    public MutableLiveData<Question> getQuestionForTheDay(String today) {
        if (question != null) return question;

        question = new MutableLiveData<>();
        questionRepository.getQuestionForTheDay(today, new QuestionRepository.QuestionForTheDayResultListener() {
            @Override
            public void onQuestionResult(Question q) {
                question.setValue(q);
            }

            @Override
            public void onQuestionError(Exception e) {
                error.setValue(e);
            }
        });
        return question;
    }
}
