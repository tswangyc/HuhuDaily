package com.qingxu.android.huhudaily.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.qingxu.android.huhudaily.R;
import com.qingxu.android.huhudaily.adapter.ThemeAdapter;
import com.qingxu.android.huhudaily.model.ThemeBean;
import com.qingxu.android.huhudaily.util.FetchThemeBeanTask;
import com.qingxu.android.huhudaily.view.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class ThemeListActivity extends BaseActivity {

    public static final String TAG = "ThemeListActivity";
    public static final String EXTRA_ID = "com.qingxu.android.huhudaily.activity.ThemeListActivity.extra_id";
    private ThemeBean themeBean;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ThemeAdapter mThemeAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, ThemeListActivity.class);
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(EXTRA_ID, id)
                .apply();
        return intent;
    }

    @Override
    protected void doBusiness() {
        setContentView(getLayoutRes());

        /**
         * 获取themeBean
         */
        int extra_id = PreferenceManager.getDefaultSharedPreferences(this).getInt(EXTRA_ID, -1);
        try {
            themeBean = new FetchThemeBeanTask().execute(extra_id).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        /**
         * 配置toolbar
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(themeBean.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG, "onCreate: " + themeBean + " ,Name: " + themeBean.getName());

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.theme_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                //TODU
            }
        });
        /**
         * banner
         */
        View banner = LayoutInflater.from(this).inflate(R.layout.theme_banner, null);
        ImageView bannerImageView = (ImageView) banner.findViewById(R.id.theme_backend);
        Picasso.with(this)
                .load(themeBean.getBackground())
                .placeholder(null)
                .into(bannerImageView);
        bannerImageView.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        TextView bannerTextView = (TextView) banner.findViewById(R.id.theme_title);
        bannerTextView.setText(themeBean.getDescription());

        /**
         * header
         */
        View header = LayoutInflater.from(this).inflate(R.layout.theme_header, null);
        LinearLayout linearLayout = (LinearLayout) header.findViewById(R.id.theme_header_layout);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        for (int i = 0; i < themeBean.getEditors().size(); i++) {
            CircleImageView circleImageView = new CircleImageView(this, Color.WHITE, 90);
            LayoutParams params = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            params.rightMargin = 16;
            circleImageView.setLayoutParams(params);
            Picasso.with(this)
                    .load(themeBean.getEditors().get(i).getAvatar())
                    .placeholder(null)
                    .into(circleImageView);
            final String name = themeBean.getEditors().get(i).getName();
            circleImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ThemeListActivity.this, name, Toast.LENGTH_SHORT).show();
                }
            });
            assert linearLayout != null;
            linearLayout.addView(circleImageView);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.theme_recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mThemeAdapter = new ThemeAdapter();

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mThemeAdapter);
        mThemeAdapter.setBanner(banner, 0);
        mThemeAdapter.setHeaderView(header);
        mThemeAdapter.addDatas(themeBean.getStories());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_theme_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}

