package com.shashankbhat.musicplayer.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;

import com.shashankbhat.musicplayer.MainActivity;
import com.shashankbhat.musicplayer.R;
import com.shashankbhat.musicplayer.data.Song;
import com.shashankbhat.musicplayer.broadcast.NotificationActionReceiver;
import com.shashankbhat.musicplayer.utils.UniqueMediaPlayer;

import static com.shashankbhat.musicplayer.application.App.CHANNEL_ID;
import static com.shashankbhat.musicplayer.utils.Constants.SONG;

public class CreateNotification {

    public static Notification sendOnChannel(Context context, Song song) {

        Intent intentPlay = new Intent(context, NotificationActionReceiver.class);
        intentPlay.putExtra(SONG, song);

        PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay, PendingIntent.FLAG_CANCEL_CURRENT);

        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        mainActivityIntent.putExtra(SONG, song);
        PendingIntent mainActivityPI = PendingIntent.getActivity(context, 0, mainActivityIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(song.getSongName())
                .setContentText(song.getSongArtist())
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle())
                .setContentIntent(mainActivityPI);

        if(UniqueMediaPlayer.getMediaPlayer().isPlaying()) {
            notificationBuilder.setOngoing(true);
            notificationBuilder.addAction(R.drawable.ic_pause, "Pause", pendingIntentPlay);
        } else
            notificationBuilder.addAction(R.drawable.ic_play,"Play", pendingIntentPlay);

        return notificationBuilder.build();
    }


}