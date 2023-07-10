package app.curioustale.curioustale.ui.settings.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.curioustale.curioustale.repo.suggestions.FirebaseSuggestionRepository;
import app.curioustale.curioustale.repo.suggestions.SuggestionRepository;

public class SuggestionViewModel extends ViewModel {
    private final SuggestionRepository suggestionRepository;

    public SuggestionViewModel() {
        this.suggestionRepository = new FirebaseSuggestionRepository();
    }

    public MutableLiveData<Boolean> submitSuggestion(String suggestion) {
        MutableLiveData<Boolean> success = new MutableLiveData<>();
        suggestionRepository.submitSuggestion(suggestion, new SuggestionRepository.SuggestionResultListener() {
            @Override
            public void onSubmissionSuccess() {
                success.postValue(true);
            }

            @Override
            public void onSubmissionError(Exception e) {
                e.printStackTrace();
                success.postValue(false);
            }
        });
        return success;
    }
}
