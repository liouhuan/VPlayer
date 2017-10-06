package com.vplayer.www.controller.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.vplayer.www.R;
import com.vplayer.www.controller.adapter.HomeAdapter;
import com.vplayer.www.controller.base.BaseFragment;
import com.vplayer.www.model.Model;
import com.vplayer.www.model.bean.VideoBean;
import com.vplayer.www.ui.activity.MainActivity;
import com.vplayer.www.ui.custom.MyRadioGroup;
import com.vplayer.www.ui.refresh.MaterialRefreshLayout;
import com.vplayer.www.ui.refresh.MaterialRefreshListener;
import com.vplayer.www.utils.DensityUtil;
import com.vplayer.www.utils.LogUtil;
import com.vplayer.www.utils.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Author     : Young
 * Date       : 2017/9/30 13:17 *
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.tab_home)
    TabLayout tabHome;
    @BindView(R.id.rg_home)
    MyRadioGroup rgHome;
    @BindView(R.id.rec_home)
    RecyclerView recHome;
    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;

    private final String TABNEW = "new";
    private final String TABHOT = "hot";
    private final String TABSUB = "subscribe";
    private String TABLAYOUT = TABNEW;

    //测试数据
    private String[] title = new String[]{"最新影片", "热门强档", "订阅项目"};
    private List<String> newS = new ArrayList<>();
    private List<String> newF = new ArrayList<>();
    private List<String> newD = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private List<VideoBean.DataBean.AdjBean> adj;
    private VideoBean video;

    @Override
    public View setLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View home = inflater.inflate(R.layout.homefragment, null);
        EventBus.getDefault().register(this);
        return home;
    }

    @Override
    protected void setData() {
        super.setData();
        initTab();
        initBtn();
        initRec();

        refresh.autoRefresh();
    }


    private void initRec() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recHome.setLayoutManager(linearLayoutManager);
        homeAdapter = new HomeAdapter(R.layout.home_item_layout, null, getContext());
        recHome.setAdapter(homeAdapter);
        recHome.setHasFixedSize(true);
        recHome.setNestedScrollingEnabled(false);

    }

    private void initBtn() {
        adj = new ArrayList<>();
        newS.add("新上架");
        newS.add("新发行");
        newF.add("日排行");
        newF.add("周排行");
        newF.add("月排行");
        addView(newS, 0);
    }

    private void initTab() {
        tabHome.setTabMode(TabLayout.MODE_FIXED);
        for (int x = 0; x < title.length; x++) {
            tabHome.addTab(tabHome.newTab().setText(title[x]));
        }
    }

    private boolean judge;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(VideoBean videoBean) {
        video = videoBean;
        if (judge == true) {
            adj = video.getData().getAdj();
            homeAdapter.setNewData(adj);
        } else {
            List<VideoBean.DataBean.AdjBean> ad = video.getData().getAdj();
            this.adj.addAll(ad);
            homeAdapter.setNewData(this.adj);
        }

    }

    @Override
    protected void initListener() {
        super.initListener();
        /**
         * tablayout点击事件
         */
        tabHome.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                rgHome.removeAllViews();
                switch (tab.getPosition()) {
                    case 0:
                        TABLAYOUT = TABNEW;
                        addView(newS, 0);
                        break;
                    case 1:
                        TABLAYOUT = TABHOT;
                        addView(newF, 1);
                        break;
                    case 2:
                        TABLAYOUT = TABSUB;
                        addView(newD, 2);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        /**
         * MyRadioGroup点击事件
         */
        rgHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int count = rgHome.indexOfChild(rgHome.findViewById(checkedId));
                switch (TABLAYOUT) {
                    case TABNEW:
                        ToastUtil.showShort(getContext(), title[tabHome.getSelectedTabPosition()] + ":" + newS.get(count));
                        switch (count) {
                            case 0:

                                break;
                            case 1:

                                break;
                        }
                        break;
                    case TABHOT:
                        ToastUtil.showShort(getContext(), title[tabHome.getSelectedTabPosition()] + ":" + newF.get(count));
                        switch (count) {
                            case 0:

                                break;
                            case 1:

                                break;
                            case 2:

                                break;
                        }
                        break;
                    case TABSUB:
                        break;
                }
            }
        });

        /**
         *RecycleView点击事件
         */
        recHome.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.showShort(getContext(), "第：" + position + " 项");

            }
        });



        /**
         *ScrollView滑动监听（刷新事件）
         */
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                 LogUtil.e("scrollview顶部监听："+scrollView.getScrollY());
                if (scrollView.getScrollY() == 0) {
                    refresh.setFristRefresh(true);
                } else {
                    refresh.setFristRefresh(false);
                }
                LogUtil.e(scrollView.getHeight()+scrollView.getScrollY()+"----"+scrollView.getChildAt(0).getHeight());
                if (scrollView.getHeight() + scrollView.getScrollY() == scrollView.getChildAt(0).getHeight()) {
                    refresh.setMoreRefresh(true);
                } else {
                    refresh.setMoreRefresh(false);
                }
            }
        });

        /**
         *上下拉刷新事件
         */
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                judge = true;
                Model.getIstace().getVideo((MainActivity) f_context, refresh);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                judge = false;
                Model.getIstace().getMore((MainActivity) f_context, refresh, video.getMore());

            }
        });
    }

    /**
     * 动态添加RadioButton
     *
     * @param list
     * @param count
     */
    private void addView(List<String> list, int count) {
        if (list.size() > 0 && list != null) {
            rgHome.setVisibility(View.VISIBLE);
            AppBarLayout.LayoutParams lp = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, DensityUtil.dp2px(getContext(), 26));
            lp.setMargins(DensityUtil.dp2px(getContext(), 8), DensityUtil.dp2px(getContext(), 4), DensityUtil.dp2px(getContext(), 8), DensityUtil.dp2px(getContext(), 4));
            for (int x = 0; x < list.size(); x++) {
                RadioButton btn = new RadioButton(getContext());
                btn.setLayoutParams(lp);
                btn.setBackgroundResource(R.drawable.home_radiob_selector);
                btn.setButtonDrawable(null);
                btn.setWidth(DensityUtil.dp2px(getContext(), 60));
                btn.setGravity(Gravity.CENTER);
                btn.setTextSize(12);
                btn.setHeight(DensityUtil.dp2px(getContext(), 21));
                btn.setTextColor(getContext().getResources().getColor(android.support.v7.appcompat.R.color.background_material_dark));
                switch (count) {
                    case 0:
                        btn.setText(list.get(x));
                        break;
                    case 1:
                        btn.setText(list.get(x));
                        break;
                    case 2:
                        btn.setText(list.get(x));
                        break;
                }
                rgHome.addView(btn, lp);
            }
            RadioButton r = (RadioButton) rgHome.getChildAt(0);
            r.setChecked(true);
            rgHome.setSingleColumn(false);
            rgHome.setColumnNumber(4);
            rgHome.setColumnHeight(DensityUtil.dp2px(getContext(), 38));
        } else {
            rgHome.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
