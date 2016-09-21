package com.qingxu.android.huhudaily.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingxu.android.huhudaily.R;
import com.qingxu.android.huhudaily.ThemeDetailActivity;
import com.qingxu.android.huhudaily.model.StoriesBean;
import com.qingxu.android.huhudaily.view.DailyBodyHolder;
import com.qingxu.android.huhudaily.view.ThemeBannerHolder;
import com.qingxu.android.huhudaily.view.ThemeHeaderHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QingXu on 2016/9/20.
 */

public class ThemeAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static final String TAG = "ThemeAdapter";
    private static final int VIEW_HEADER = R.layout.theme_header;
    private static final int VIEW_BANNER = R.layout.theme_banner;
    private static final int VIEW_BODY = R.layout.news_item;

    private View mBannerView, mHeaderView;
    private int bannerCount = 0;
    private int headerCount = 0;

    private List<StoriesBean> mDatas = new ArrayList<>();

    public int getHeaderView() {
        return headerCount;
    }

    public void setHeaderView(View headerView) {
        int position = getItemCount();
        mHeaderView = headerView;
        headerCount++;
        notifyItemInserted(position);
    }

    public void setBanner(View bannerView, int position) {
        mBannerView = bannerView;
        bannerCount++;
        notifyItemInserted(position);
    }

    public void addDatas(List<StoriesBean> storiesBeens) {
        mDatas.addAll(storiesBeens);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_BANNER) {
            return new ThemeBannerHolder(mBannerView);
        } else if (viewType == VIEW_HEADER) {
            return new ThemeHeaderHolder(mHeaderView);
        } else {
            View mBodyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            return new DailyBodyHolder(mBodyView);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if (holder instanceof ThemeBannerHolder) {
            return;
        }
        if (holder instanceof ThemeHeaderHolder) {
            return;
        }
        if (holder instanceof DailyBodyHolder) {
            final int realPosition = getRealPosition(position);
//            Log.d(TAG, "position: " + position + "; realPosition: " + realPosition + "; pagerCount: " + pagerCount + "; headerCount: " + headerCount);
            StoriesBean storiesBean = mDatas.get(realPosition);

            ((DailyBodyHolder) holder).bindBody(storiesBean);

            final int extra_id = storiesBean.getId();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(holder.itemView.getContext(), "realPosition: " + realPosition + "; position: " + position, Toast.LENGTH_SHORT).show();
                    Intent intent = ThemeDetailActivity.newIntent(holder.itemView.getContext(), extra_id);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + bannerCount + headerCount;
    }

    public int getRealPosition(int position) {

        return position - bannerCount - headerCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_BANNER;
        }
        if (position == 1) {

            return VIEW_HEADER;
        }
        return VIEW_BODY;
    }
}
