package com.vplayer.www.controller.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.vplayer.www.R;
import com.vplayer.www.controller.base.BaseFragment;
import com.vplayer.www.ui.custom.MyRadioGroup;
import com.vplayer.www.utils.DensityUtil;
import com.vplayer.www.utils.LogUtil;
import com.vplayer.www.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Author     : Young
 * Date       : 2017/9/30 13:18 *
 */

public class SearchFragment extends BaseFragment {


    @BindView(R.id.tab_search)
    TabLayout tabSearch;
    @BindView(R.id.rg_search)
    MyRadioGroup rgSearch;
    @BindView(R.id.stretch_search)
    ImageView stretchSearch;


    private int number = 0;
    //测试数据
    private String[] title = new String[]{"分类", "分组"};
    private List<String> Fen = new ArrayList<>();
    private List<String> women = new ArrayList<>();

    @Override
    public View setLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View search = inflater.inflate(R.layout.searchfragment, null);
        return search;
    }

    @Override
    protected void setData() {
        super.setData();
        initTab();
        initBtn();
    }

    private void initBtn() {
        Fen.add("已订阅");
        Fen.add("主题");
        Fen.add("角色");
        Fen.add("服装");
        Fen.add("道具");
        Fen.add("行为");
        Fen.add("玩法");
        Fen.add("其他");

        women.add("全部");
        women.add("已订阅");
        women.add("A");
        women.add("B");
        women.add("C");
        women.add("D");
        women.add("E");
        women.add("F");
        women.add("G");
        women.add("H");
        women.add("I");
        women.add("J");
        women.add("K");
        women.add("L");
        women.add("M");
        women.add("N");
        women.add("O");

        addView(Fen, 0);
    }

    private void initTab() {
        tabSearch.setTabMode(TabLayout.MODE_FIXED);
        for (int x = 0; x < title.length; x++) {
            tabSearch.addTab(tabSearch.newTab().setText(title[x]));
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        tabSearch.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtil.e("选中:" + tab.getPosition());
                rgSearch.removeAllViews();
                switch (tab.getPosition()) {
                    case 0:
                        number = 0;
                        addView(Fen, 0);
                        break;
                    case 1:
                        number = 1;
                        addView(women, 1);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtil.e("从选中项离开");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtil.e("选中项重复点击");
            }
        });

        rgSearch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int count = rgSearch.indexOfChild(rgSearch.findViewById(checkedId));
                ToastUtil.showShort(getContext(),""+count);

            }
        });
    }

    private boolean jugdeF = true;
    private boolean jugdeW = true;
    @OnClick(R.id.stretch_search)
    public void onViewClicked() {
        switch (number) {
            case 0:
                if (jugdeF) {
                    jugdeF = false;
                    rgSearch.setSingleHeiNum(-1);
                    stretchSearch.setBackgroundResource(R.drawable.up_w);
                    rgSearch.requestLayout();
                    rgSearch.invalidate();
                } else {
                    jugdeF = true;
                    rgSearch.setSingleHeiNum(Fen.size() > 10 ? 10 : Fen.size());
                    stretchSearch.setBackgroundResource(R.drawable.down_w);
                    rgSearch.requestLayout();
                    rgSearch.invalidate();
                }
                break;
            case 1:
                if (jugdeW) {
                    rgSearch.setSingleHeiNum(-1);
                    stretchSearch.setBackgroundResource(R.drawable.up_w);
                    jugdeW = false;
                    rgSearch.requestLayout();
                    rgSearch.invalidate();
                } else {
                    jugdeW = true;
                    stretchSearch.setBackgroundResource(R.drawable.down_w);
                    rgSearch.setSingleHeiNum(women.size() > 10 ? 10 : women.size());
                    rgSearch.requestLayout();
                    rgSearch.invalidate();
                }
                break;
        }
    }


    /**
     * 动态添加RadioButton
     *
     * @param list
     * @param count
     */
    private void addView(List<String> list, int count) {
        if (list.size() > 0 && list != null) {
            rgSearch.setVisibility(View.VISIBLE);
            stretchSearch.setVisibility(View.VISIBLE);
            AppBarLayout.LayoutParams lp = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, DensityUtil.dp2px(getContext(), 26));
            lp.setMargins(0, 0, 0, 0);
            for (int x = 0; x < list.size(); x++) {
                RadioButton btn = new RadioButton(getContext());
                btn.setLayoutParams(lp);
                btn.setBackgroundResource(R.drawable.search_radiob_selector);
                btn.setButtonDrawable(null);
                btn.setGravity(Gravity.CENTER);
                btn.setWidth(DensityUtil.dp2px(getContext(), 52));
                btn.setTextSize(11);
                btn.setHeight(DensityUtil.dp2px(getContext(), 20));
                btn.setTextColor(getContext().getResources().getColor(android.support.v7.appcompat.R.color.background_material_dark));
                switch (count) {
                    case 0:
                        btn.setText(list.get(x));
                        break;
                    case 1:
                        btn.setText(list.get(x));
                        break;
                }
                rgSearch.addView(btn, lp);
            }
            RadioButton r = (RadioButton) rgSearch.getChildAt(0);
            r.setChecked(true);
            rgSearch.setSingleColumn(false);
            rgSearch.setColumnNumber(5);
            rgSearch.setSingleHeiNum(list.size() > 10 ? 10 : list.size());
            rgSearch.setColumnHeight(DensityUtil.dp2px(getContext(), 30));
            stretchSearch.setBackgroundResource(R.drawable.down_w);
            jugdeF = true;
            jugdeW = true;
        } else {
            stretchSearch.setVisibility(View.GONE);
            rgSearch.setVisibility(View.GONE);
        }
    }
}
