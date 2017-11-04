package com.yarward.mvp2test.mvp2_test1;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yarward.basemvp2.helper.RequirePresenter;
import com.yarward.basemvp2.view.BaseActivity;
import com.yarward.mvp2test.Patient;
import com.yarward.mvp2test.R;


@RequirePresenter(PatientDetailPresenter.class)
public class PatientDetailActivity extends BaseActivity<PatientDetailPresenter> implements PatientDetailConstract.IPatientDetailView{


    @Override
    public int getLayoutId() {
        return R.layout.activity_patient_detail;
    }



    @Override
    public void initFields() {

    }

    @Override
    public void bindEventListener() {

    }


    @Override
    public void showPatientDetail(Patient patient) {
        if(patient == null){
            return;
        }
        ((EditText) getView(R.id.et_patientName)).setText(patient.getName());
        ((EditText) getView(R.id.et_patientAge)).setText(String.valueOf(patient.getAge()));
        ((EditText) getView(R.id.et_patientBed)).setText(patient.getBedName());
        ((EditText) getView(R.id.et_patientInTime)).setText(patient.getInTime());
    }

    @Override
    public void setUIShowPatientDetails() {

        uiComponentLostFocuse();
        getView(R.id.et_patientName).setEnabled(false);
        getView(R.id.et_patientAge).setEnabled(false);
        getView(R.id.et_patientBed).setEnabled(false);
        getView(R.id.et_patientInTime).setEnabled(false);
        getView(R.id.bt_changeSync).setVisibility(View.VISIBLE);
        getView(R.id.bt_edit).setVisibility(View.VISIBLE);
        getView(R.id.bt_save).setVisibility(View.GONE);
        getView(R.id.bt_In).setVisibility(View.GONE);
    }

    @Override
    public void setUIEditPatient() {
        getView(R.id.et_patientName).setEnabled(true);
        getView(R.id.et_patientAge).setEnabled(true);
        getView(R.id.et_patientBed).setEnabled(true);
        getView(R.id.et_patientInTime).setEnabled(true);
        getView(R.id.bt_changeSync).setVisibility(View.GONE);
        getView(R.id.bt_edit).setVisibility(View.GONE);
        getView(R.id.bt_save).setVisibility(View.VISIBLE);
        getView(R.id.bt_In).setVisibility(View.GONE);
    }

    @Override
    public void setUIAddPatient() {

        uiComponentLostFocuse();
        getView(R.id.et_patientName).setEnabled(true);
        ((EditText) getView(R.id.et_patientName)).setText("");
        getView(R.id.et_patientAge).setEnabled(true);
        ((EditText) getView(R.id.et_patientAge)).setText("");
        getView(R.id.et_patientBed).setEnabled(true);
        ((EditText) getView(R.id.et_patientBed)).setText("");
        getView(R.id.et_patientInTime).setEnabled(true);
        ((EditText) getView(R.id.et_patientInTime)).setText("");

        getView(R.id.bt_changeSync).setVisibility(View.GONE);
        getView(R.id.bt_edit).setVisibility(View.GONE);
        getView(R.id.bt_save).setVisibility(View.GONE);
        getView(R.id.bt_In).setVisibility(View.VISIBLE);
    }

    @Override
    public void toastInfo(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFakeOButtonUsability(boolean isusable) {
        getView(R.id.layout).requestFocus();
    }

    @Override
    public void setSaveEditButtonUsability(boolean isusable) {
        getView(R.id.bt_save).setEnabled(isusable);
    }

    @Override
    public void setPatientInButtonUsability(boolean isusable) {
        getView(R.id.bt_In).setEnabled(isusable);
    }

    private void uiComponentLostFocuse(){
        getView(R.id.layout).requestFocus();
    }

    @Override
    public Activity getActivity() {
        return null;
    }

    @Override
    public Fragment getFragment() {
        return null;
    }
}
