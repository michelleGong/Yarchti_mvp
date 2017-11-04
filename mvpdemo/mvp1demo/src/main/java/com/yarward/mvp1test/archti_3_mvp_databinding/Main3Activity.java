package com.yarward.mvp1test.archti_3_mvp_databinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yarward.R;
import com.yarward.mvp1test.archti_3_mvp_databinding.bean.Bed;
import com.yarward.mvp1test.archti_3_mvp_databinding.bedp.BedPLayoutFragment;
import com.yarward.mvp1test.archti_3_mvp_databinding.currentpatient.CurrentPatientFragment;


public class Main3Activity extends AppCompatActivity implements BedPLayoutFragment.PatientInSelectInterface {

    private BedPLayoutFragment bedPLayoutFragment;
    private CurrentPatientFragment currentPatientFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        if(savedInstanceState == null){

            bedPLayoutFragment = new BedPLayoutFragment();
            currentPatientFragment = new CurrentPatientFragment();
            getFragmentManager().beginTransaction().add(R.id.layout_bedPLayout,bedPLayoutFragment).add(R.id.layout_currentPatient,currentPatientFragment).commit();

        }
    }


    @Override
    public void onPatientSelect(Bed currentPatient) {
       currentPatientFragment.showCurrentPatient(currentPatient);
    }
}
