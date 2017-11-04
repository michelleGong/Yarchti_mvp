package com.yarward.mvp1test.archti_3_mvp_databinding.bedp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.yarward.DemoApplication;
import com.yarward.R;
import com.yarward.mvp1test.archti_3_mvp_databinding.bean.Bed;
import com.yarward.mvp1test.archti_3_mvp_databinding.editpatient.PatientInActivity;
import com.yarward.basemvp.model.IBusiness;
import com.yarward.basemvp.presenter.FragmentPresenter;

/**
 * Created by Michelle_Hong on 2017/9/4 0004.
 *
 * @des
 */

public class BedPLayoutFragment extends FragmentPresenter<BedPLayoutViewDelegate> implements BedPLayoutConstract.IBedPLayoutPresenter{

    private BedPLayoutBusiness bedPLayoutBusiness;

    @Override
    protected Class<BedPLayoutViewDelegate> getDelegateClass() {
        return BedPLayoutViewDelegate.class;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            patientInSelectInterface = (PatientInSelectInterface)context;
            Log.d("aaa","onAttach....");
        } catch (Exception e) {
            throw new RuntimeException("Activity should implements PatientInSelectInterface");
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bedPLayoutBusiness = new BedPLayoutBusiness();
        Log.d("aaa","onCreate....");
    }

    /**
     * 生命周期方法的控制
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(patientInSelectInterface == null){
            patientInSelectInterface = (PatientInSelectInterface) getActivity();
        }
        Log.d("aaa","oncreateView....");
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        //初始化床位一览表
        initBedPLayout();



        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("aaa","onActivityCreated....");
    }

    public PatientInSelectInterface patientInSelectInterface;



    public List<Bed> getCachedBedLayoutDatas(){
        return DemoApplication.instance().getCachedbedLayouts();
    }


    @Override
    protected void initViewDelegate() {

    }

    @Override
    protected void bindEventListener() {

        viewDelegate.setRecycleViewItemClickListener(new BedPLayoutViewDelegate.OnRecycleViewItemClickListener<Bed>() {
            @Override
            public void onItemClick(View item, int position, Bed data) {
                Bed currentBed =  DemoApplication.instance().getCachedbedLayouts().get(position);
                if(currentBed != null && currentBed.getCurrentpatient() != null){
                    Log.d("111","展示病人详细信息");
                    showPatientDetail(currentBed);
                }else{
                    Log.d("111","跳转添加病人页面");
                    gotoPatientInEditPage(currentBed);
                }
            }
        });

      /*  ((RecyclerView)viewDelegate.getView(R.id.rv_bedPLayout)).addOnItemTouchListener(new SimpleRecyclerViewItemClickListener(new SimpleRecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               Bed currentBed =  DemoApplication.instance().getCachedbedLayouts().get(position);
                if(currentBed != null && currentBed.getCurrentpatient() != null){
                    Log.d("111","展示病人详细信息");
                    showPatientDetail(currentBed);
                }else{
                    Log.d("111","跳转添加病人页面");
                    gotoPatientInEditPage(currentBed);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }

            @Override
            public void onItemDoubleClick(View view, int postion) {

            }
        }));*/
    }


    @Override
    public void initBedPLayout() {

        //初始化RecyclerView
        viewDelegate.initBedLayoutRecyclerView(getActivity());
        //显示progress Dialog
        viewDelegate.showLoadingBedPLayoutProgressDialog("正在加载",getActivity());
        bedPLayoutBusiness.loadBedPatientLayoutData(new IBusiness.Callback<List<Bed>>() {
            @Override
            public void onResult(List<Bed> bedList) {

                viewDelegate.dimissLoadingBedPLayoutProgressDialog();

//                viewDelegate.showBedpLayout(bedList,getActivity());
                //databinding data变化notify UI变化

                viewDelegate.showBedpLayout(DemoApplication.instance().getCachedbedLayouts(),getActivity());

                //cached just弥补
                if(getCachedBedLayoutDatas() == null){
                    DemoApplication.instance().setCachedbedLayouts(bedList);
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });


        //databinding测试，后台修改床位一览数据
        testModifyDataBackground();
    }


    /**
     * databinding do this
     */
    @Override
    public void refreshBedPLayout() {

    }

    @Override
    public void gotoPatientInEditPage(Bed bed) {
        Intent intent = new Intent(getActivity(),PatientInActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CurrentBed",bed);
        intent.putExtra("Argument",bundle);
        startActivity(intent);
    }

    @Override
    public void showPatientDetail(Bed currentPatient) {

        if(patientInSelectInterface != null){
            patientInSelectInterface.onPatientSelect(currentPatient);

        }
    }

    public interface PatientInSelectInterface{
        void onPatientSelect(Bed currentPatient);
    }

    @Override
    public void testModifyDataBackground() {
        bedPLayoutBusiness.testDataBinding(null);

    }
}
