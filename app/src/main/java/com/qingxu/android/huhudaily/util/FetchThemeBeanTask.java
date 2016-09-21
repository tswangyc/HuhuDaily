package com.qingxu.android.huhudaily.util;

import android.os.AsyncTask;

import com.qingxu.android.huhudaily.model.ThemeBean;

/**
 * Created by lenovo on 2016/9/17 017.
 */
public class FetchThemeBeanTask extends AsyncTask<Integer, Void, ThemeBean> {
    @Override
    protected ThemeBean doInBackground(Integer... params) {
        return new DailyFetcher().fetchThemeBean(params[0]);
    }
}
