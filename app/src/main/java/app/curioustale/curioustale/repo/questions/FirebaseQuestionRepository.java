package app.curioustale.curioustale.repo.questions;

import com.google.firebase.firestore.FirebaseFirestore;

import app.curioustale.curioustale.utils.FirebaseUtils;
import app.curioustale.curioustale.models.Question;

public class FirebaseQuestionRepository implements QuestionRepository {
    private final FirebaseFirestore db;

    public FirebaseQuestionRepository() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void getQuestionForTheDay(String today, QuestionForTheDayResultListener listener) {
        db.collection(FirebaseUtils.COLLECTION_QUESTIONS).document(today).get().addOnSuccessListener(documentSnapshot -> {
            Question question = documentSnapshot.toObject(Question.class);
            listener.onQuestionResult(question);
        }).addOnFailureListener(listener::onQuestionError);
    }
}
