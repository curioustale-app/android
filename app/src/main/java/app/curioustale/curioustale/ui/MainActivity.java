package app.curioustale.curioustale.ui;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.elevation.SurfaceColors;

import app.curioustale.curioustale.R;
import app.curioustale.curioustale.config.Constants;
import app.curioustale.curioustale.databinding.ActivityMainBinding;
import app.curioustale.curioustale.repo.auth.FirebaseAuthRepository;
import app.curioustale.curioustale.utils.AlarmUtils;
import app.curioustale.curioustale.utils.PermissionUtils;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String userId = new FirebaseAuthRepository().getCurrentUser().getUid();
        Log.d(Constants.DEBUG_TAG, "User id: " + userId);
        if (!PermissionUtils.hasNotificationPermission(MainActivity.this)) {
            manageNotificationPermission(MainActivity.this);
        } else {
            setDailyAlarm();
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_my_stories,
                R.id.navigation_insights,
                R.id.navigation_settings
        ).build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void setDailyAlarm() {
        AlarmUtils alarmUtils = new AlarmUtils(MainActivity.this);
        alarmUtils.setRepeatingAlarm();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    public void manageNotificationPermission(Activity context) {
        if (PermissionUtils.requirePermissionRationale(context)) {
            new MaterialAlertDialogBuilder(context)
                    .setTitle("Requesting permission")
                    .setMessage("Enable notification permission for daily reminders.")
                    .setPositiveButton("Accept", (dialog, which) -> PermissionUtils.requestNotificationPermission(context)).show();
        } else {
            PermissionUtils.requestNotificationPermission(context);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setDailyAlarm();
        }
    }
}