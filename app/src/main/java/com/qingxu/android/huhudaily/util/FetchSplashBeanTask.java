package com.qingxu.android.huhudaily.util;

import android.os.AsyncTask;

import com.qingxu.android.huhudaily.model.SplashBean;

/**
 * Created by QingXu on 2016/9/25.
 */
public class FetchSplashBeanTask extends AsyncTask<Void, Void, SplashBean> {

    @Override
    protected SplashBean doInBackground(Void... params) {

        return new DailyFetcher().fetchSplash();
    }

}
