package com.qingxu.android.huhudaily.util;

import android.os.AsyncTask;

import com.qingxu.android.huhudaily.model.LatestBean;

/**
 * Created by QingXu on 2016/9/13.
 */
public class FetchLatestBeanTask extends AsyncTask<Void, Void, LatestBean> {
    @Override
    protected LatestBean doInBackground(Void... params) {
        return new DailyFetcher().fetchLatestNews();
    }
}
