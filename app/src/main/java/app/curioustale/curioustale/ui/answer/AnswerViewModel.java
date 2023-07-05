package app.curioustale.curioustale.ui.answer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.curioustale.curioustale.models.Answer;
import app.curioustale.curioustale.repo.answers.AnswerRepository;
import app.curioustale.curioustale.repo.answers.FirebaseAnswerRepository;
import app.curioustale.curioustale.repo.auth.FirebaseAuthRepository;
import app.curioustale.curioustale.utils.Either;

public class AnswerViewModel extends ViewModel {
    private final AnswerRepository answerRepository;
    private MutableLiveData<Either<Exception, Boolean>> answerResult;

    public AnswerViewModel() {
        answerRepository = new FirebaseAnswerRepository();
    }

    public MutableLiveData<Either<Exception, Boolean>> submitAnswer(Answer answer) {
        if (answerResult != null) return answerResult;

        answerResult = new MutableLiveData<>();
        String userId = new FirebaseAuthRepository().getCurrentUser().getUid();
        answerRepository.submitAnswer(userId, answer, new AnswerRepository.SubmitAnswerResultListener() {
            @Override
            public void onAnswerSubmitResult() {
                answerResult.setValue(Either.right(true));
            }
            @Override
            public void onAnswerSubmitError(Exception e) {
                answerResult.setValue(Either.left(e));
            }
        });
        return answerResult;
    }
}
