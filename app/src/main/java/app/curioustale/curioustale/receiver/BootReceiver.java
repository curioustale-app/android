package app.curioustale.curioustale.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import app.curioustale.curioustale.utils.AlarmUtils;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            new AlarmUtils(context).setRepeatingAlarm();
        }
    }
}
