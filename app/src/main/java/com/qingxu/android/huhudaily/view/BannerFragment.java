package com.qingxu.android.huhudaily.view;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingxu.android.huhudaily.R;
import com.qingxu.android.huhudaily.DetailActivity;
import com.qingxu.android.huhudaily.model.TopStoriesBean;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BannerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BannerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "position";
    // TODO: Rename and change types of parameters
    private TopStoriesBean mTopStoriesBean;

    public BannerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param topStoriesBean .
     * @return A new instance of fragment BannerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BannerFragment newInstance(TopStoriesBean topStoriesBean) {
        BannerFragment fragment = new BannerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, topStoriesBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTopStoriesBean = (TopStoriesBean) getArguments().getSerializable(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_banner, container, false);
        ImageView bannerImageView = (ImageView) v.findViewById(R.id.bannerImage);
        Picasso.with(getActivity())
                .load(mTopStoriesBean.getImage())
                .placeholder(null)
                .into(bannerImageView);
        bannerImageView.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        TextView bannerTextView = (TextView) v.findViewById(R.id.bannerText);
        bannerTextView.setText(mTopStoriesBean.getTitle());
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "mTopStoriesBean.getTitle：" + mTopStoriesBean.getTitle(), Toast.LENGTH_SHORT).show();
                int extra_id = mTopStoriesBean.getId();
                Intent intent = DetailActivity.newIntent(getActivity(), extra_id);
                startActivity(intent);
            }
        });
        return v;
    }

}
