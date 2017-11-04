package com.yarward.basemvp2.helper;



import com.yarward.basemvp2.presenter.BasePresenter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Lornj
 * @time 2016/12/21
 * @des 注释在 View 对象上，绑定 View 和 Presenter 对象
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePresenter {
    Class<? extends BasePresenter> value();
}
