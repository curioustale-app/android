package app.curioustale.curioustale;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import app.curioustale.curioustale.config.Constants;
import app.curioustale.curioustale.databinding.ActivityMainBinding;
import app.curioustale.curioustale.repo.auth.FirebaseAuthRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String userId = new FirebaseAuthRepository().getCurrentUser().getUid();
        Log.d(Constants.DEBUG_TAG, "User id: " + userId);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}