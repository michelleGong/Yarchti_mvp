package com.yarward.mvp1test.archti_1_mvp.patient.patientDetail;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;


import com.yarward.mvp1test.archti_1_mvp.bean.Patient;
import com.yarward.basemvp.model.IBusiness;
import com.yarward.basemvp.presenter.ActivityPresenter;


/**
 * Presenter
 */
public class PatientDetailActivity extends ActivityPresenter<PatientDetailViewDelegate> implements PatientDetailConstract.IPatientDetailPresenter {

    private PatientDetailBusiness patientDetailBusiness;
    private Handler mainHandler  = new Handler();

    private Patient currentPatient;

    @Override
    protected Class<PatientDetailViewDelegate> getDelegateClass() {
        return PatientDetailViewDelegate.class;
    }

    @Override
    protected void initViewDelegate() {
        viewDelegate.setFragmentManager(getFragmentManager());
        viewDelegate.setmActivity(this);
    }

    @Override
    protected void bindEventListener() {
        viewDelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.bt_addPatient:
                        addPatient();
                        break;
                    case R.id.bt_changeSync:  //测试ViewModel用
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
        }, R.id.bt_addPatient,R.id.bt_save,R.id.bt_changeSync,R.id.bt_edit,R.id.bt_In);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例化关联的业务
        patientDetailBusiness = new PatientDetailBusiness();
        //利用生命周期方法，进行项目逻辑的控制
        loadPatientInfo("123");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        patientDetailBusiness = null;
    }

    @Override
    public void editPatient() {
        viewDelegate.setUIEditPatient();

    }

    @Override
    public void addPatient() {
        viewDelegate.setUIAddPatient();
    }

    @Override
    public void doFakeBackgroundOperation() {
        viewDelegate.setFakeOButtonUsability(false);
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
                        viewDelegate.setFakeOButtonUsability(true);
                        viewDelegate.showPatientDetail(currentPatient);
                    }
                });

            }
        });
    }

    @Override
    public void doModifyPatient() {
        viewDelegate.setSaveEditButtonUsability(false);
        final Patient patient = new Patient();
        patient.setName(((EditText)viewDelegate.getView(R.id.et_patientName)).getText().toString());
        patient.setAge(Integer.parseInt(((EditText)viewDelegate.getView(R.id.et_patientAge)).getText().toString()));
        patient.setBedName(((EditText)viewDelegate.getView(R.id.et_patientBed)).getText().toString());
        patient.setInTime(((EditText)viewDelegate.getView(R.id.et_patientInTime)).getText().toString());

        patientDetailBusiness.modifyPatientDetail(patient, new IBusiness.Callback<String>() {

            @Override
            public void onResult(String str) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        viewDelegate.setSaveEditButtonUsability(true);
                        viewDelegate.setUIShowPatientDetails();
                        //注意修改后，其他模块的通知机制如何处理【根据业务判断是否有必要】，archti_1中未涉及该部分
                        viewDelegate.showPatientDetail(patient);
                    }
                });

            }

            @Override
            public void onError(Throwable throwable) {

            }


        });
    }

    @Override
    public void doPatientIn() {
        viewDelegate.setPatientInButtonUsability(false);
        final Patient patient = new Patient();
        patient.setName(((EditText)viewDelegate.getView(R.id.et_patientName)).getText().toString());
        String ageStr = ((EditText)viewDelegate.getView(R.id.et_patientAge)).getText().toString();
        patient.setAge(Integer.parseInt(TextUtils.isEmpty(ageStr) ? "0" : ageStr));
        patient.setBedName(((EditText)viewDelegate.getView(R.id.et_patientBed)).getText().toString());
        patient.setInTime(((EditText)viewDelegate.getView(R.id.et_patientInTime)).getText().toString());

        patientDetailBusiness.doPatientIn(patient, new IBusiness.Callback<String>() {

            @Override
            public void onResult(String str) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        viewDelegate.setPatientInButtonUsability(true);
                        viewDelegate.showPatientDetail(patient);
                        viewDelegate.setUIShowPatientDetails();
                        viewDelegate.toastInfo("病人入院成功");
                    }
                });

            }

            @Override
            public void onError(Throwable throwable) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        viewDelegate.toastInfo("病人入院失败");
                    }
                });

            }


        });
    }

    @Override
    public void loadPatientInfo(String patientId) {
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
                                viewDelegate.showPatientDetail(patient);
                                viewDelegate.setUIShowPatientDetails();
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
