package com.qingxu.android.huhudaily.util;

import android.os.AsyncTask;

import com.qingxu.android.huhudaily.model.BeforeBean;

/**
 * Created by QingXu on 2016/9/10.
 */
public class FetchBeforeBeanTask extends AsyncTask<String, Void, BeforeBean> {

    @Override
    protected BeforeBean doInBackground(String... params) {

        return new DailyFetcher().fetchBeforeNews(params[0]);
    }
}
