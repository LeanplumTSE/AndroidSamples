package com.lp.android_mparticle_lp;

import com.leanplum.LeanplumPushFcmListenerService;

/**
 * Created by fede on 3/13/17.
 */

public class Custom_PushFcmListenerService extends LeanplumPushFcmListenerService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
    }
}
