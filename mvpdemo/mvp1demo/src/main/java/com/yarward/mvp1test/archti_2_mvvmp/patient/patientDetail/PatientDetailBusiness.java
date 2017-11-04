package com.yarward.mvp1test.archti_2_mvvmp.patient.patientDetail;

import android.text.TextUtils;


import com.yarward.mvp1test.DemoApplication;
import com.yarward.mvp1test.archti_2_mvvmp.bean.Patient;
import com.yarward.mvp1test.common.ThreadPoolTools;

/**
 * Created by Michelle_Hong on 2017/8/30 0030.
 *
 * @des
 */

public class PatientDetailBusiness implements PatientDetailConstract.IPatientDetailModel {

    private DemoApplication demoApplication;

    public PatientDetailBusiness(DemoApplication demoApplication){
        this.demoApplication = demoApplication;
    }


    @Override
    public void modifyPatientDetail(Patient patient, final Callback<String> callback) {
        ThreadPoolTools.LIMITED_TASK_EXECUTOR.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);

                    if(callback != null){

                        callback.onResult("");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void doPatientIn(Patient patient, final Callback<String> callback) {

        ThreadPoolTools.LIMITED_TASK_EXECUTOR.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    if(callback != null){

                        callback.onResult("");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean validataPatientVariable(Patient patient) {
        if(patient != null && !TextUtils.isEmpty(patient.getName())){
            return true;
        }
        return false;
    }


    @Override
    public void getpatientDetail(String patientId, final PatientDetailCallback callback) {

        ThreadPoolTools.LIMITED_TASK_EXECUTOR.submit(new Runnable() {
            @Override
            public void run() {
                //1. 从本地db中查询
                //2. 从服务器网络请求访问
                //3. （根据业务从 1,2 多级查询数据），在此模拟数据
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Patient patient = new Patient();
                patient.setName("王小名");
                patient.setAge(12);
                patient.setBedName("01床");
                patient.setInTime("2012-11-05 12:00");
                //接口回调的方式
                if(callback != null){

                    callback.ongetPatientSuccess(patient);

                }

            }
        });
    }
}
