package com.qingxu.android.huhudaily.util;

import android.os.AsyncTask;

import com.qingxu.android.huhudaily.model.UpdateBean;

/**
 * Created by QingXu on 2016/9/21.
 */

public class FetchUpdateBeanTask extends AsyncTask<String, Void, UpdateBean> {
    @Override
    protected UpdateBean doInBackground(String... params) {
        return new DailyFetcher().fetchUpdateBean(params[0]);
    }
}
