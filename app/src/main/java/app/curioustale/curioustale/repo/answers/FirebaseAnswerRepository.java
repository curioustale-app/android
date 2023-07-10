package app.curioustale.curioustale.repo.answers;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import app.curioustale.curioustale.models.Answer;
import app.curioustale.curioustale.utils.FirebaseUtils;

public class FirebaseAnswerRepository implements AnswerRepository {
    private final FirebaseFirestore db;

    public FirebaseAnswerRepository() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void submitAnswer(Answer answer, SubmitAnswerResultListener listener) {
        db.collection(FirebaseUtils.COLLECTION_USERS_ANSWERS)
                .document(answer.getUserId())
                .collection(FirebaseUtils.COLLECTION_ANSWERS)
                .document(answer.getQuestionId())
                .set(answer)
                .addOnSuccessListener(unused -> listener.onAnswerSubmitResult())
                .addOnFailureListener(listener::onAnswerSubmitError);
    }

    @Override
    public void myAnswers(String userId, MyAnswersResultListener listener) {
        db.collection(FirebaseUtils.COLLECTION_USERS_ANSWERS)
                .document(userId)
                .collection(FirebaseUtils.COLLECTION_ANSWERS)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> listener.onAnswerListResult(queryDocumentSnapshots.toObjects(Answer.class)))
                .addOnFailureListener(listener::onAnswerListError);
    }

    @Override
    public void getAnswer(String userId, String questionId, GetAnswerResultListener listener) {
        db.collection(FirebaseUtils.COLLECTION_USERS_ANSWERS)
                .document(userId)
                .collection(FirebaseUtils.COLLECTION_ANSWERS)
                .document(questionId)
                .get()
                .addOnSuccessListener(snapshot -> {
                    Answer answer = snapshot.toObject(Answer.class);
                    if (answer == null) {
                        listener.onAnswerError(new Exception(""));
                    } else {
                        listener.onAnswerResult(answer);
                    }
                })
                .addOnFailureListener(listener::onAnswerError);
    }
}
