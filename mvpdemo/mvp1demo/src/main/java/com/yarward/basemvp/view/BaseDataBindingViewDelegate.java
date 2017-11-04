package com.yarward.basemvp.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Michelle_Hong on 2017/9/11 0011.
 *
 * @des MVP View的通用类，当使用Fragment或者Activity（Presenter）的使用[Android官方框架
 * ]DataBinding时使用
 * 重写create方法
 */

public abstract class BaseDataBindingViewDelegate extends ViewDelegateWrapper implements IDelegate{


    ViewDataBinding viewDataBinding;

    public abstract int getRootLayoutId();

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutID = getRootLayoutId();
//        rootView = inflater.inflate(layoutID,container,false);
        viewDataBinding = DataBindingUtil.inflate(inflater,layoutID,container,false);
        rootView = viewDataBinding.getRoot();
    }


    @Override
    public View getRootView() {
        return viewDataBinding.getRoot();
    }

    public ViewDataBinding getViewDataBinding(){
        return viewDataBinding;
    }




}
