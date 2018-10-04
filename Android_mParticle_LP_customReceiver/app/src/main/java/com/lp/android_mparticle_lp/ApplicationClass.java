package com.lp.android_mparticle_lp;

import android.app.Application;
import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumPushNotificationCustomizer;
import com.leanplum.LeanplumPushService;
import com.leanplum.annotations.Parser;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.mparticle.MParticle;
import com.mparticle.MParticleOptions;

/**
 * Created by fede on 5/22/18.
 */

public class ApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MParticleOptions options;

        // Example to explicitate the build type - Dev. vs Prod.

        options = MParticleOptions.builder(this)
                .credentials("<mParticle_APP_KEY>","mParticle_CLient_Key")
                .build();


        // Leanplum Parser - this is for detecting the LP variables defined in other classes (in this case in Lpvariables.class)
        // Need to be executed before Leanplum starts
        Parser.parseVariablesForClasses(Lpvariables.class);

        // Enabling Firebase
//        LeanplumPushService.enableFirebase();

        // Leanplum setCustomizer to, in this case, change the default small icon of the Push Notification


        LeanplumPushService.setCustomizer(new LeanplumPushNotificationCustomizer() {
            @Override
            public void customize(NotificationCompat.Builder builder, Bundle bundle) {
                builder.setSmallIcon(R.drawable.androidbnw);
            }

            @Override
            public void customize(Notification.Builder builder, Bundle bundle, @Nullable Notification.Style style) {

            }
        });

        // Starting MParticle - this will also start Leanplum
        MParticle.start(options);
    }
}