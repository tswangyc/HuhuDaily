package com.qingxu.android.huhudaily.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.qingxu.android.huhudaily.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by QingXu on 2016/9/10.
 */
public class DailyHeaderHolder extends RecyclerView.ViewHolder {

    public static final String TAG = "DailyHeaderHolder";
    private TextView mDateTextView;

    public DailyHeaderHolder(View itemView) {
        super(itemView);
        mDateTextView = (TextView) itemView.findViewById(R.id.news_date);
    }

    public void bindHeader(String s) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(new Date());//20160912
//        Log.d(TAG, "bindHeader: " + "date: " + date + "; s: " + s);
        //20160912转date，date转2016年09月12日 星期一
        try {
            Date tempDate = format.parse(s);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 EEEE");
            String text = sdf.format(tempDate);
            if (s.equals(date)) {
                mDateTextView.setText("今日热闻");
            } else {
                mDateTextView.setText(text);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
