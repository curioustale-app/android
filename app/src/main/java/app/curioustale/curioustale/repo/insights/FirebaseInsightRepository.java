package app.curioustale.curioustale.repo.insights;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import app.curioustale.curioustale.models.Insight;
import app.curioustale.curioustale.utils.FirebaseUtils;

public class FirebaseInsightRepository implements InsightRepository {
    private final FirebaseFirestore db;

    public FirebaseInsightRepository() {
        this.db = FirebaseFirestore.getInstance();
    }

    @Override
    public void listInsights(InsightListListener listener) {
        db.collection(FirebaseUtils.COLLECTION_INSIGHTS)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(20)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots ->
                        listener.onInsightsListResult(queryDocumentSnapshots.toObjects(Insight.class)))
                .addOnFailureListener(listener::onInsightsListError);
    }
}
