package com.fede.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumPushService;
import com.leanplum.callbacks.VariablesChangedCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fede on 1/13/16.
 */
public class MainActivity extends Activity {

    public String newUserId = "FedeTest_NewUserID_9";
    public String newCustomSenderId = "222286048049";

    public boolean loggedIn;
    public boolean leanplumPush = true;

    Map<String, Object> personAttributes = new HashMap<String, Object>();
    Map<String, Object> loginAttributes = new HashMap<String, Object>();

    public void serverSwitchLoginLogout(View view){
        if (loggedIn){
            loggedIn = false;
        }
        else {
            loggedIn = true;
        }
        Log.i("#### UserLoggedIn:", String.valueOf(loggedIn));
    }

    public void setUserID(View view) {
        loginAttributes.put("LoggedIn", "true");
        Leanplum.setUserAttributes(loginAttributes);
        Leanplum.setUserId(newUserId);
    }

    public void setUserAttribute(View view) {
        personAttributes.put("gender", "Female");
        personAttributes.put("age", 29);
        Leanplum.setUserAttributes(personAttributes);

    }
    public void forceContentUpdate(View view) {
        Leanplum.forceContentUpdate(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("### Leanplum", "### Content Updated!");
            }
        });
    }

    public void registerForPush(View view){
        // Note - this is doing nothing at the moment since Registering for Push is not possible after Leanplum.Start
        if (leanplumPush){
            Log.i("### Leanplum", "Registering for Push Using Leanplum SenderID");
            LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        }
        else {
            Log.i("### Leanplum", "Registering for Push Using CustomProject SenderID");
            LeanplumPushService.setGcmSenderId(newCustomSenderId);
        }
    }


    public void changeSenderId(View view){
        if (leanplumPush){
            leanplumPush = false;
        }
        else {
            leanplumPush = true;
        }
        Log.i("### LeanplumPush?: ", String.valueOf(leanplumPush));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Leanplum.setDeviceId("CustomDeviceID_20160114_9");

        Log.i("### UserLoggedIn: ", String.valueOf(loggedIn));
        Log.i("### LeanplumPush?: ", String.valueOf(leanplumPush));

        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        Leanplum.start(this);

    }
}
