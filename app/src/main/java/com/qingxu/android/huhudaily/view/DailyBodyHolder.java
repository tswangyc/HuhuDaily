package com.qingxu.android.huhudaily.view;

import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingxu.android.huhudaily.R;
import com.qingxu.android.huhudaily.model.StoriesBean;
import com.squareup.picasso.Picasso;

/**
 * Created by QingXu on 2016/9/10.
 */
public class DailyBodyHolder extends RecyclerView.ViewHolder {

    private TextView mTitleTextView;
    private ImageView mImageView;

    public DailyBodyHolder(View itemView) {
        super(itemView);

        mTitleTextView = (TextView) itemView.findViewById(R.id.news_item_text);
        mImageView = (ImageView) itemView.findViewById(R.id.news_item_image);
    }

    public void bindBody(StoriesBean storiesBean) {
        boolean isBigSize = PreferenceManager.getDefaultSharedPreferences(itemView.getContext()).getBoolean("switch_preference_textsize", false);

        if (isBigSize) {
            mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        } else {
            mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        }
        mTitleTextView.setText(storiesBean.getTitle());
        if (storiesBean.getImages() == null) {
            return;
        }
        Picasso.with(itemView.getContext())
                .load(storiesBean.getImages().get(0))
                .placeholder(null)
                .into(mImageView);
    }

}
