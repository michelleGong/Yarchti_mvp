package com.yarward.mvp1test.archti_3_mvp_databinding.editpatient;

import android.os.Handler;
import android.util.Log;

import com.yarward.DemoApplication;
import com.yarward.mvp1test.archti_3_mvp_databinding.bean.Bed;
import com.yarward.mvp1test.common.ThreadPoolTools;

/**
 * Created by Michelle_Hong on 2017/9/12 0012.
 *
 * @des
 */

public class PatientInBusiness implements PatientInConstract.PatientInModel {

    private Handler uiHandler = new Handler();

    @Override
    public void savePatient(final Bed bed, final PatientInCallback callback) {
        ThreadPoolTools.LIMITED_TASK_EXECUTOR.submit(new Runnable() {
            @Override
            public void run() {
                Log.d("222222",bed.getBedName()+"    "+bed.getCurrentpatient().getPatientName());
                //request server,then save db,when success then cached
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                DemoApplication.instance().updateCachedBedLayoutPatient(bed);
                if(callback != null){
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess();
                        }
                    });

                }
            }
        });
    }
}
