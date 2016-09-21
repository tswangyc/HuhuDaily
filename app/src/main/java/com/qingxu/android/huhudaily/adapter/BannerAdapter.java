package com.qingxu.android.huhudaily.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.qingxu.android.huhudaily.model.TopStoriesBean;
import com.qingxu.android.huhudaily.view.BannerFragment;

import java.util.List;

/**
 * Created by QingXu on 2016/9/14.
 */
public class BannerAdapter extends FragmentStatePagerAdapter {
    List<TopStoriesBean> mTopStoriesBeans;

    public BannerAdapter(FragmentManager fm, List<TopStoriesBean> topStoriesBeans) {
        super(fm);
        mTopStoriesBeans = topStoriesBeans;
    }

    @Override
    public Fragment getItem(int position) {
        TopStoriesBean topStoriesBean = mTopStoriesBeans.get(position);
        return BannerFragment.newInstance(topStoriesBean);
    }

    @Override
    public int getCount() {
        return mTopStoriesBeans.size();
    }
}
