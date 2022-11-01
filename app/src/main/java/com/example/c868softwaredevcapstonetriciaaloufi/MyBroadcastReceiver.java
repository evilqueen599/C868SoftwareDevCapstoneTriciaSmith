package com.example.c868softwaredevcapstonetriciaaloufi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


public class MyBroadcastReceiver extends BroadcastReceiver {
    String channel_id = "test";
    static int notificationID;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_NOTIFICATION_URI);
        mediaPlayer.start();
        Toast.makeText(context, intent.getStringExtra("key"), Toast.LENGTH_LONG).show();
        createNotificationChannel(context, channel_id);
        Notification n = new NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.drawable.ic_book_icon)
                .setContentText(intent.getStringExtra("key"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentTitle("Notification").build();
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(notificationID++, n);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(Context context, String CHANNEL_ID) {
        CharSequence name = context.getResources().getString(R.string.channel_name);
        String description = context.getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
