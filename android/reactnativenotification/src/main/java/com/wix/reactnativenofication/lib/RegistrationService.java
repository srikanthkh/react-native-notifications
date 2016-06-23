package com.wix.reactnativenofication.lib;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

public class RegistrationService extends IntentService {

    private final String TAG = this.getClass().getSimpleName();

    public RegistrationService() {
        super("com.wix.reactnativenofication.lib.RegistrationService");
    }
 
    @Override
    protected void onHandleIntent(Intent intent) {
        final String senderId = intent.getStringExtra("senderId");

        final InstanceID instanceId = InstanceID.getInstance(this);
        String tokenId = null;
        try {
            tokenId = instanceId.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.i(TAG, "Received new token: "+tokenId);
        } catch (IOException e) {
            Log.e(TAG, "Failed to fetch token, intent="+intent, e);
            return;
        }

        // TODO somehow publish new token to React
    }
}