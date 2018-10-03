package com.lp.android_mparticle_lp;

import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.leanplum.LeanplumPushFirebaseMessagingService;

/**
 * Created by fede on 3/13/17.
 */

public class Custom_FirebaseMessagingService extends LeanplumPushFirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        boolean isLeanPlumPushNotification = (remoteMessage.getData().containsKey("lp_version"));

        if (isLeanPlumPushNotification) {
            Log.i("### ", "LP notification: " + String.valueOf(isLeanPlumPushNotification));
            // Code to be executed in case of a Leanplum Notification
            // Calling 'onMessageReceived' from the parent class
            super.onMessageReceived(remoteMessage);
        }
        else {
            // Code to be executed in case of a non-Leanplum Notification
            Log.i("##### ", "Not a LP notification");
        }
    }
}
