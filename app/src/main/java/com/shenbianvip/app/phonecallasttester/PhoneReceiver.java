package com.shenbianvip.app.phonecallasttester;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Android Studio.
 * ProjectName: PhoneCallAstTester
 * Author: haozi
 * Date: 2017/11/22
 * Time: 16:44
 */

public class PhoneReceiver extends BroadcastReceiver {

    private static final String TAG = PhoneReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"[Broadcast] action"+intent.getAction());
        if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){
            // 去电，可以用定时挂断(双卡的手机可能不走这个Action)
            String phoneNumber = intent .getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Log.d(TAG, "[Broadcast] ACTION_NEW_OUTGOING_CALL:" + phoneNumber);
        }else if(intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)){
            // 来电去电都会走 (获取当前电话状态)
            Log.d(TAG, "[Broadcast] ACTION_PHONE_STATE_CHANGED");
            doReceivePhone(context,intent);
        }
    }

    /**
     * 处理电话广播.
     * @param context
     * @param intent
     */
    public void doReceivePhone(Context context, Intent intent) {
        // 获取电话号码
        String phoneNumber = intent.getStringExtra( TelephonyManager.EXTRA_INCOMING_NUMBER);
        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        int state = telephony.getCallState();
        switch(state){
            case TelephonyManager.CALL_STATE_RINGING:
                Log.i(TAG, "[Broadcast]等待接电话="+phoneNumber);
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                Log.i(TAG, "[Broadcast]电话挂断="+phoneNumber);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.i(TAG, "[Broadcast]通话中="+phoneNumber);
                break;
        }
    }

}
