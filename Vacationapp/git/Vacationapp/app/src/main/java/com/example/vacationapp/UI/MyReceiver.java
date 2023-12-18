package com.example.vacationapp.UI;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.vacationapp.R;

public class MyReceiver extends BroadcastReceiver {

    String Channel_ID = "End";
    static int notify ;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("Ending"), Toast.LENGTH_LONG).show();
        createNotificationsChannel(context, Channel_ID);
        Notification n = new NotificationCompat.Builder(context, Channel_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("Ending"))
                .setContentTitle("Notes").build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notify++, n);
    }


    private void createNotificationsChannel(Context context, String CHANNEL_ID) {
        CharSequence name = context.getResources().getString(R.string.channel2);
        String description = context.getString(R.string.channel_d2);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}

