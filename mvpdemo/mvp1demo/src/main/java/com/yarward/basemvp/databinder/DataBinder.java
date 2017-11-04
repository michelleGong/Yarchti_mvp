package com.yarward.basemvp.databinder;


import com.yarward.basemvp.model.IModel;
import com.yarward.basemvp.view.IDelegate;

/**
 * Created by Michelle_Hong on 2017/8/30 0030.
 *
 * ViewModel层接口,以View和Model做泛型
 * @des
 */

public interface DataBinder<V extends IDelegate,M extends IModel> {

    /**
     * 将数据绑定到VIew上，数据改变时，数据改变时，通过该方法定义Model的哪个数据绑定到哪个view上。
     * 数据改变时，回调该接口
     *
     * @param viewDelegate View
     * @param model Model
     */
    void viewBindModel(V viewDelegate, M model);

    /**
     * view改变时，回调该接口
     * @param viewDelegate
     * @param model
     */
    void modelBindView(V viewDelegate, M model);

}
