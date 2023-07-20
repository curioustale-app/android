package app.curioustale.curioustale.repo.suggestions;

public interface SuggestionRepository {
    void submitSuggestion(String suggestion, SuggestionResultListener listener);

    interface SuggestionResultListener {
        void onSubmissionSuccess();

        void onSubmissionError(Exception e);
    }
}
