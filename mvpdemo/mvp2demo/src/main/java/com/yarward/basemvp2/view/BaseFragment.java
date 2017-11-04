package com.yarward.basemvp2.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yarward.basemvp2.helper.PresenterFactory;
import com.yarward.basemvp2.helper.ReflectionPresenterFactory;
import com.yarward.basemvp2.presenter.BasePresenter;
import com.yarward.basemvp2.presenter.PresenterDelegate;


/**
 * @author Michelle
 * @des 作为View层的 Fragment 的基类
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView,
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterDelegate.bindViewCompont(this);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(getLayoutId(),null,false);
        initFields();
        bindEventListener();
        getPresenter().onCreate(savedInstanceState);
        getPresenter().setListeners();
        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getPresenter().onAttach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresenter().onActivityCreated();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().onStop();
    }

    @Override
    public void onDestroy() {
        presenterDelegate.unbindViewCompont();
        super.onDestroy();
        getPresenter().onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getPresenter().onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getPresenter().onSaveInstanceState(outState);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
        return getActivity();
    }
}
