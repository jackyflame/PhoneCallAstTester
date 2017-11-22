package com.shenbianvip.app.phonecallasttester;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Android Studio.
 * ProjectName: PhoneCallAstTester
 * Author: haozi
 * Date: 2017/11/22
 * Time: 16:52
 */

public class MyPhoneStateListener extends PhoneStateListener{

    private static final String TAG = MyPhoneStateListener.class.getSimpleName();
    private Context mContext;

    public MyPhoneStateListener(Context  context) {
        mContext = context;
    }

    @Override
    public void onServiceStateChanged(ServiceState serviceState) {
        super.onServiceStateChanged(serviceState);
        Log.d(TAG, "MyPhoneStateListener onServiceStateChanged: " + serviceState);
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        Log.i(TAG, "[Listener]电话号码:"+incomingNumber);
        switch(state){
            case TelephonyManager.CALL_STATE_RINGING:
                Log.i(TAG, "[Listener]等待接电话:"+incomingNumber);
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                Log.i(TAG, "[Listener]电话挂断:"+incomingNumber);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.i(TAG, "[Listener]通话中:"+incomingNumber);
                break;
        }
        super.onCallStateChanged(state, incomingNumber);
    }

}
