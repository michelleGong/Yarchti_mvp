package com.yarward.mvp1test.archti_3_mvp_databinding.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.yarward.BR;

/**
 * Created by Michelle_Hong on 2017/9/4 0004.
 *
 * @des
 */

public class Patient extends BaseObservable{

    private String patientId;
    private String patientName;
    private int patientAge;
    private Bed bed;
    private String inTime;

    @Bindable
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
        notifyPropertyChanged(BR.patientId);
    }

    @Bindable
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
        notifyPropertyChanged(BR.patientName);
    }

    @Bindable
    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
        notifyPropertyChanged(BR.patientAge);
    }

    @Bindable
    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
        notifyPropertyChanged(BR.bed);
    }

    @Bindable
    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
        notifyPropertyChanged(BR.inTime);
    }
}
