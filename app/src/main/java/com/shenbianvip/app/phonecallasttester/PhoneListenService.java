package com.shenbianvip.app.phonecallasttester;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Android Studio.
 * ProjectName: PhoneCallAstTester
 * Author: haozi
 * Date: 2017/11/22
 * Time: 16:56
 */

public class PhoneListenService extends Service {

    public static final String TAG = PhoneListenService.class.getSimpleName();

    public static final String ACTION_REGISTER_LISTENER = "action_register_listener";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand action: " + intent.getAction() + " flags: " + flags + " startId: " + startId);
        String action = intent.getAction();
        if (action.equals(ACTION_REGISTER_LISTENER)) {
            registerPhoneStateListener();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void registerPhoneStateListener() {
        MyPhoneStateListener customPhoneStateListener = new MyPhoneStateListener(this);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            telephonyManager.listen(customPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }
}
