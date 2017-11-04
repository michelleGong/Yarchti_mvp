package com.yarward.mvp1test.archti_2_mvvmp.bean;

import com.yarward.basemvp.model.IModel;

/**
 * Created by Michelle_Hong on 2017/8/30 0030.
 *
 * @des
 */

public class Patient implements IModel {

    private String name;
    private int age;
    private String bedName;
    private String inTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBedName() {
        return bedName;
    }

    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
