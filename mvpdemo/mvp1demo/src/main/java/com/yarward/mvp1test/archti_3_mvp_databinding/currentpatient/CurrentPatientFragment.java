package com.yarward.mvp1test.archti_3_mvp_databinding.currentpatient;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.yarward.MyCustom;
import com.yarward.mvp1test.archti_3_mvp_databinding.bean.Bed;
import com.yarward.basemvp.presenter.FragmentPresenter;

/**
 * Created by Michelle_Hong on 2017/9/5 0005.
 *
 * @des
 */

public class CurrentPatientFragment extends FragmentPresenter<CurrentPatientViewDelegate> implements CurrentPatientConstract.CurrentPatientPresent{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater,container,savedInstanceState);
        return rootView;
    }


    @Override
    protected Class<CurrentPatientViewDelegate> getDelegateClass() {
        return CurrentPatientViewDelegate.class;
    }

    @Override
    protected void initViewDelegate() {

    }

    @Override
    protected void bindEventListener() {

    }


    /**
     * invoked in Activity
     * @param currentpatient
     */
    @Override
    public void showCurrentPatient(Bed currentpatient) {


        ViewDataBinding dataBinding = viewDelegate.getViewDataBinding();
        MyCustom myCustomDataBinding = (MyCustom) dataBinding;
        myCustomDataBinding.setCurrentbed(currentpatient);
//        dataBinding.setCurrentbed(currentpatient);

    }
}
