package app.curioustale.curioustale;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import app.curioustale.curioustale.databinding.ActivitySplashBinding;
import app.curioustale.curioustale.repo.auth.AuthRepository;
import app.curioustale.curioustale.repo.auth.FirebaseAuthRepository;

public class Splash extends AppCompatActivity implements AuthRepository.AnonymousSignInListener<FirebaseUser> {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseAuthRepository authRepository = new FirebaseAuthRepository();

        if (authRepository.isUserSignedIn()) {
            navigateToDashboard();
        }
        else {
            authRepository.signAnonymously(this);
        }
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