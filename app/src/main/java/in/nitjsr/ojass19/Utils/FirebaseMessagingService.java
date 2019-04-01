package in.nitjsr.ojass19.Utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import in.nitjsr.ojass19.Activity.HomeActivity;
import in.nitjsr.ojass19.R;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG ="FirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG,"FROM :"+ remoteMessage.getFrom());

        if(remoteMessage.getData().size()>0) {
            Log.d(TAG,"Message : "+ remoteMessage.getData());
        }
        if(remoteMessage.getNotification()!=null)
        {
            Log.d(TAG,"Message Body :"+ remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getBody());
        }


    }

    private void handleNotification(String body) {
        playRingtone();
        Intent intent =new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notifiBuilder= new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ojass_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ojass_icon))
                .setContentTitle("Ojass")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notifiBuilder.build());
    }

    private void playRingtone() {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),uri);
        ringtone.play();
    }
}
