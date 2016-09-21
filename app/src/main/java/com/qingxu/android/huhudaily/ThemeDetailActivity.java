package com.qingxu.android.huhudaily;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.qingxu.android.huhudaily.model.DetailBean;
import com.qingxu.android.huhudaily.model.StoryExtraBean;
import com.qingxu.android.huhudaily.util.FetchDetailBeanTask;
import com.qingxu.android.huhudaily.util.FetchStoryExtraBeanTask;

import java.util.concurrent.ExecutionException;

public class ThemeDetailActivity extends AppCompatActivity {

    public static final String TAG = "ThemeDetailActivity";
    public static final String EXTRA_ID = "com.qingxu.android.huhudaily.ThemeDetailActivity.extra_id";
    private DetailBean detailBean;
    private ProgressBar theme_detail_progressbar;
    private StoryExtraBean mStoryExtraBean;

    public static Intent newIntent(Context context, int extra_id) {
        Intent intent = new Intent(context, ThemeDetailActivity.class);
//        intent.putExtra(EXTRA_ID, extra_id);
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(EXTRA_ID, extra_id)
                .apply();
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        int extra_id = getIntent().getIntExtra(EXTRA_ID, -1);
        int extra_id = PreferenceManager.getDefaultSharedPreferences(this).getInt(EXTRA_ID, -1);
        try {
            detailBean = new FetchDetailBeanTask().execute(extra_id).get();
            mStoryExtraBean = new FetchStoryExtraBeanTask().execute(extra_id).get();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        theme_detail_progressbar = (ProgressBar) findViewById(R.id.theme_detail_progress_bar);
        theme_detail_progressbar.setProgress(100);
        WebView theme_detail_webview = (WebView) findViewById(R.id.theme_detail_web_view);
        theme_detail_webview.getSettings().setJavaScriptEnabled(true);
        theme_detail_webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    theme_detail_progressbar.setVisibility(View.GONE);
                } else {
                    theme_detail_progressbar.setVisibility(View.VISIBLE);
                    theme_detail_progressbar.setProgress(newProgress);
                }
            }

//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                getSupportActionBar().setSubtitle(title);
//            }

        });

        boolean isBigSize = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("switch_preference_textsize", false);
        WebSettings webSettings = theme_detail_webview.getSettings();
        webSettings.setSupportZoom(true);
        if (isBigSize) {
            webSettings.setTextZoom(150);
        } else {
            webSettings.setTextZoom(100);
        }

        String linkCss = "<link rel=\"stylesheet\" href=\"file:///android_asset/common.css\" type=\"text/css\">";
        String body = "<html><header>" + linkCss + "</header><body>" + detailBean.getBody() + "</body></html>";
        theme_detail_webview.loadDataWithBaseURL(detailBean.getShare_url(), body, "text/html", "utf-8", null);

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
