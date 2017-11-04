package com.yarward.mvp2test.mvp2_test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.yarward.basemvp2.model.IBusiness;
import com.yarward.mvp2test.Patient;
import com.yarward.mvp2test.R;
import com.yarward.mvp2test.common.ThreadPoolTools;

/**
 * Created by Michelle on 10/28/2017.
 *  Presenter层，持有View层的变量mView
 * @des
 */

public class PatientDetailPresenter extends PatientDetailConstract.IPatientDetailPresenter {

    private PatientDetailBusiness patientDetailBusiness;
    private Patient currentPatient;

    /**
     * 设置监听
     *
     * 这里可以在View层做给View组件设置监听的操作，留出回调接口，在presenter层在该方法里，设置回掉。
     *
     * 测试demo这里，在该方法里直接设置监听，作为演示。
     */
    @Override
    public void setListeners() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.bt_addPatient:
                        addPatient();
                        break;
                    case R.id.bt_changeSync:
                        doFakeBackgroundOperation();
                        break;
                    case R.id.bt_In:
                        doPatientIn();
                        break;
                    case R.id.bt_edit:
                        editPatient();
                        break;
                    case R.id.bt_save:
                        doModifyPatient();
                        break;
                }
            }
        };
        mView.getView(R.id.bt_addPatient).setOnClickListener(onClickListener);
        mView.getView(R.id.bt_save).setOnClickListener(onClickListener);
        mView.getView(R.id.bt_changeSync).setOnClickListener(onClickListener);
        mView.getView(R.id.bt_edit).setOnClickListener(onClickListener);
        mView.getView(R.id.bt_In).setOnClickListener(onClickListener);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //实例化关联的业务
        patientDetailBusiness = new PatientDetailBusiness();
        //利用生命周期方法，进行项目逻辑的控制
        loadPatientInfo("123");
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        patientDetailBusiness = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onResult() {

    }

    @Override
    void editPatient() {
        mView.setUIEditPatient();
    }

    @Override
    void doModifyPatient() {
        mView.setSaveEditButtonUsability(false);
        final Patient patient = new Patient();
        patient.setName(((EditText)mView.getView(R.id.et_patientName)).getText().toString());
        patient.setAge(Integer.parseInt(((EditText)mView.getView(R.id.et_patientAge)).getText().toString()));
        patient.setBedName(((EditText)mView.getView(R.id.et_patientBed)).getText().toString());
        patient.setInTime(((EditText)mView.getView(R.id.et_patientInTime)).getText().toString());

        patientDetailBusiness.modifyPatientDetail(patient, new IBusiness.Callback<String>() {

            @Override
            public void onResult(String str) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.setSaveEditButtonUsability(true);
                        mView.setUIShowPatientDetails();
                        //注意修改后，其他模块的通知机制如何处理【根据业务判断是否有必要】，archti_1中未涉及该部分
                        mView.showPatientDetail(patient);
                    }
                });

            }

            @Override
            public void onError(Throwable throwable) {

            }


        });
    }

    @Override
    void doFakeBackgroundOperation() {
        mView.setFakeOButtonUsability(false);
        ThreadPoolTools.LIMITED_TASK_EXECUTOR.submit(new Runnable() {
            @Override
            public void run() {
                currentPatient.setBedName("00001床");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //修改完毕之后，UI更新，下面代码。可以利用ViewModel，也可以手动写。注意其他模块的通知【根据业务判断是否有必要】
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.setFakeOButtonUsability(true);
                        mView.showPatientDetail(currentPatient);
                    }
                });

            }
        });
    }

    @Override
    void addPatient() {
        mView.setUIAddPatient();
    }

    @Override
    void doPatientIn() {
        mView.setPatientInButtonUsability(false);
        final Patient patient = new Patient();
        patient.setName(((EditText)mView.getView(R.id.et_patientName)).getText().toString());
        String ageStr = ((EditText)mView.getView(R.id.et_patientAge)).getText().toString();
        patient.setAge(Integer.parseInt(TextUtils.isEmpty(ageStr) ? "0" : ageStr));
        patient.setBedName(((EditText)mView.getView(R.id.et_patientBed)).getText().toString());
        patient.setInTime(((EditText)mView.getView(R.id.et_patientInTime)).getText().toString());

        patientDetailBusiness.doPatientIn(patient, new IBusiness.Callback<String>() {

            @Override
            public void onResult(String str) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.setPatientInButtonUsability(true);
                        mView.showPatientDetail(patient);
                        mView.setUIShowPatientDetails();
                        mView.toastInfo("病人入院成功");
                    }
                });

            }

            @Override
            public void onError(Throwable throwable) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.toastInfo("病人入院失败");
                    }
                });

            }


        });
    }

    @Override
    void loadPatientInfo(String patientId) {
        patientDetailBusiness.getpatientDetail("123", new IBusiness.Callback<Patient>() {

            @Override
            public void onResult(final Patient patient) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        currentPatient = patient;
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                //下行代码可以使用ViewModel来替换
                                mView.showPatientDetail(patient);
                                mView.setUIShowPatientDetails();
                            }
                        });

                    }
                });
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
