package com.yarward.mvp1test.archti_2_mvvmp.patient.patientDetail;

import com.yarward.mvp1test.archti_2_mvvmp.bean.Patient;
import com.yarward.basemvp.model.IBusiness;
import com.yarward.basemvp.presenter.IPresenter;
import com.yarward.basemvp.view.IView;

/**
 * Created by Michelle_Hong on 2017/8/30 0030.
 *
 * @des MVP合约
 */

public interface PatientDetailConstract {


    interface IPatientDetailModel extends IBusiness{


        //接口回调的方式，完成异步操作接口的返回
        interface PatientDetailCallback{
            void ongetPatientSuccess(Patient patient);
            void onError(String msg);
        }



        /**
         * 修改病人信息业务
         * @param patient
         * @param callback
         */
        void modifyPatientDetail(Patient patient, Callback<String> callback);

        /**
         * 住院业务
         * @param patient
         * @param callback
         */
        void doPatientIn(Patient patient, Callback<String> callback);

        /**
         * 校验病人信息
         * @param patient
         * @return
         */
        boolean validataPatientVariable(Patient patient);

        /**
         * 获取某病人信息
         * @param patientId
         */
        void getpatientDetail(String patientId, PatientDetailCallback callback);



    }


    interface IPatientDetailView extends IView{


        /**
         * show patient variable info
         * @param patient
         */
        void showPatientDetail(Patient patient);

        /**
         * set the View Compont to add mode
         */
        void setUIShowPatientDetails();

        void setUIEditPatient();

        void setUIAddPatient();

        void showInfoDialog(String msg);

        void toastInfo(String msg);

        void setFakeOButtonUsability(boolean isusable);
        void setSaveEditButtonUsability(boolean isusable);
        void setPatientInButtonUsability(boolean isusable);



    }

    interface IPatientDetailPresenter extends IPresenter{

        /**
         * 编辑病人
         */
        void editPatient();

        /**
         * 修改病人
         */
        void doModifyPatient();

        /**
         * 后台模拟操作：修改当前病人信息的字段(View Model 层测试)
         */
        void doFakeBackgroundOperation();

        void addPatient();

        /**
         * 住院
         */
        void doPatientIn();

        /**
         *  加载病人信息
         */
        void loadPatientInfo(String patientId);

    }
}
