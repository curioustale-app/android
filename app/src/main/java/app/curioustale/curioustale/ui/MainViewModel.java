package app.curioustale.curioustale.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.curioustale.curioustale.models.Question;
import app.curioustale.curioustale.models.User;
import app.curioustale.curioustale.repo.questions.FirebaseQuestionRepository;
import app.curioustale.curioustale.repo.questions.QuestionRepository;
import app.curioustale.curioustale.repo.users.FirebaseUserRepository;
import app.curioustale.curioustale.repo.users.UserRepository;

public class MainViewModel extends ViewModel {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    private MutableLiveData<Question> question;
    private MutableLiveData<User> user;

    public MainViewModel() {
        questionRepository = new FirebaseQuestionRepository();
        userRepository = new FirebaseUserRepository();
    }

    public MutableLiveData<Question> getQuestionForTheDay(String today) {
        if (question != null) return question;

        question = new MutableLiveData<>();
        questionRepository.getQuestionForTheDay(today, new QuestionRepository.QuestionForTheDayResultListener() {
            @Override
            public void onQuestionResult(Question q) {
                question.postValue(q);
            }

            @Override
            public void onQuestionError(Exception e) {
                question.postValue(null);
            }
        });
        return question;
    }

    public MutableLiveData<User> getCurrentUserDetails(String userId) {
        if (user != null) return user;

        user = new MutableLiveData<>();
        userRepository.getUser(userId, new UserRepository.GetUserResultListener() {
            @Override
            public void onUserResult(User u) {
                user.postValue(u);
            }

            @Override
            public void onError(Exception e) {
                user.postValue(new User(userId));
            }
        });
        return user;
    }

    public void saveUserDetails(User u) {
        if (user == null) user = new MutableLiveData<>();
        userRepository.saveUser(u, new UserRepository.SaveUserResultListener() {
            @Override
            public void onUserSaved() {
                user.postValue(u);
            }

            @Override
            public void onError(Exception e) {
                user.postValue(null);
            }
        });
    }
}
