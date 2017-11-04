package com.yarward.basemvp2.helper;


import com.yarward.basemvp2.presenter.BasePresenter;

/**
 * @author Lornj
 * @time 2016/12/21
 * @des Presenter 的工厂类接口，实现此接口的类负责产生 Presenter 实例
 */
public interface PresenterFactory<P extends BasePresenter> {
    P createPresenter();
}
