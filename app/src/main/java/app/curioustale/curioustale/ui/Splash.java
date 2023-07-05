package app.curioustale.curioustale.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import app.curioustale.curioustale.R;
import app.curioustale.curioustale.config.PreferenceUtils;
import app.curioustale.curioustale.databinding.ActivitySplashBinding;
import app.curioustale.curioustale.repo.auth.AuthRepository;
import app.curioustale.curioustale.repo.auth.FirebaseAuthRepository;
import app.curioustale.curioustale.repo.config.ConfigRepository;
import app.curioustale.curioustale.repo.config.FirebaseConfigRepository;
import app.curioustale.curioustale.utils.FirebaseUtils;

public class Splash extends AppCompatActivity implements AuthRepository.AnonymousSignInListener<FirebaseUser> {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ConfigRepository configRepository = new FirebaseConfigRepository();
        AuthRepository<FirebaseUser> authRepository = new FirebaseAuthRepository();

        configRepository.setTimestamp(() -> configRepository.getServerConfig(serverConfig -> {
            String today = FirebaseUtils.firebaseTimestampToDayKey(serverConfig.getTimestamp());
            PreferenceUtils.saveToday(Splash.this, today);
            if (authRepository.isUserSignedIn()) {
                navigateToDashboard();
            } else {
                authRepository.signInAnonymously(this);
            }
        }));
    }

    private void navigateToDashboard() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void success(FirebaseUser user) {
        navigateToDashboard();
    }

    @Override
    public void error(Exception e) {
        Snackbar.make(binding.getRoot(), R.string.anon_acc_create_error, BaseTransientBottomBar.LENGTH_LONG).show();
    }
}