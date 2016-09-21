package com.qingxu.android.huhudaily.util;

import android.os.AsyncTask;

import com.qingxu.android.huhudaily.model.StoryExtraBean;

/**
 * Created by QingXu on 2016/9/20.
 */

public class FetchStoryExtraBeanTask extends AsyncTask<Integer, Void, StoryExtraBean> {

    @Override
    protected StoryExtraBean doInBackground(Integer... params) {
        return new DailyFetcher().fetchStoryExtraBean(params[0]);
    }
}
