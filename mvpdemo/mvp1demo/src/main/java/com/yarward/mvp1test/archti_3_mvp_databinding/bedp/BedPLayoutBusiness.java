package com.yarward.mvp1test.archti_3_mvp_databinding.bedp;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.yarward.DemoApplication;
import com.yarward.mvp1test.archti_3_mvp_databinding.bean.Bed;
import com.yarward.mvp1test.archti_3_mvp_databinding.bean.Patient;
import com.yarward.mvp1test.common.ThreadPoolTools;

/**
 * Created by Michelle_Hong on 2017/9/5 0005.
 *
 * @des
 */

public class BedPLayoutBusiness implements BedPLayoutConstract.IBedPLayoutModel {

    private Handler uiHandler = new Handler();


    @Override
    public void loadBedPatientLayoutData(final Callback<List<Bed>> bedPLayoutCallBack) {
        final List<Bed> bedpLayoutDatas = new ArrayList<Bed>();

        ThreadPoolTools.LIMITED_TASK_EXECUTOR.submit(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 60 ; i++){
                    Bed bed = new Bed();
                    bed.setBedName(i+"床");
                    bed.setBedId(i);
                    if(i%3 == 0){
                        Patient p = new Patient();
                        p.setInTime("2017-02-"+i);
                        p.setPatientAge(i+5);
                        p.setPatientId("0x33"+i);
                        p.setPatientName("Name"+i);
                        bed.setCurrentpatient(p);
                    }
                    bedpLayoutDatas.add(bed);
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //cached
                DemoApplication.instance().setCachedbedLayouts(bedpLayoutDatas);

                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        bedPLayoutCallBack.onResult(bedpLayoutDatas);
                    }
                });


            }
        });
    }

    /**
     * @param patientDetailCallBack
     */
    @Override
    public void testDataBinding(final Callback<Bed> patientDetailCallBack) {

        ThreadPoolTools.LIMITED_TASK_EXECUTOR.submit(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i = 0; ; i++){
                    final Bed bed = DemoApplication.instance().getCachedbedLayouts().get(0);
                    Patient patient = new Patient();
                    patient.setPatientName(i+"a"+i);
                    patient.setPatientAge(37);
                    bed.setCurrentpatient(patient);
                    Log.d("change","***************************");

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(patientDetailCallBack != null){
                                patientDetailCallBack.onResult(bed);

                            }
                        }
                    });
                }
            }
        });
    }
}
