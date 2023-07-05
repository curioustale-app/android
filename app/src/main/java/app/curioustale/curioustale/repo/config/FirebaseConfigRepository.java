package app.curioustale.curioustale.repo.config;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import app.curioustale.curioustale.utils.FirebaseUtils;
import app.curioustale.curioustale.models.ServerConfig;

public class FirebaseConfigRepository implements ConfigRepository {
    private final FirebaseFirestore db;

    public FirebaseConfigRepository() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void setTimestamp(ServerTimestampSetListener listener) {
        Map<String, Object> config = new HashMap<>();
        config.put("timestamp", FieldValue.serverTimestamp());

        db.collection(FirebaseUtils.COLLECTION_CONFIGS).document(FirebaseUtils.COLLECTION_CONFIGS_SERVER).set(config).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                listener.onTimestampSet();
            }
        });
    }

    @Override
    public void getServerConfig(ServerConfigResultListener listener) {
        db.collection(FirebaseUtils.COLLECTION_CONFIGS).document(FirebaseUtils.COLLECTION_CONFIGS_SERVER)
                .get().addOnSuccessListener(documentSnapshot -> {
                    ServerConfig config = documentSnapshot.toObject(ServerConfig.class);
                    listener.onServerConfigResult(config);
                });
    }
}
