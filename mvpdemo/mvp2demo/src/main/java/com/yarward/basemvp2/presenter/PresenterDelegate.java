package com.yarward.basemvp2.presenter;


import com.yarward.basemvp2.helper.PresenterFactory;
import com.yarward.basemvp2.view.IView;


/**
 * @author Lornj
 * @time 2016/12/21
 * @des Presenter 的装饰者,外界通过此类来产生 Presenter 实例并绑定 Presenter
 */
public class PresenterDelegate<P extends BasePresenter> {

    private PresenterFactory<P> mPresenterFactory;
    private P presenter;

    public PresenterDelegate(PresenterFactory<P> persenterFactory) {
        this.mPresenterFactory = persenterFactory;
    }

    public P getPresenter() {
        if (presenter !=  null){
            return presenter;
        }
        if (mPresenterFactory != null) {
            if (presenter == null) {
                presenter = mPresenterFactory.createPresenter();
            }
        }
        return presenter;
    }

    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    public PresenterFactory<P> getPresenterFactory() {
        return mPresenterFactory;
    }

    public void setPresenterFactory(PresenterFactory<P> presenterFactory) {
        mPresenterFactory = presenterFactory;
    }


    public void bindViewCompont(IView view){
        if(presenter == null){
            getPresenter();
        }
        if(presenter != null){
            presenter.attachViewCompont(view);
        }
    }


    public void unbindViewCompont(){
        if(presenter != null){
            presenter.detechViewCompont();
        }
    }
}
