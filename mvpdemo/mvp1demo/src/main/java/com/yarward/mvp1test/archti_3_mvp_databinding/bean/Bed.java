package com.yarward.mvp1test.archti_3_mvp_databinding.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;

import com.yarward.mvp1test.BR;

/**
 * Created by Michelle_Hong on 2017/9/4 0004.
 *
 * @des
 */

public class Bed extends BaseObservable implements Serializable {

    private int bedId;
    private String bedName;
    private Patient currentpatient;

    @Bindable
    public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
        notifyPropertyChanged(BR.bedId);
    }

    @Bindable
    public String getBedName() {
        return bedName;
    }

    public void setBedName(String bedName) {
        this.bedName = bedName;
        notifyPropertyChanged(BR.bedName);
    }

    @Bindable
    public Patient getCurrentpatient() {
        return currentpatient;
    }

    public void setCurrentpatient(Patient currentpatient) {
        this.currentpatient = currentpatient;
        notifyPropertyChanged(BR.currentpatient);
    }
}
