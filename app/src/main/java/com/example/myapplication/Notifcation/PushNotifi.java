package com.example.myapplication.Notifcation;

import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.R;
import com.google.firebase.messaging.FirebaseMessagingService;

import static com.example.myapplication.dashbord.Dashbord.CHANNEL_ID;

public class PushNotifi extends FirebaseMessagingService {
public static void displayNotification(Context context, String title, String body){
    NotificationCompat.Builder mBuilder=
            new NotificationCompat.Builder(context,CHANNEL_ID)
            .setSmallIcon(R.drawable.bulk_heading_img)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    NotificationManagerCompat managerCompat=NotificationManagerCompat.from(context);
    managerCompat.notify(1,mBuilder.build());
}
}
