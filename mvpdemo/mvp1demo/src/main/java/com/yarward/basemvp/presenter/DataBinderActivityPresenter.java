package com.yarward.basemvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yarward.basemvp.databinder.DataBinder;
import com.yarward.basemvp.model.IModel;
import com.yarward.basemvp.view.IDelegate;


/**
 * Created by Administrator on 2017/8/30 0030.
 *
 * @des
 */

public abstract class DataBinderActivityPresenter<V extends IDelegate> extends ActivityPresenter<V> {

    protected DataBinder dataBinder;
    public abstract DataBinder getDataBinder();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinder = getDataBinder();
    }


    /**
     * 当model变化时，调用该方法就可回调绑定（手动绑定）
     * @param model
     * @param <M>
     */
    public<M extends IModel> void notifyModelChanged(M model){
        if(dataBinder != null){
            dataBinder.viewBindModel(viewDelegate,model);
        }
    }

    public<M extends IModel> void getViewChange(M model){
        if(dataBinder != null){
            dataBinder.modelBindView(viewDelegate,model);
        }
    }
}
