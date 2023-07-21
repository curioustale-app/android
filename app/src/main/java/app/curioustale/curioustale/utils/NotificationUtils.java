package app.curioustale.curioustale.utils;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import app.curioustale.curioustale.R;
import app.curioustale.curioustale.config.Constants;
import app.curioustale.curioustale.ui.MainActivity;

public class NotificationUtils {
    private final String channelId;
    private final Context context;
    private final NotificationManagerCompat notificationManager;

    public NotificationUtils(Context context) {
        this.context = context;
        this.notificationManager = NotificationManagerCompat.from(context);
        this.channelId = context.getString(R.string.notification_channel);
    }

    public void launchNotification() {
        createNotificationChannel();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(0, createNotification());
        }
    }

    private void createNotificationChannel() {
        if (notificationManager.getNotificationChannel(channelId) == null) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(context.getString(R.string.notification_category_desc));
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private Notification createNotification() {
        Intent homeIntent = new Intent(context, MainActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(context, Constants.REMINDER_NOTIFICATION_ID, homeIntent, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId);
        return notificationBuilder
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_my_stories)
                .setContentIntent(contentIntent)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_subtitle))
                .build();
    }

}
