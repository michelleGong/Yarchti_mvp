package com.yarward.mvp1test.archti_2_mvvmp.patient.patientDetail;


import android.app.Activity;
import android.app.FragmentManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yarward.mvp1test.R;
import com.yarward.mvp1test.archti_2_mvvmp.bean.Patient;
import com.yarward.basemvp.view.BaseViewDelegate;
import com.yarward.mvp1test.widget.ConfirmDialog;

/**
 * Created by Michelle_Hong on 2017/8/30 0030.
 * View
 * @des
 */

public class PatientDetailViewDelegate extends BaseViewDelegate implements PatientDetailConstract.IPatientDetailView {

    private ConfirmDialog confirmDialog;
    private FragmentManager fragmentManager;
    private final String INFO_DIALOG_TAG = "confirmDialog";
    private Activity mActivity;


    public void setmActivity(Activity mActivity){
        this.mActivity = mActivity;
    }

    public void setFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_patient_detail;
    }

    /**
     * 加载view，默认初始化操作
     * 加载该页面时，只展示病人数据，因此将EditText设置为disable
     */
    @Override
    public void initView() {
        setUIShowPatientDetails();
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
        uiComponentLostFocuse();
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
    public void showInfoDialog(String msg) {
        if(fragmentManager == null){
            throw new RuntimeException("PatientDetailViewDelegate need set the FragmentManager");
        }
        confirmDialog = (ConfirmDialog) fragmentManager.findFragmentByTag(INFO_DIALOG_TAG);
        if(confirmDialog == null){
            confirmDialog = ConfirmDialog.newInstance(msg);
        }
        confirmDialog.show(fragmentManager,INFO_DIALOG_TAG);
    }

    @Override
    public void toastInfo(String msg) {
        if(mActivity == null){
            throw new RuntimeException("PatientDetailViewDelegate need set the Activity");
        }
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    private void uiComponentLostFocuse(){
        getView(R.id.layout).requestFocus();
    }

    @Override
    public void setFakeOButtonUsability(boolean isusable) {
        getView(R.id.bt_changeSync).setEnabled(isusable);
    }

    @Override
    public void setSaveEditButtonUsability(boolean isusable) {
        getView(R.id.bt_save).setEnabled(isusable);
    }

    @Override
    public void setPatientInButtonUsability(boolean isusable) {
        getView(R.id.bt_In).setEnabled(isusable);
    }
}
