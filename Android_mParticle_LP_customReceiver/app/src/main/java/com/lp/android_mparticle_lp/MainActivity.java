package com.lp.android_mparticle_lp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.leanplum.Leanplum;
import com.leanplum.callbacks.StartCallback;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.mparticle.MPEvent;
import com.mparticle.MParticle;
import com.mparticle.MParticleTask;
import com.mparticle.commerce.CommerceEvent;
import com.mparticle.commerce.Product;
import com.mparticle.commerce.TransactionAttributes;
import com.mparticle.identity.IdentityApiRequest;
import com.mparticle.identity.IdentityApiResult;
import com.mparticle.identity.IdentityHttpResponse;
import com.mparticle.identity.MParticleUser;
import com.mparticle.identity.TaskFailureListener;
import com.mparticle.identity.TaskSuccessListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    MParticleUser currentUser = MParticle.getInstance().Identity().getCurrentUser();
    public static EditText mEdit;
    public static EditText getmEdit() {
        return mEdit;
    }




    public void login (View view) {
        mEdit = findViewById(R.id.loginField);

        Log.i("#### ", "here 0");

        IdentityApiRequest apiRequest = IdentityApiRequest.withEmptyUser()
                //the IdentityApiRequest provide convenience methods for common identity types
                .email("foo@example.com")
                .customerId(mEdit.getText().toString())
                //alternatively, you can use the setUserIdentity method and supply the MPUserIdentity type
                .userIdentity(MParticle.IdentityType.Other, "bar-id")
                .build();

        Log.i("#### ", "here 1");

        MParticle.getInstance().Identity().login(apiRequest)
                .addFailureListener(new TaskFailureListener() {
                    @Override
                    public void onFailure(IdentityHttpResponse identityHttpResponse) {
                        //device may be offline and request should be retried - see below.
                    }
                })
                .addSuccessListener(new TaskSuccessListener() {
                    @Override
                    public void onSuccess(IdentityApiResult identityApiResult) {
                        //Continue with login, and you can also access the new/updated user:
                        MParticleUser user = identityApiResult.getUser();
                    }
                });

    }

    public void trackPurchase (View view) {
        // Start Commerce event
        // Creating the product
        Product product = new Product.Builder("Foo name", "Foo sku", 100.00)
                .quantity(4)
                .build();

        // Create a Transaction ID - seems to be required by mParticle
        // TransactionAttributes are going to be passed as Event parameters
        TransactionAttributes attributes = new TransactionAttributes("foo-transaction-id")
                .setRevenue(450.00)
                .setTax(30.00)
                .setShipping(20.00);

        // Tracking the purchase event
        CommerceEvent event = new CommerceEvent.Builder(Product.PURCHASE, product)
                .transactionAttributes(attributes)
                .currency("EUR") // Currency can be passed as an event parameter but is NOT going to do an automatic conversion
                .build();

        MParticle.getInstance().logEvent(event);
        // End Commerce event


        // mParticle event test start
        Map<String, String> values = new HashMap<>();
        values.put("Lifetime Value", "12345");
//        tagEvent("Insufficient Beans Alert", values);

        MPEvent eventTest = new MPEvent.Builder("Food Order", MParticle.EventType.Transaction)
                .duration(100)
                .info(values)
                .category("Delivery")
                .build();

        MParticle.getInstance().logEvent(eventTest);
        // mParticle event test end



        Map<String, String> eventInfo = new HashMap<String, String>(2);
        eventInfo.put("spice", "hot");
        eventInfo.put("Lifetime Value", "LifetimeValue_Value");

        MPEvent eventTest2 = new MPEvent.Builder("LifetimeValue Value", MParticle.EventType.Transaction)
                .duration(100)
                .info(eventInfo)
                .category("Delivery")
                .build();

        MParticle.getInstance().logEvent(eventTest2);

    }


    public void setUA (View view) {
        MParticleUser currentUser = MParticle.getInstance().Identity().getCurrentUser();
//query for the unique mParticle ID of this user
        long mpid = currentUser.getId();
//Update user attributes associated with the user
        currentUser.setUserAttribute("Lifetime Value Test_Aaaaa_Bbbbb","Lifetime Value Test_Aaaa_Bbbb");
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("### Leanplum", "Variables are synced - " + Lpvariables.welcomeString );
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}