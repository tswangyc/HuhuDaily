package com.qingxu.android.huhudaily;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingxu.android.huhudaily.model.LatestBean;
import com.qingxu.android.huhudaily.model.SplashBean;
import com.qingxu.android.huhudaily.model.ThemeListBean;
import com.qingxu.android.huhudaily.util.DailyFetcher;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "SplashActivity";
    @BindView(R.id.splash_image)
    ImageView mSplashImage;
    @BindView(R.id.splash_text)
    TextView mSplashText;
    private SplashBean mSplashBean;
    private LatestBean mLatestBean;
    private ThemeListBean mThemeListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        new FetchSplashTask().execute();

        //传递LatestNewsInfo给MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("mLatestBean", mLatestBean);
//                bundle.putSerializable("mThemeListBean", mThemeListBean);
//                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 3000);
    }

    private class FetchSplashTask extends AsyncTask<Void, Void, SplashBean> {

        @Override
        protected SplashBean doInBackground(Void... params) {
            mSplashBean = new DailyFetcher().fetchSplash();
            return mSplashBean;
        }

        @Override
        protected void onPostExecute(SplashBean splashBean) {
            if (splashBean == null) {
                return;
            }
            Picasso.with(getApplicationContext())
                    .load(splashBean.getImg())
                    .placeholder(R.color.colorSplash)
                    .into(mSplashImage);
            mSplashText.setText(splashBean.getText());
        }
    }

}
