package com.qingxu.android.huhudaily.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qingxu.android.huhudaily.R;
import com.qingxu.android.huhudaily.adapter.BannerAdapter;
import com.qingxu.android.huhudaily.adapter.DailyAdapter;
import com.qingxu.android.huhudaily.model.BeforeBean;
import com.qingxu.android.huhudaily.model.LatestBean;
import com.qingxu.android.huhudaily.model.OthersBean;
import com.qingxu.android.huhudaily.model.StoriesBean;
import com.qingxu.android.huhudaily.model.ThemeListBean;
import com.qingxu.android.huhudaily.model.TopStoriesBean;
import com.qingxu.android.huhudaily.util.FetchBeforeBeanTask;
import com.qingxu.android.huhudaily.util.FetchLatestBeanTask;
import com.qingxu.android.huhudaily.util.FetchThemeListBeanTask;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MainActivity";
    public static final int SWIPEREFRESH = 0x00;
    public static final int STARTTIMER = 0x02;
    private static final int LOADMORE = 0x01;
    private LatestBean mLatestBean;
    private List<TopStoriesBean> mTopStoriesBeans;
    private ThemeListBean themeListBean;
    private OthersBean othersBean;
    private List<OthersBean> othersBeans;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private DailyAdapter mDailyAdapter;
    private List<StoriesBean> mDatas;
    private int lastVisibleItemPosition;

    private View bannerView;
    private ViewPager mBannerPager;
    private PagerAdapter mBannerAdapter;
    private RadioGroup indicatorGroup;
    private Timer mTimer;

    private boolean isNightMode;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case STARTTIMER:
                    if (mTopStoriesBeans.size() == 0)
                        return;
                    int curIndex = (mBannerPager.getCurrentItem() + 1) % mTopStoriesBeans.size();
                    mBannerPager.setCurrentItem(curIndex);
                    break;
                case SWIPEREFRESH:
                    mDailyAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                    break;
                case LOADMORE:
                    mDailyAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void doBusiness() {
        setContentView(getLayoutRes());
        //夜间模式设置http://www.open-open.com/lib/view/open1456843724062.html
        //OptionMenu中切换夜间模式
        isNightMode = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("isNightMode", false);

        try {
            mLatestBean = new FetchLatestBeanTask().execute().get();
            themeListBean = new FetchThemeListBeanTask().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (mLatestBean == null) {
            return;
        }
        mTopStoriesBeans = mLatestBean.getTop_stories();
        othersBeans = themeListBean.getOthers();
        Log.d(TAG, "mThemesBean: " + themeListBean);

        //填充mDatas
        mDatas = mLatestBean.getStories();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //抽屉
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        for (int i = 0; i < othersBeans.size(); i++) {
            othersBean = othersBeans.get(i);
            int itemId = othersBean.getId();
            String name = othersBean.getName();
            navigationView.getMenu().add(0, itemId, i, name).setCheckable(true);
        }

        ImageView nav_header_icon_imageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.nav_header_icon_imageView);
        nav_header_icon_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODU
            }
        });

        //配置SwipeRefreshLayout
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                reLoadLatestNews();
            }
        });
        bannerView = LayoutInflater.from(this).inflate(R.layout.banner_layout, null);

        //配置indicator
        indicatorGroup = (RadioGroup) bannerView.findViewById(R.id.indicator_group);
        for (int i = 0; i < mTopStoriesBeans.size(); i++) {
            RadioButton indicatorView = new RadioButton(this);
            RadioGroup.LayoutParams params;
            params = new RadioGroup.LayoutParams(16, 16);
            params.leftMargin = 8;
            indicatorView.setBackgroundResource(R.drawable.banner_point);
            indicatorView.setLayoutParams(params);
            indicatorView.setChecked(false);
            indicatorGroup.addView(indicatorView);
        }

        //配置viewpager
        mBannerPager = (ViewPager) bannerView.findViewById(R.id.banner_view_pager);

        mBannerAdapter = new BannerAdapter(getSupportFragmentManager(), mTopStoriesBeans);
        mBannerPager.setAdapter(mBannerAdapter);
        mBannerPager.setCurrentItem(0);
        ((RadioButton) indicatorGroup.getChildAt(0)).setChecked(true);
        startTimer();

        mBannerPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) indicatorGroup.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //配置RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mDailyAdapter = new DailyAdapter();
        assert mRecyclerView != null;
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mDailyAdapter);

        mDailyAdapter.setPagerView(bannerView, 0);
        addHeader(mLatestBean.getDate());

        mDailyAdapter.addDatas(mDatas);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mDailyAdapter.getItemCount()) {

                    try {
                        String date;
                        if (mDailyAdapter.getHeaderView() < 2) {
                            date = mLatestBean.getDate();

                        } else {
                            date = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("before_date", "");
                        }
                        BeforeBean beforeBean = new FetchBeforeBeanTask().execute(date).get();
                        List<StoriesBean> storiesBeans = beforeBean.getStories();
                        PreferenceManager.getDefaultSharedPreferences(MainActivity.this)
                                .edit()
                                .putString("before_date", beforeBean.getDate())
                                .apply();

                        String getDate = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("before_date", "");
                        addHeader(getDate);
                        mDailyAdapter.addDatas(storiesBeans);

                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }

                }

                Message message = Message.obtain();
                message.what = LOADMORE;
                mHandler.sendMessage(message);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onDestroy() {
        // isStop = true;
        stopTimer();
        super.onDestroy();
    }

    /**
     * 开始自动滚动任务，图片大于1张滚动
     */
    public void startTimer() {
        if (mTimer == null && mTopStoriesBeans.size() > 1) {
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                public void run() {
                    Message message = Message.obtain();
                    message.what = STARTTIMER;
                    mHandler.sendMessage(message);
                }
            }, 5000, 5000);
        }
    }

    /**
     * 停止自动滚动任务
     */
    public void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    private void addHeader(String s) {
        mDailyAdapter.setHeaderView(s);
    }

    /**
     * 下拉刷新
     */
    private void reLoadLatestNews() {

        //清空viewpager和recyclerview的数据源
        mBannerPager.removeAllViews();
        mRecyclerView.removeAllViews();

        mTopStoriesBeans.clear();
        mDatas.clear();
        Log.d(TAG, "reLoadLatestNews: mDatas: " + mDatas.size());

        //下拉刷新重新获取mLatestNewsBean。
        try {
            mLatestBean = new FetchLatestBeanTask().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "reLoadLatestNews: " + mLatestBean);
        mTopStoriesBeans.addAll(mLatestBean.getTop_stories());

        //
        mBannerPager.setAdapter(null);
        mBannerAdapter = new BannerAdapter(getSupportFragmentManager(), mTopStoriesBeans);
        mBannerPager.setAdapter(mBannerAdapter);
        mBannerPager.setCurrentItem(0);
        ((RadioButton) indicatorGroup.getChildAt(0)).setChecked(true);


        //填充mDatas
        mDatas.addAll(mLatestBean.getStories());
        mRecyclerView.setAdapter(null);
        mDailyAdapter = new DailyAdapter();
        mRecyclerView.setAdapter(mDailyAdapter);

        mDailyAdapter.setPagerView(bannerView, 0);

        addHeader(mLatestBean.getDate());
        mDailyAdapter.addDatas(mDatas);


        Message message = Message.obtain();
        message.what = SWIPEREFRESH;
        mHandler.sendMessage(message);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        MenuItem toggleItem = menu.findItem(R.id.action_mode_toggle);
        if (isNightMode) {
            toggleItem.setTitle(R.string.action_day_mode);
        } else {
            toggleItem.setTitle(R.string.action_night_mode);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mDailyAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_mode_toggle) {
            if (isNightMode) {
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
            } else {
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                recreate();
            }
            isNightMode = !isNightMode;
            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putBoolean("isNightMode", isNightMode)
                    .apply();
            Log.d(TAG, "isNightMode: " + isNightMode);
            invalidateOptionsMenu();//刷新选项菜单
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                reLoadLatestNews();
                break;

            default:
                Intent intent = ThemeListActivity.newIntent(this, id);
                startActivity(intent);
                Log.d(TAG, "onNavigationItemSelected: " + id);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
