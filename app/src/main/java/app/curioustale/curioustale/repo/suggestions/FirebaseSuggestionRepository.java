package app.curioustale.curioustale.repo.suggestions;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import app.curioustale.curioustale.utils.FirebaseUtils;

public class FirebaseSuggestionRepository implements SuggestionRepository {
    private final FirebaseFirestore db;

    public FirebaseSuggestionRepository () {
        this.db = FirebaseFirestore.getInstance();
    }

    @Override
    public void submitSuggestion(String suggestion, SuggestionResultListener listener) {
        Map<String, String> data = new HashMap<>();
        data.put("suggestion", suggestion);
        db.collection(FirebaseUtils.COLLECTION_SUGGESTIONS)
                .document()
                .set(data)
                .addOnSuccessListener(unused -> listener.onSubmissionSuccess())
                .addOnFailureListener(listener::onSubmissionError);
    }
}
