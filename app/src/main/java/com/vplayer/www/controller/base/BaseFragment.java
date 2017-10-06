package com.vplayer.www.controller.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Author     : Young
 * Date       : 2017/9/30 13:42 *
 */

public abstract class BaseFragment extends Fragment {

    public  Context f_context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        f_context = getActivity();
        initVariable();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = setLayoutId(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        setData();
        initListener();
        return view;
    }

    protected  abstract View setLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    protected  void initVariable(){}
    protected void setData(){}
    protected void initListener(){}

}
