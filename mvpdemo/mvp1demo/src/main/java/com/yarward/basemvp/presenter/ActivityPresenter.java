package com.yarward.basemvp.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yarward.basemvp.view.IDelegate;


/**
 * Created by Michelle_Hong on 2017/8/30 0030.
 *
 * Activity作为P的基类,View作为泛型
 * 需要将View构建好
 * @des
 */

public abstract class ActivityPresenter<V extends IDelegate> extends Activity {
    protected V viewDelegate;
    protected View rootView;

    /**
     * 指定ViewDelegate类型
     * @return
     */
    protected abstract Class<V> getDelegateClass();

    /**
     * 实现该方法,设置各种view的event监听
     */
    protected abstract void bindEventListener();

    /**
     * 可进行必要的初始化ViewDelegate的各种聚合的对象
     */
    protected abstract void initViewDelegate();


    public ActivityPresenter(){
        super();
        try {
            viewDelegate = getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("activity create IDelegate error");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("activity create IDelegate error");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.create(getLayoutInflater(),null,savedInstanceState);
        rootView = viewDelegate.getRootView();
        setContentView(rootView);
        initViewDelegate();
        viewDelegate.initView();
        bindEventListener();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(viewDelegate == null){
            try {
                viewDelegate = getDelegateClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("activity create IDelegate error");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("activity create IDelegate error");
            }
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewDelegate = null;
    }
}
