package com.yarward.mvp1test.archti_3_mvp_databinding.currentpatient;

import com.yarward.mvp1test.archti_3_mvp_databinding.bean.Bed;
import com.yarward.basemvp.presenter.IPresenter;
import com.yarward.basemvp.view.IView;

/**
 * Created by Michelle_Hong on 2017/9/11 0011.
 *
 * @des
 */

public interface CurrentPatientConstract {


    interface CurrentPatientView extends IView{

    }

    interface CurrentPatientPresent extends IPresenter{

        void showCurrentPatient(Bed currentpatient);
    }

}
