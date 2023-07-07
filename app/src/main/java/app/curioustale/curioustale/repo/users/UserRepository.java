package app.curioustale.curioustale.repo.users;

import app.curioustale.curioustale.models.User;

public interface UserRepository {
    void getUser(String userId, GetUserResultListener listener);

    void saveUser(User user, SaveUserResultListener listener);

    interface GetUserResultListener {
        void onUserResult(User user);

        void onError(Exception e);
    }

    interface SaveUserResultListener {
        void onUserSaved();

        void onError(Exception e);
    }
}
