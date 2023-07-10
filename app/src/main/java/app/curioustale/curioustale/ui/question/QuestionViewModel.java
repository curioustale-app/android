package app.curioustale.curioustale.ui.question;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.curioustale.curioustale.models.Answer;
import app.curioustale.curioustale.repo.answers.AnswerRepository;
import app.curioustale.curioustale.repo.answers.FirebaseAnswerRepository;
import app.curioustale.curioustale.repo.auth.FirebaseAuthRepository;
import app.curioustale.curioustale.utils.Either;

public class QuestionViewModel extends ViewModel {
    private final AnswerRepository answerRepository;
    private MutableLiveData<Either<Exception, Answer>> answerById;

    public QuestionViewModel() {
        answerRepository = new FirebaseAnswerRepository();
    }

    public MutableLiveData<Either<Exception, Answer>> getAnswerForQuestion(String questionId) {
        String userId = new FirebaseAuthRepository().getCurrentUser().getUid();
        answerById = new MutableLiveData<>();
        answerRepository.getAnswer(userId, questionId, new AnswerRepository.GetAnswerResultListener() {
            @Override
            public void onAnswerResult(Answer answer) {
                answerById.postValue(Either.right(answer));
            }

            @Override
            public void onAnswerError(Exception e) {
                answerById.postValue(Either.left(e));
            }
        });
        return answerById;
    }
}
