package com.yarward.mvp1test.archti_3_mvp_databinding.editpatient;

import com.yarward.mvp1test.archti_3_mvp_databinding.bean.Bed;
import com.yarward.basemvp.model.IBusiness;
import com.yarward.basemvp.presenter.IPresenter;
import com.yarward.basemvp.view.IView;

/**
 * Created by Michelle_Hong on 2017/9/12 0012.
 *
 * @des
 */

public interface PatientInConstract {

    public interface PatientInPresenter extends IPresenter{

        /**
         * 进行入院操作
         * @param bed
         */
        void doPatientIn(Bed bed);

    }

    public interface PatientInView extends IView{

    }

    public interface PatientInModel extends IBusiness{

        interface PatientInCallback{
            void onSuccess();
        }

        /**
         * 病人入院
         * @param bed
         */
        void savePatient(Bed bed,PatientInCallback patientInCallback);

    }
}
