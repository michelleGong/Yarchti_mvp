package com.yarward.basemvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Michelle_Hong on 2017/8/29 0029.
 *
 * MVP View的通用基础类，提供一些通用方法，业务View，继承该类，实现IView接口接口
 * @des
 */

public abstract class BaseViewDelegate extends ViewDelegateWrapper implements IDelegate {


    public abstract int getRootLayoutId();

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutID = getRootLayoutId();
        rootView = inflater.inflate(layoutID,container,false);
    }

    @Override
    public View getRootView() {
        return rootView;
    }


}
