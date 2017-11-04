package com.yarward.mvp1test.archti_3_mvp_databinding.currentpatient;

import com.yarward.R;
import com.yarward.basemvp.view.BaseDataBindingViewDelegate;

/**
 * Created by Michelle_Hong on 2017/9/11 0011.
 *
 * @des
 */

public class CurrentPatientViewDelegate extends BaseDataBindingViewDelegate implements CurrentPatientConstract.CurrentPatientView {

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_patientdetail;
    }

    @Override
    public void initView() {

    }


}
