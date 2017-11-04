package com.yarward.basemvp2.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;


import com.yarward.basemvp2.presenter.BasePresenter;
import com.yarward.basemvp2.presenter.PresenterDelegate;
import com.yarward.basemvp2.helper.PresenterFactory;
import com.yarward.basemvp2.helper.ReflectionPresenterFactory;



/**
 * @author Lornj
 * @time 2016/8/11
 * @des 作为View层的 Activity 的基类
 */
public abstract class BaseActivity<P extends BasePresenter> extends Activity implements IView,
        ViewWithPresenter<P> {

    private PresenterDelegate<P> presenterDelegate =
            new PresenterDelegate<>(ReflectionPresenterFactory.<P>fromViewClass(getClass()));


    protected final SparseArray<View> mViews = new SparseArray<View>();

    protected View rootView;

    /**
     * init View
     * @param id
     * @param <T>
     * @return
     */

    public <T extends View> T bindView(int id){
        T view = (T) mViews.get(id);
        if(view == null){
            view = (T) rootView.findViewById(id);
            mViews.put(id,view);
        }
        return  view;
    }

    @Override
    public <T extends View> T getView(int id){
        return (T) bindView(id);
    }


    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterDelegate.bindViewCompont(this);
        rootView = getLayoutInflater().inflate(getLayoutId(),null,false);
        setContentView(rootView);
        initFields();
        bindEventListener();
        getPresenter().onCreate(savedInstanceState);
        getPresenter().setListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getPresenter().onRestart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getPresenter().onStop();
    }

    @Override
    protected void onDestroy() {
        presenterDelegate.unbindViewCompont();
        super.onDestroy();
        getPresenter().onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPresenter().onActivityResult(requestCode,resultCode,data);
    }

    /**
     * 返回layout 布局文件的id
     * @return
     */
    public abstract int getLayoutId();


    /**
     * 初始化其他属性
     */
    public abstract void initFields();

    /**
     * 设置监听
     */
    public abstract void bindEventListener();

    /**
     * 快捷的设置onclickListener
     *
     * @param listener onclickListener
     * @param views    要设置onclickListener的Views
     */
    public void setViewClickListener(View.OnClickListener listener, View... views) {
        if (views == null){
            return;
        }
        for (View ele : views) {
            if (ele != null && listener != null) {
                ele.setOnClickListener(listener);
            }
        }
    }


    @Override
    public PresenterFactory<P> getPresenterFactory() {
        return presenterDelegate.getPresenterFactory();
    }

    @Override
    public void setPresenterFactory(PresenterFactory<P> factory) {
        presenterDelegate.setPresenterFactory(factory);
    }

    @Override
    public P getPresenter() {
        return presenterDelegate.getPresenter();
    }

    public void setPresenter(P presenter){
        presenterDelegate.setPresenter(presenter);
    }


    @Override
    public Context getContext() {
        return this;
    }
}
