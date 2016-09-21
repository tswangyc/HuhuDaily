package com.qingxu.android.huhudaily.util;

import android.os.AsyncTask;

import com.qingxu.android.huhudaily.model.DetailBean;

/**
 * Created by lenovo on 2016/9/16.
 */
public class FetchDetailBeanTask extends AsyncTask<Integer, Void, DetailBean> {
    @Override
    protected DetailBean doInBackground(Integer... params) {
        return new DailyFetcher().fetchDetailBean(params[0]);
    }
}
