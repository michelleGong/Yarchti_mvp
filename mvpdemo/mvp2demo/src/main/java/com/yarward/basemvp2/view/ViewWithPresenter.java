package com.yarward.basemvp2.view;


import com.yarward.basemvp2.presenter.BasePresenter;
import com.yarward.basemvp2.helper.PresenterFactory;

/**
 * @author Lornj
 * @time 2016/12/21
 * @des View 要实现的接口,指定绑定行为
 */
public interface ViewWithPresenter<P extends BasePresenter> {

    PresenterFactory<P> getPresenterFactory();

    void setPresenterFactory(PresenterFactory<P> factory);

    P getPresenter();
}
