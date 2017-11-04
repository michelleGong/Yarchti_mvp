package com.yarward.basemvp.view;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by Michelle_Hong on 2017/9/12 0012.
 *
 *
 * @des ViewDelegate的包装功能类，提供了如下方法：
 *
 * 1. 初始化view，并获取view实例的方法 @link bindView() 或者 @link getView()
 * 2. 给view设置点击监听的方法  @link setOnClickListener
 *
 *
 */

public class ViewDelegateWrapper {

    protected View rootView;

    protected final SparseArray<View> mViews = new SparseArray<View>();

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

    public <T extends View> T getView(int id){
        return (T) bindView(id);
    }

    /**
     * set OnClick listener
     *
     * @param l
     * @param ids
     */
    public void setOnClickListener(View.OnClickListener l, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            getView(id).setOnClickListener(l);
        }
    }
}
