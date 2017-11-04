package com.yarward.mvp1test.archti_2_mvvmp.patient.patientDetail.databinder;

import android.widget.EditText;

import com.yarward.mvp1test.R;
import com.yarward.mvp1test.archti_2_mvvmp.bean.Patient;
import com.yarward.mvp1test.archti_2_mvvmp.patient.patientDetail.PatientDetailViewDelegate;
import com.yarward.basemvp.databinder.DataBinder;


/**
 * Created by Michelle_Hong on 2017/9/2 0002.
 *
 * @des
 */

public class PatientDataBinder implements DataBinder<PatientDetailViewDelegate,Patient> {

    @Override
    public void viewBindModel(PatientDetailViewDelegate viewDelegate, Patient model) {

        ((EditText)viewDelegate.getView(R.id.et_patientName)).setText(model.getName());
        ((EditText)viewDelegate.getView(R.id.et_patientAge)).setText(model.getAge()+"");
        ((EditText)viewDelegate.getView(R.id.et_patientBed)).setText(model.getBedName());
        ((EditText)viewDelegate.getView(R.id.et_patientInTime)).setText(model.getInTime());

    }

    @Override
    public void modelBindView(PatientDetailViewDelegate viewDelegate, Patient model) {

    }
}
