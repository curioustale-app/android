package app.curioustale.curioustale.repo.questions;

import com.google.firebase.firestore.FirebaseFirestore;

import app.curioustale.curioustale.models.Question;
import app.curioustale.curioustale.utils.FirebaseUtils;

public class FirebaseQuestionRepository implements QuestionRepository {
    private final FirebaseFirestore db;

    public FirebaseQuestionRepository() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void getQuestionForTheDay(String today, QuestionForTheDayResultListener listener) {
        db.collection(FirebaseUtils.COLLECTION_QUESTIONS)
                .document(today)
                .get()
                .addOnSuccessListener(documentSnapshot -> listener.onQuestionResult(documentSnapshot.toObject(Question.class)))
                .addOnFailureListener(listener::onQuestionError);
    }
}
