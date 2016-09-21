package com.qingxu.android.huhudaily.util;

import android.os.AsyncTask;

import com.qingxu.android.huhudaily.model.ThemeListBean;

/**
 * Created by QingXu on 2016/9/19.
 */
public class FetchThemeListBeanTask extends AsyncTask<Void, Void, ThemeListBean> {
    @Override
    protected ThemeListBean doInBackground(Void... params) {

        return new DailyFetcher().fetchThemeList();
    }
}
