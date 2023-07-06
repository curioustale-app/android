package app.curioustale.curioustale.repo.answers;

import com.google.firebase.firestore.FirebaseFirestore;

import app.curioustale.curioustale.models.Answer;
import app.curioustale.curioustale.utils.FirebaseUtils;

public class FirebaseAnswerRepository implements AnswerRepository {
    private final FirebaseFirestore db;

    public FirebaseAnswerRepository() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void submitAnswer(String userId, Answer answer, SubmitAnswerResultListener listener) {
        db.collection(FirebaseUtils.COLLECTION_USERS)
                .document(userId)
                .collection(FirebaseUtils.COLLECTION_ANSWERS)
                .document(answer.getQuestionId())
                .set(answer)
                .addOnSuccessListener(unused -> listener.onAnswerSubmitResult())
                .addOnFailureListener(listener::onAnswerSubmitError);
    }

    @Override
    public void myAnswers(String userId, MyAnswersResultListener listener) {
        db.collection(FirebaseUtils.COLLECTION_USERS)
                .document(userId)
                .collection(FirebaseUtils.COLLECTION_ANSWERS)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> listener.onAnswerListResult(queryDocumentSnapshots.toObjects(Answer.class)))
                .addOnFailureListener(listener::onAnswerListError);
    }
}
