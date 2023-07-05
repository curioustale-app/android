package app.curioustale.curioustale.repo.answers;

import app.curioustale.curioustale.models.Answer;

public interface AnswerRepository {
    void submitAnswer(String userId, Answer answer, SubmitAnswerResultListener listener);

    interface SubmitAnswerResultListener {
        void onAnswerSubmitResult();

        void onAnswerSubmitError(Exception e);
    }
}
