package com.yarward.basemvp2.helper;


import com.yarward.basemvp2.presenter.BasePresenter;

/**
 * @author Lornj
 * @time 2016/12/21
 * @des 利用反射产生 Presenter 实例的真正工厂类，实现了 PresenterFactory 接口
 */
public class ReflectionPresenterFactory<P extends BasePresenter> implements PresenterFactory<P> {
    // presenter 的类型
    private Class<P> presenterClass;

    // 私有化 constractor
    private ReflectionPresenterFactory(Class<P> presenterClass) {
        this.presenterClass = presenterClass;
    }

    /**
     * 读取注解，并通过注解的 value 拿到 Presenter 的类型
     *
     * @param viewClass
     * @param <P>
     * @return
     */
    public static <P extends BasePresenter> ReflectionPresenterFactory<P> fromViewClass(Class<?> viewClass) {
        RequirePresenter annotation = viewClass.getAnnotation(RequirePresenter.class);

        Class<P> presenterClass = annotation == null ? null : (Class<P>) annotation.value();
        return presenterClass == null ? null : new ReflectionPresenterFactory<>(presenterClass);
    }

    /**
     * PresenterFactory 接口的实现方法，通过反射创建 Presenter 实例
     *
     * @return presenter 的实例
     */
    @Override
    public P createPresenter() {
        try {
            return presenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
