package com.yarward.mvp1test.archti_3_mvp_databinding.bedp;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import com.yarward.mvp1test.archti_3_mvp_databinding.bean.Bed;
import com.yarward.basemvp.model.IBusiness;
import com.yarward.basemvp.presenter.IPresenter;
import com.yarward.basemvp.view.IView;

/**
 * Created by Michelle_Hong on 2017/9/4 0004.
 *
 *  病人床位一览Constract
 * @des
 */

public interface BedPLayoutConstract {

    interface IBedPLayoutModel extends IBusiness{

        /**
         * 加载床位一览表数据
         * @param bedPLayoutCallBack
         */
        void loadBedPatientLayoutData(@NonNull Callback<List<Bed>> bedPLayoutCallBack);


        void testDataBinding(Callback<Bed> patientDetailCallBack);


    }

    interface IBedPLayoutView extends IView{

        /**
         * 显示加载一览表progress Dialog
         * @param msg
         */
        void showLoadingBedPLayoutProgressDialog(String msg,Context mContext);

        /**
         * dimiss 加载一览表 progress dialog
         */
        void dimissLoadingBedPLayoutProgressDialog();

        void initBedLayoutRecyclerView(Context mContext);

        /**
         * 显示数据到一览表
         * @param bedList
         */
        void showBedpLayout(List<Bed> bedList,Context mContext);

        /**
         * useless
         * @param newBed
         * @param position
         */
        void refreshABedPLayout(Bed newBed,int position);

    }



    interface IBedPLayoutPresenter extends IPresenter{

        /**
         * 加载一览表
         */
        void initBedPLayout();

        /**
         * 刷新一览表
         */
        void refreshBedPLayout();

        /**
         * 跳转病人编辑页面
         */
        void gotoPatientInEditPage(Bed bed);
        /**
         * 给详情页面当前要显示的病人
         * @param currentPatient
         */
        void showPatientDetail(Bed currentPatient);

        void testModifyDataBackground();

    }
}
