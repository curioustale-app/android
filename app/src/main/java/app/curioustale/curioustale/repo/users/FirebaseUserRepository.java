package app.curioustale.curioustale.repo.users;

import com.google.firebase.firestore.FirebaseFirestore;

import app.curioustale.curioustale.models.User;
import app.curioustale.curioustale.utils.FirebaseUtils;

public class FirebaseUserRepository implements UserRepository {
    private final FirebaseFirestore db;

    public FirebaseUserRepository() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void getUser(String userId, GetUserResultListener listener) {
        db.collection(FirebaseUtils.COLLECTION_USERS)
                .document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    User user = documentSnapshot.toObject(User.class);
                    if (user == null) {
                        user = new User(userId);
                    }
                    listener.onUserResult(user);
                })
                .addOnFailureListener(listener::onError);
    }

    @Override
    public void saveUser(User user, SaveUserResultListener listener) {
        db.collection(FirebaseUtils.COLLECTION_USERS)
                .document(user.getUserId())
                .set(user)
                .addOnSuccessListener(unused -> listener.onUserSaved())
                .addOnFailureListener(listener::onError);
    }
}
