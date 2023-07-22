package app.curioustale.curioustale.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import app.curioustale.curioustale.R;
import app.curioustale.curioustale.databinding.ActivitySplashBinding;
import app.curioustale.curioustale.repo.auth.AuthRepository;
import app.curioustale.curioustale.repo.auth.FirebaseAuthRepository;

public class Splash extends AppCompatActivity implements AuthRepository.AnonymousSignInListener<FirebaseUser> {
    private ActivitySplashBinding binding;
    private AuthRepository<FirebaseUser> authRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnGetStarted.setOnClickListener(this::handleGetStarted);
        authRepository = new FirebaseAuthRepository();
        if (authRepository.isUserSignedIn()) {
            navigateToDashboard();
        }
    }

    private void handleGetStarted(View view) {
        binding.btnGetStarted.setEnabled(false);
        binding.btnGetStarted.setText(R.string.starting_now_text);
        authRepository.signInAnonymously(this);
    }

    private void navigateToDashboard() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void success(FirebaseUser user) {
        navigateToDashboard();
    }

    @Override
    public void error(Exception e) {
        binding.btnGetStarted.setEnabled(true);
        binding.btnGetStarted.setText(R.string.get_started_btn_text);
        Snackbar.make(binding.getRoot(), R.string.anon_acc_create_error, BaseTransientBottomBar.LENGTH_LONG).show();
    }
}