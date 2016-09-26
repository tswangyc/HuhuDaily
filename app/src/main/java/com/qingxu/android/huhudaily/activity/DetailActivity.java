package com.qingxu.android.huhudaily.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qingxu.android.huhudaily.R;
import com.qingxu.android.huhudaily.model.DetailBean;
import com.qingxu.android.huhudaily.model.StoryExtraBean;
import com.qingxu.android.huhudaily.util.FetchDetailBeanTask;
import com.qingxu.android.huhudaily.util.FetchStoryExtraBeanTask;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

public class DetailActivity extends BaseActivity {

    public static final String TAG = "DetailActivity";
    public static final String EXTRA_ID = "com.qingxu.android.huhudaily.activity.DetailActivity.extra_id";
    private DetailBean detailBean;
    private StoryExtraBean mStoryExtraBean;
    private ImageView detail_image;
    private TextView detail_image_text;
    private TextView detail_image_source;
    private ProgressBar detail_progressbar;

    public static Intent newIntent(Context context, int extra_id) {
        Intent intent = new Intent(context, DetailActivity.class);
//        intent.putExtra(EXTRA_ID, extra_id);
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(EXTRA_ID, extra_id)
                .apply();
        return intent;
    }

    @Override
    protected void doBusiness() {
        setContentView(getLayoutRes());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        int extra_id = getIntent().getIntExtra(EXTRA_ID, -1);
        int extra_id = PreferenceManager.getDefaultSharedPreferences(this).getInt(EXTRA_ID, -1);
        try {
            detailBean = new FetchDetailBeanTask().execute(extra_id).get();
            mStoryExtraBean = new FetchStoryExtraBeanTask().execute(extra_id).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        detail_image = (ImageView) findViewById(R.id.detail_image);
        detail_image_text = (TextView) findViewById(R.id.detail_image_text);
        detail_image_source = (TextView) findViewById(R.id.detail_image_source);

        Picasso.with(this)
                .load(detailBean.getImage())
                .placeholder(null)
                .into(detail_image);
        detail_image.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);

        detail_image_text.setText(detailBean.getTitle());
        detail_image_source.setText(detailBean.getImage_source());

        detail_progressbar = (ProgressBar) findViewById(R.id.detail_progress_bar);
        detail_progressbar.setProgress(100);
        WebView detail_webview = (WebView) findViewById(R.id.detail_web_view);
        detail_webview.getSettings().setJavaScriptEnabled(true);
        detail_webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    detail_progressbar.setVisibility(View.GONE);
                } else {
                    detail_progressbar.setVisibility(View.VISIBLE);
                    detail_progressbar.setProgress(newProgress);
                }
            }

//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                getSupportActionBar().setSubtitle(title);
//            }

        });
        boolean isBigSize = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("switch_preference_textsize", false);
        WebSettings webSettings = detail_webview.getSettings();
        webSettings.setSupportZoom(true);
        if (isBigSize) {
            webSettings.setTextZoom(150);
        } else {
            webSettings.setTextZoom(100);
        }

        String linkCss = "<link rel=\"stylesheet\" href=\"file:///android_asset/common.css\" type=\"text/css\">";
        String body = "<html><header>" + linkCss + "</header><body>" + detailBean.getBody() + "</body></html>";
        detail_webview.loadDataWithBaseURL(detailBean.getShare_url(), body, "text/html", "utf-8", null);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_detail;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        MenuItem commentItem = menu.findItem(R.id.action_comment_text);
        commentItem.setTitle(mStoryExtraBean.getComments() + "");

        MenuItem voteItem = menu.findItem(R.id.action_comment_vote_text);
        voteItem.setTitle(mStoryExtraBean.getPopularity() + "");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_comment_image) {
            //TODU
        } else if (id == R.id.action_comment_vote_image) {
            //TODU
        }
        invalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
    }
}
