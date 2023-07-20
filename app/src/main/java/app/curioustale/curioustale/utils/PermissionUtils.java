package app.curioustale.curioustale.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import app.curioustale.curioustale.config.Constants;

public class PermissionUtils {
    private PermissionUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean hasNotificationPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    public static boolean requirePermissionRationale(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_MEDIA_AUDIO);
        } else {
            return ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    public static void requestNotificationPermission(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                    context,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    Constants.PERMISSION_NOTIFICATION
            );
        }
    }

}
