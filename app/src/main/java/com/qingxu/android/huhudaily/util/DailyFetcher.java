package com.qingxu.android.huhudaily.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.qingxu.android.huhudaily.model.BeforeBean;
import com.qingxu.android.huhudaily.model.DetailBean;
import com.qingxu.android.huhudaily.model.LatestBean;
import com.qingxu.android.huhudaily.model.SplashBean;
import com.qingxu.android.huhudaily.model.StoryExtraBean;
import com.qingxu.android.huhudaily.model.ThemeBean;
import com.qingxu.android.huhudaily.model.ThemeListBean;
import com.qingxu.android.huhudaily.model.UpdateBean;

/**
 * Created by QingXu on 2016/9/7.
 */
public class DailyFetcher {

    public static final String TAG = "DailyFetcher";
    //1. 启动界面图像获取
    private static final String SPLASH_URL = "http://news-at.zhihu.com/api/4/start-image/1080*1776";
    //2. 软件版本查询
    private static final String UPDATE_URL = "http://news-at.zhihu.com/api/4/version/android/";
    //3. 最新消息
    private static final String NEWS_LATEST_URL = "http://news-at.zhihu.com/api/4/news/latest";
    //4. 消息内容获取与离线下载http://news-at.zhihu.com/api/4/news/#{id}
    private static final String NEWS_GET_BY_ID_URL = "http://news-at.zhihu.com/api/4/news/";
    //5. 过往消息http://news.at.zhihu.com/api/4/news/before/#{date}
    private static final String NEWS_BEFORE_URL = "http://news.at.zhihu.com/api/4/news/before/";
    //6. 新闻额外信息 http://news-at.zhihu.com/api/4/story-extra/#{id}
    private static final String NEWS_EXTRA_URL = "http://news-at.zhihu.com/api/4/story-extra/";
    //7. 新闻对应长评论查看 http://news-at.zhihu.com/api/4/story/#{id}/long-comments
    //8. 新闻对应短评论查看http://news-at.zhihu.com/api/4/story/#{id}/short-comments
    private static final String NEWS_COMMENTS_URL = "http://news-at.zhihu.com/api/4/story/";
    //9. 主题日报列表查看
    private static final String NEWS_THEMES_URL = "http://news-at.zhihu.com/api/4/themes";
    //10. 主题日报内容查看http://news-at.zhihu.com/api/4/theme/#{id}
    private static final String NEWS_THEMES_BY_ID_URL = "http://news-at.zhihu.com/api/4/theme/";

    /**
     * @return SplashBean
     */
    public SplashBean fetchSplash() {
        String response = HttpRequestUtil.sendGet(SPLASH_URL, null);
        SplashBean splashBean = JSON.parseObject(response, SplashBean.class);
        Log.d(TAG, "fetchSplash: " + splashBean);
        return splashBean;
    }

    /**
     * @return LatestBean
     */
    public LatestBean fetchLatestNews() {
        String response = HttpRequestUtil.sendGet(NEWS_LATEST_URL, null);
        LatestBean latestBean = JSON.parseObject(response, LatestBean.class);
        Log.d(TAG, "fetchLatestNews: " + latestBean);
        return latestBean;
    }

    /**
     * @return BeforeBean
     */
    public BeforeBean fetchBeforeNews(String date) {
        String response = HttpRequestUtil.sendGet(NEWS_BEFORE_URL + date, null);
        BeforeBean beforeBeforeBean = JSON.parseObject(response, BeforeBean.class);
        Log.d(TAG, "fetchBeforeNews: " + beforeBeforeBean);
        return beforeBeforeBean;
    }

    /**
     * @return ThemeListBean
     */
    public ThemeListBean fetchThemeList() {
        String response = HttpRequestUtil.sendGet(NEWS_THEMES_URL, null);
        ThemeListBean themeListBean = JSON.parseObject(response, ThemeListBean.class);
        Log.d(TAG, "fetchThemeList: " + themeListBean);
        return themeListBean;
    }

    /**
     * @param id
     * @return DetailBean
     */
    public DetailBean fetchDetailBean(int id) {
        String response = HttpRequestUtil.sendGet(NEWS_GET_BY_ID_URL + id, null);
        DetailBean detailBean = JSON.parseObject(response, DetailBean.class);
        Log.d(TAG, "fetchDetailBean: " + detailBean);
        return detailBean;
    }

    /**
     * @param id
     * @return ThemeBean
     */
    public ThemeBean fetchThemeBean(int id) {
        String response = HttpRequestUtil.sendGet(NEWS_THEMES_BY_ID_URL + id, null);
        ThemeBean themeBean = JSON.parseObject(response, ThemeBean.class);
        Log.d(TAG, "fetchThemeBean: " + themeBean);
        return themeBean;
    }

    /**
     * @param id
     * @return StoryExtraBean
     */
    public StoryExtraBean fetchStoryExtraBean(int id) {
        String response = HttpRequestUtil.sendGet(NEWS_EXTRA_URL + id, null);
        StoryExtraBean storyExtraBean = JSON.parseObject(response, StoryExtraBean.class);
        Log.d(TAG, "fetchStoryExtraBean: " + storyExtraBean);
        return storyExtraBean;
    }

    public UpdateBean fetchUpdateBean(String version) {
        String response = HttpRequestUtil.sendGet(UPDATE_URL + version, null);
        UpdateBean updateBean = JSON.parseObject(response, UpdateBean.class);
        Log.d(TAG, "fetchUpdateBean: " + updateBean);
        return updateBean;
    }

}
