package com.fede.myapplication;

import android.app.Application;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumApplication;
import com.leanplum.LeanplumPushService;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

/**
 * Created by fede on 1/13/16.
 */
public class MainApplication extends LeanplumApplication {

    @Variable
    public static String welcomeMessage = "Welcome to Leanplum";


    @Override
    public void onCreate(){
        super.onCreate();

        // Insert here your App Leanplum keys
        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("app_aDBIMk9nN4VH0zabSwtZ61Rz8RxfDIoQbXrchEikZXs", "dev_XPFus0gB0BlVUe0DCwcE4XDvZzs671SDVPZDIMjQwxI");
        } else {
            Leanplum.setAppIdForProductionMode("app_aDBIMk9nN4VH0zabSwtZ61Rz8RxfDIoQbXrchEikZXs", "prod_fG8RRLU3SaJTFSaEaoqCpXTTpVNHOFU2Qv6UhygSnY8");
        }

//        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

//        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
//            @Override
//            public void variablesChanged() {
//                Log.i("Leanplum", "####" + welcomeMessage);
//            }
//        });

    }
}
