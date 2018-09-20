package com.lp.android_mparticle_lp;

import android.app.Application;
import android.os.Bundle;
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

//        if (BuildConfig.DEBUG) {
//        options = MParticleOptions.builder(this)
//                .credentials("8358137317de2d40a186fd2c69b8c645","Mpy67u95yGxNN22XCuBItku4AxVnkbkDfuIUbB55CtWdrwU-VcGLpz8HupON3jME")
//                .environment(MParticle.Environment.Development)
//                .build();
//        } else {
//            options = MParticleOptions.builder(this)
//                    .credentials("8358137317de2d40a186fd2c69b8c645","Mpy67u95yGxNN22XCuBItku4AxVnkbkDfuIUbB55CtWdrwU-VcGLpz8HupON3jME")
//                    .environment(MParticle.Environment.Production)
//                    .build();
//        }

        options = MParticleOptions.builder(this)
                .credentials("<API_KEY>","<API_KEY_SECRET>")
                .build();


        // Leanplum Parser - this is for detecting the LP variables defined in other classes (in this case in Lpvariables.class)
        // Need to be executed before Leanplum starts
        Parser.parseVariablesForClasses(Lpvariables.class);

        // Enabling Firebase
        LeanplumPushService.enableFirebase();

        // Leanplum setCustomizer to, in this case, change the default small icon of the Push Notification
        LeanplumPushService.setCustomizer(new LeanplumPushNotificationCustomizer() {
            @Override
            public void customize(NotificationCompat.Builder builder, Bundle bundle) {
                builder.setSmallIcon(R.drawable.androidbnw);
            }
        });

        // Leanplum Start callback
        Leanplum.addStartResponseHandler(new StartCallback() {
            @Override
            public void onResponse(boolean b) {
                // Once Leanplum has started, following code will be executed
                Log.i("### ", "Leanplum has started");
            }
        });

        // Starting MParticle - this will also start Leanplum
        MParticle.start(options);

    }
}
