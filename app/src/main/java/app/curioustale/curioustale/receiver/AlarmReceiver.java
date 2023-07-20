package app.curioustale.curioustale.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import app.curioustale.curioustale.utils.NotificationUtils;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationUtils notificationUtils = new NotificationUtils(context);
        notificationUtils.launchNotification();
    }
}
