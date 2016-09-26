package com.qingxu.android.huhudaily.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.qingxu.android.huhudaily.R;
import com.qingxu.android.huhudaily.receiver.NetworkStateReceiver;

/**
 * Created by QingXu on 2016/9/22.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = "BaseActivity";
    public static final int REQUEST_FAILURE = 0x00;
    public static final int REQUEST_SUCCESS = 0x01;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REQUEST_FAILURE:
                    onRequestFailure();
                    break;
                case REQUEST_SUCCESS:
                    onRequestSuccess();
                    break;
            }
        }
    };
    private BroadcastReceiver mReceiver;
    private IntentFilter mFilter;

    protected abstract void doBusiness();

    protected abstract int getLayoutRes();

    private void onRequestSuccess() {
        doBusiness();
    }

    private void onRequestFailure() {

        setContentView(R.layout.activity_error);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert toolbar != null;
        toolbar.setTitle(getString(R.string.app_name));
        getSupportActionBar();

        Button errorButton = (Button) findViewById(R.id.error_button);
        assert errorButton != null;
        errorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mReceiver = new NetworkStateReceiver(mHandler);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mFilter);
        Log.d(TAG, "onResume: " + "registerReceiver");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
        Log.d(TAG, "onPause: " + "unregisterReceiver");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
