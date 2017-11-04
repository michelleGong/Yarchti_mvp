package com.yarward.mvp1test.archti_3_mvp_databinding.editpatient;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yarward.MyPatientInDataBinding;
import com.yarward.R;
import com.yarward.mvp1test.archti_3_mvp_databinding.bean.Bed;
import com.yarward.basemvp.presenter.ActivityPresenter;


/**
 * DataBinding双向绑定
 */
public class PatientInActivity extends ActivityPresenter<PatientInViewDelegate> implements PatientInConstract.PatientInPresenter {

    MyPatientInDataBinding patientInDataBinding;
    private PatientInBusiness patientInBusiness;
    private Bed currentBed;

    @Override
    protected Class<PatientInViewDelegate> getDelegateClass() {
        return PatientInViewDelegate.class;
    }

    @Override
    protected void initViewDelegate() {

    }

    @Override
    protected void bindEventListener() {

        viewDelegate.getView(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PatientInActivity.this.finish();
            }
        });

        viewDelegate.getView(R.id.bt_inPatient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPatientIn(currentBed);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent data = getIntent();
        if(data == null){
            Log.d("***111","null null null null");
            return;
        }
        Bundle bundle = data.getBundleExtra("Argument");
        currentBed = (Bed)bundle.getSerializable("CurrentBed");
        Log.d("***111",currentBed.getBedName());
        patientInDataBinding = (MyPatientInDataBinding) viewDelegate.getViewDataBinding();
        patientInDataBinding.setBed(currentBed);

        patientInBusiness = new PatientInBusiness();
    }


    @Override
    public void doPatientIn(Bed bed) {

//        Patient patient = new Patient();

        Log.d("111111",bed.getBedName()+"    "+bed.getCurrentpatient().getPatientName());
        //since保存会，立刻更新了Application中的缓存集合，gridView也用了databind了缓存集合，因此不需要手动更新UI
        patientInBusiness.savePatient(bed, new PatientInConstract.PatientInModel.PatientInCallback() {
            @Override
            public void onSuccess() {
                PatientInActivity.this.finish();
            }
        });
    }
}
