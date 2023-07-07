package app.curioustale.curioustale.repo.answers;

import java.util.List;

import app.curioustale.curioustale.models.Answer;

public interface AnswerRepository {
    void submitAnswer(Answer answer, SubmitAnswerResultListener listener);

    void myAnswers(String userId, MyAnswersResultListener listener);

    interface SubmitAnswerResultListener {
        void onAnswerSubmitResult();

        void onAnswerSubmitError(Exception e);
    }

    interface MyAnswersResultListener {
        void onAnswerListResult(List<Answer> answers);

        void onAnswerListError(Exception e);
    }
}
