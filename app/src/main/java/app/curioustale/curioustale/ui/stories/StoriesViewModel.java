package app.curioustale.curioustale.ui.stories;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import app.curioustale.curioustale.models.Answer;
import app.curioustale.curioustale.repo.answers.AnswerRepository;
import app.curioustale.curioustale.repo.answers.FirebaseAnswerRepository;
import app.curioustale.curioustale.repo.auth.FirebaseAuthRepository;
import app.curioustale.curioustale.utils.Either;

public class StoriesViewModel extends ViewModel {
    private final AnswerRepository repository;
    private MutableLiveData<Either<Exception, List<Answer>>> stories;

    public StoriesViewModel() {
        repository = new FirebaseAnswerRepository();
    }

    public MutableLiveData<Either<Exception, List<Answer>>> myStories() {
        if (stories != null) return stories;

        stories = new MutableLiveData<>();
        String userId = new FirebaseAuthRepository().getCurrentUser().getUid();
        repository.myAnswers(userId, new AnswerRepository.MyAnswersResultListener() {
            @Override
            public void onAnswerListResult(List<Answer> answers) {
                stories.postValue(Either.right(answers));
            }

            @Override
            public void onAnswerListError(Exception e) {
                stories.postValue(Either.left(e));
            }
        });
        return stories;
    }

}