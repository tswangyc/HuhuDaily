package com.qingxu.android.huhudaily.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.qingxu.android.huhudaily.activity.BaseActivity;

public class NetworkStateReceiver extends BroadcastReceiver {
    public static final String TAG = "NetworkStateReceiver";
    private Handler mHandler;

    public NetworkStateReceiver(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            Log.d(TAG, "网络状态已经改变");
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            connectivityManager.getActiveNetworkInfo();
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            Message message = Message.obtain();
            if (networkInfo != null && networkInfo.isAvailable()) {
                String name = networkInfo.getTypeName();
                Log.d(TAG, "当前网络名称: " + name);
                message.what = BaseActivity.REQUEST_SUCCESS;
            } else {
                Log.d(TAG, "没有可用网络");
                message.what = BaseActivity.REQUEST_FAILURE;
            }
            mHandler.sendMessage(message);
        }
    }
}
