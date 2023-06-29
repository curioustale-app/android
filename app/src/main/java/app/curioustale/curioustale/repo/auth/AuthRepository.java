package app.curioustale.curioustale.repo.auth;

public interface AuthRepository<T> {
    boolean isUserSignedIn();

    void signInAnonymously(AnonymousSignInListener<T> listener);

    T getCurrentUser();

    interface AnonymousSignInListener<T> {
        void success(T user);

        void error(Exception e);
    }
}
