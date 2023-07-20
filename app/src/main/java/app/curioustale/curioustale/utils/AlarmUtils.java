package app.curioustale.curioustale.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import app.curioustale.curioustale.config.Constants;
import app.curioustale.curioustale.receiver.AlarmReceiver;

public class AlarmUtils {
    private final AlarmManager alarmManager;
    private final PendingIntent alarmIntent;

    public AlarmUtils(Context context) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.alarmIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_IMMUTABLE);
    }

    public void setRepeatingAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                alarmIntent
        );
        Log.d(Constants.DEBUG_TAG, "alarm set!");
    }
}
