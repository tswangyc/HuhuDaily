package com.qingxu.android.huhudaily.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingxu.android.huhudaily.activity.DetailActivity;
import com.qingxu.android.huhudaily.R;
import com.qingxu.android.huhudaily.model.StoriesBean;
import com.qingxu.android.huhudaily.view.DailyBodyHolder;
import com.qingxu.android.huhudaily.view.DailyHeaderHolder;
import com.qingxu.android.huhudaily.view.DailyPagerHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QingXu on 2016/9/9.
 */
public class DailyAdapter extends RecyclerView.Adapter<ViewHolder> {

    public static final String TAG = "DailyAdapter";
    private static final int VIEW_HEADER = R.layout.news_header;
    private static final int VIEW_PAGER = R.layout.banner_layout;
    private static final int VIEW_BODY = R.layout.news_item;

    private View mPagerView;
    private int pagerCount = 0;
    private int headerCount = 0;

    private List<StoriesBean> mDatas = new ArrayList<>();
    private Map<Integer, String> dateMap = new HashMap<>();

    public int getHeaderView() {
        return headerCount;
    }

    public void setHeaderView(String s) {
        int position = getItemCount();

        dateMap.put(position, s);
        headerCount = dateMap.keySet().size();//修改于2016年9月15日23:43:34
        notifyItemInserted(position);
    }

    public void setPagerView(View pagerView, int position) {
        mPagerView = pagerView;
        pagerCount++;
        notifyItemInserted(position);
    }

    public void addDatas(List<StoriesBean> storiesBeens) {
        mDatas.addAll(storiesBeens);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_PAGER) {
            return new DailyPagerHolder(mPagerView);
        } else if (viewType == VIEW_HEADER) {
            View mHeaderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_header, parent, false);
            return new DailyHeaderHolder(mHeaderView);
        } else {
            View mBodyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            return new DailyBodyHolder(mBodyView);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (holder instanceof DailyPagerHolder) {

            return;
        }
        if (holder instanceof DailyHeaderHolder) {
            final String s = dateMap.get(position);
            ((DailyHeaderHolder) holder).bindHeader(s);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(holder.itemView.getContext(), "s: " + s + "; position: " + position, Toast.LENGTH_SHORT).show();
                }
            });
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
                    Intent intent = DetailActivity.newIntent(holder.itemView.getContext(), extra_id);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + pagerCount + headerCount;
    }

    public int getRealPosition(int position) {

        int count = 0;
        for (int i = 0; i < position + 1; i++) {
            if (dateMap.containsKey(i)) {
                count++;
            }
        }
        return position - pagerCount - count;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return VIEW_PAGER;
        }
        if (dateMap.containsKey(position)) {

            return VIEW_HEADER;
        }
        return VIEW_BODY;
    }
}
