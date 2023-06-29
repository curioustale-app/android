package app.curioustale.curioustale.repo.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthRepository implements AuthRepository<FirebaseUser> {
    private final FirebaseAuth firebaseAuth;

    public FirebaseAuthRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean isUserSignedIn() {
        return firebaseAuth.getCurrentUser() != null;
    }

    @Override
    public void signInAnonymously(AnonymousSignInListener<FirebaseUser> listener) {
        firebaseAuth.signInAnonymously()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        listener.success(user);
                    } else {
                        listener.error(task.getException());
                    }
                });
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }
}
