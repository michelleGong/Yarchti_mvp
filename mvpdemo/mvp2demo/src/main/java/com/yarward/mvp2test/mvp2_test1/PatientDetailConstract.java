package com.yarward.mvp2test.mvp2_test1;

import com.yarward.basemvp2.model.IBusiness;
import com.yarward.basemvp2.presenter.BasePresenter;
import com.yarward.basemvp2.view.IView;
import com.yarward.mvp2test.Patient;


/**
 * Created by Michelle on 10/27/2017.
 *
 * @des  定义该功能模块的接口合约
 * <p> 1.命名规则： [模块代号]Constract </p>
 * <p> 2.三层接口均作为内部元素定义，命名也按照相应规则。</p>
 * <P> 3.声明的接口，请写明确清晰注释</P>
 *
 */

public interface PatientDetailConstract {


    public interface IPatientDetailModel extends IBusiness{

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
         * 获取病人信息的业务
         * @param patientId
         */
        void getpatientDetail(String patientId,Callback<Patient> callback);
    }

    public interface IPatientDetailView extends IView{
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

    public abstract class IPatientDetailPresenter extends BasePresenter<IPatientDetailView>{
        /**
         * 编辑病人
         */
        abstract void editPatient();

        /**
         * 修改病人
         */
        abstract void doModifyPatient();

        /**
         * 后台模拟操作：修改当前病人信息的字段(View Model 层测试)
         */
        abstract void doFakeBackgroundOperation();

        /**
         * 切换到添加病人操作
         */
        abstract void addPatient();

        /**
         * 住院
         */
        abstract void doPatientIn();

        /**
         *  加载病人信息
         */
        abstract void loadPatientInfo(String patientId);
    }
}
