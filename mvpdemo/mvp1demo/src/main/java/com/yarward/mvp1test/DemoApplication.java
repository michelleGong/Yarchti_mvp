package com.yarward.mvp1test;

import android.app.Application;
import android.content.Context;


import com.yarward.mvp1test.archti_3_mvp_databinding.bean.Bed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Michelle_Hong on 2017/8/31 0031.
 *
 * @des
 */

public class DemoApplication extends Application {

    private List<Bed> cachedbedLayouts; //对APP回收未做处理
    private static Context demoApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        demoApplication = getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        cachedbedLayouts = Collections.synchronizedList(new ArrayList<Bed>());
    }


    public static DemoApplication instance(){
        return (DemoApplication)demoApplication;

    }

    public static Context getDemoApplicationContext(){
        return demoApplication;
    }


    public List<Bed> getCachedbedLayouts() {
        return cachedbedLayouts;
    }

    public void setCachedbedLayouts(List<Bed> cachedbedLayouts) {
        this.cachedbedLayouts = cachedbedLayouts;
    }

    public synchronized void updateCachedBedLayoutPatient(Bed targetBed){
        for(Bed bed : cachedbedLayouts){
            if(bed.getBedName().equals(targetBed.getBedName())){
                bed.setCurrentpatient(targetBed.getCurrentpatient());
            }
        }
    }
}
