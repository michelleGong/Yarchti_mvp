package com.yarward.mvp1test.archti_1_mvp.patient.patientDetail;

import com.yarward.mvp1test.archti_1_mvp.bean.Patient;
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


        /**
         * 修改病人信息业务
         * @param patient
         * @param callback
         */
        void modifyPatientDetail(Patient patient,Callback<String> callback);

        /**
         * 住院业务
         * @param patient
         * @param callback
         */
        void doPatientIn(Patient patient,Callback<String> callback);

        /**
         * 校验病人信息
         * @param patient
         * @return
         */
        boolean validataPatientVariable(Patient patient);

        /**
         * 获取病人信息的业务
         * @param patientId
         */
        void getpatientDetail(String patientId,Callback<Patient> callback);

    }


    interface IPatientDetailView extends IView{


        /**
         * show patient variable info
         * @param patient
         */
        void showPatientDetail(Patient patient);

        /**
         * set the Viewsto show mode
         */
        void setUIShowPatientDetails();

        /**
         * set the Views to edit patient mode
         */
        void setUIEditPatient();

        /**
         * set the Views to add mode
         */
        void setUIAddPatient();

        /**
         * toast a message
         * @param msg
         */
        void toastInfo(String msg);

        /**
         * change the ability of FakeOperation Button
         * @param isusable
         */
        void setFakeOButtonUsability(boolean isusable);

        /**
         * change the ability of save Button
         * @param isusable
         */
        void setSaveEditButtonUsability(boolean isusable);

        /**
         * change the ability of 住院 Button
         * @param isusable
         */
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

        /**
         * 切换到添加病人操作
         */
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
