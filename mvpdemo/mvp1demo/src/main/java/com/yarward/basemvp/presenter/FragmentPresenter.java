package com.yarward.basemvp.presenter;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yarward.basemvp.view.IDelegate;


/**
 * Created by Michelle_Hong on 2017/8/30 0030.
 *
 * Fragment作为P的基类,View作为泛型
 * 需要将View构建好
 * @des
 */

public abstract class FragmentPresenter<V extends IDelegate> extends Fragment {
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
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            viewDelegate = getDelegateClass().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        viewDelegate.create(inflater, container, savedInstanceState);
        initViewDelegate();
        return viewDelegate.getRootView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDelegate.initView();
        bindEventListener();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (viewDelegate == null) {
            try {
                viewDelegate = getDelegateClass().newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewDelegate = null;
    }
}
