package com.qingxu.android.huhudaily.activity;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingxu.android.huhudaily.R;
import com.qingxu.android.huhudaily.model.SplashBean;
import com.qingxu.android.huhudaily.util.FetchSplashBeanTask;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    public static final String TAG = "SplashActivity";
    @BindView(R.id.splash_image)
    ImageView mSplashImage;
    @BindView(R.id.splash_text)
    TextView mSplashText;
    private SplashBean mSplashBean;

    private void startMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 3000);
    }


    @Override
    protected void doBusiness() {

        setContentView(getLayoutRes());
        ButterKnife.bind(this);

        try {
            mSplashBean = new FetchSplashBeanTask().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        Picasso.with(SplashActivity.this)
                .load(mSplashBean.getImg())
                .placeholder(R.color.colorSplash)
                .into(mSplashImage);
        mSplashText.setText(mSplashBean.getText());
        startMainActivity();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

}
