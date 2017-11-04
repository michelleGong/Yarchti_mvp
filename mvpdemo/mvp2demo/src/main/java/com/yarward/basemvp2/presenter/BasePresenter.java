package com.yarward.basemvp2.presenter;


import android.os.Handler;

import com.yarward.basemvp2.view.IView;



/**
 * @author Lornj
 * @time 2016/8/22
 * @des MVP 构架中的 Presente 定义接口，定义一些通用的方法
 */
public abstract class BasePresenter<View extends IView> implements IPresenter {

    protected View mView;

    protected Handler mainHandler = new Handler();


    public void attachViewCompont(View view){
        this.mView = view;
    }

    public void detechViewCompont(){
        this.mView = null;
    }


    public abstract void setListeners();

   /* public void attachView(View view) {
        this.mView = view;
        mView.mapViews();
        mView.initFields();
    }

    public abstract void detachView();*/


}
