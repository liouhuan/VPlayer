package com.vplayer.www.controller.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.tnt.MaterialRefreshLayout;
import com.tnt.MaterialRefreshListener;
import com.vplayer.www.R;
import com.vplayer.www.controller.adapter.LikeAdapter;
import com.vplayer.www.controller.base.BaseFragment;
import com.vplayer.www.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:
 */

public class LikeFragment extends BaseFragment {
    @BindView(R.id.rcl_like)
    RecyclerView rclLike;
    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;

    private List<String> list = new ArrayList<>();


    @Override
    public View setLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View like = inflater.inflate(R.layout.likefragment, null);
        return like;
    }

    @Override
    protected void setData() {
        super.setData();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        rclLike.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rclLike.setAdapter(new LikeAdapter(R.layout.likeadapter, list));
        refresh.autoRefresh();
    }

    @Override
    protected void initListener() {
        super.initListener();
        rclLike.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.showShort(getContext(), "第：" + position + " 项");
            }
        });

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refresh.finishRefresh();
            }
        });
    }

}
