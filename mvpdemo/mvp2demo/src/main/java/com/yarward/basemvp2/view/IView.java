package com.yarward.basemvp2.view;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;

/**
 * @author Lornj
 * @time 2016/8/22
 * @des MVP模式中的View定义接口，定义一些通用的方法
 */
public interface IView {

        /**
         * 返回上下文
         * @return
         */
        public Context getContext();

        /**
         * 返回View层的根View
         * @return
         */
        public View getRootView();

        /**
         * findViewbyID
         * @param id
         * @param <T>
         * @return
         */
        public <T extends View> T getView(int id);


        //当Presnter需要涉及到Service操作时，可能需要
        public Activity getActivity();

        public Fragment getFragment();






}
