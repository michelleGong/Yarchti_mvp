package com.yarward.basemvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yarward.basemvp.databinder.DataBinder;
import com.yarward.basemvp.model.IModel;
import com.yarward.basemvp.view.IDelegate;


/**
 * Created by Administrator on 2017/8/30 0030.
 *
 * @des
 */

public abstract class DataBinderFragmentPresenter<V extends IDelegate> extends FragmentPresenter<V> {

    public DataBinder dataBinder;

    public abstract DataBinder getDataBinder();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBinder = getDataBinder();
    }

    /**
     * 当model变化时，调用该方法就可回调绑定（手动绑定）*
     *
     * @param model
     * @param <M>
     */
    public <M extends IModel> void notifyModelChanged(M model) {
        if (dataBinder != null) {
            dataBinder.modelBindView(viewDelegate, model);
        }
    }

    public <M extends IModel> void getViewChange(M model) {
        if (dataBinder != null) {
            dataBinder.modelBindView(viewDelegate, model);
        }
    }
}
