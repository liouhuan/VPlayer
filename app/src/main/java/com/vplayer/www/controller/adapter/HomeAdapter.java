package com.vplayer.www.controller.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.vplayer.www.R;
import com.vplayer.www.model.bean.VideoBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Description:
 */

public class HomeAdapter extends BaseQuickAdapter<VideoBean.DataBean.AdjBean,BaseViewHolder> {

    private final Context context;

    public HomeAdapter(@LayoutRes int layoutResId, @Nullable List data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, VideoBean.DataBean.AdjBean item) {
        helper.setText(R.id.tv_item,item.getTitle());
        Glide.with(context)
                .load(item.getImg())
                .into((ImageView) helper.getView(R.id.img_item));
        helper.setText(R.id.year_item,item.getDate());
        helper.setText(R.id.min_item,item.getMin());
        helper.getView(R.id.fl_like_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.getView(R.id.like_img).setBackgroundResource(R.drawable.like_yes);
            }
        });
    }
}
