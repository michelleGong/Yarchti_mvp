package com.yarward.basemvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Michelle_Hong on 2017/8/29 0029.
 *
 *      In the Presenter(Activity,Fragment) onCreate Method,Coding：
 *
 *      IDelegate viewDelegate = XXXX;
 *      viewDelegate.create(getLayoutInflater()，null,savedInstanceState);
 *      setContentView(viewDelegate.getRootView());
 *
 * @des View层代理接口
 */

public interface IDelegate {

    /**
     * create the RootView,if savedInstanceState is not null,init view.
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * View‘s RootView
     * @return
     */
    View getRootView();


    void initView();

}
