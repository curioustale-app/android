package app.curioustale.curioustale.repo.questions;

import app.curioustale.curioustale.models.Question;

public interface QuestionRepository {
    void getQuestionForTheDay(String today, QuestionForTheDayResultListener listener);

    interface QuestionForTheDayResultListener {
        void onQuestionResult(Question question);

        void onQuestionError(Exception e);
    }
}
