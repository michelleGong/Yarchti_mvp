package com.yarward.mvp1test.archti_3_mvp_databinding.bedp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.List;

import com.yarward.BR;
import com.yarward.R;
import com.yarward.mvp1test.archti_3_mvp_databinding.bean.Bed;
import com.yarward.basemvp.view.BaseViewDelegate;
import com.yarward.mvp1test.widget.recycleview.GridDividerItemDecoration;


/**
 * Created by Michelle_Hong on 2017/9/4 0004.
 *
 * @des
 */

public class BedPLayoutViewDelegate extends BaseViewDelegate implements BedPLayoutConstract.IBedPLayoutView{

    private QMUITipDialog loadingBedLayoutProgressDialog;
    private RecyclerView.Adapter adapter;

    private OnRecycleViewItemClickListener recycleViewItemClickListener;

    public interface OnRecycleViewItemClickListener<T>{
        void onItemClick(View item,int position,T data);
    }

    public void setRecycleViewItemClickListener(OnRecycleViewItemClickListener onRecycleViewItemClickListener){
        this.recycleViewItemClickListener =  onRecycleViewItemClickListener;
    }




    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_bedpatientlayout;
    }

    @Override
    public void initView() {
        bindView(R.id.rv_bedPLayout);
    }

    @Override
    public void initBedLayoutRecyclerView(Context mContext) {
        RecyclerView recyclerView = (RecyclerView)getView(R.id.rv_bedPLayout);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,5));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridDividerItemDecoration(8,mContext.getResources().getColor(android.R.color.transparent)));

    }

    @Override
    public void showLoadingBedPLayoutProgressDialog(String msg, Context activityContext) {

        loadingBedLayoutProgressDialog = new QMUITipDialog.Builder(activityContext)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING).setTipWord(msg).create();
        loadingBedLayoutProgressDialog.show();
    }

    @Override
    public void dimissLoadingBedPLayoutProgressDialog() {
        loadingBedLayoutProgressDialog.dismiss();
    }


    @Override
    public void showBedpLayout(List<Bed> bedList,Context mContext) {
        if(adapter == null){
//            adapter = new BedPLayoutAdapter(bedList,mContext);
            adapter = new BedPLayoutAdatper_Bind(bedList,mContext);
            ((RecyclerView)getView(R.id.rv_bedPLayout)).setAdapter(adapter);
        }

        //when use databinding,reset the adapte data and notify is needless.--MichelleHong 0908
     /*   else{
            adapter.setBedList(bedList);
            adapter.notifyDataSetChanged();
        }*/

    }


    /**
     * useless
     * @param newBed
     * @param position
     */
    @Override
    public void refreshABedPLayout(Bed newBed, int position) {

    }





    class BedPLayoutAdatper_Bind extends RecyclerView.Adapter<BedPLayoutAdatper_Bind.BViewHolder>{

        private List<Bed> beds;
        private Context mContext;


        public BedPLayoutAdatper_Bind(List<Bed> beds,Context mContext) {
            this.beds = beds;
            this.mContext = mContext;
        }


        @Override
        public BViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.item_grid_bedp,parent,false);
            BViewHolder viewHolder = new BViewHolder(viewDataBinding.getRoot());
            viewHolder.setViewDataBinding(viewDataBinding);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final BViewHolder holder, int position) {
            holder.getViewDataBinding().setVariable(BR.beditem,beds.get(position));
            holder.getViewDataBinding().executePendingBindings();
            holder.getViewDataBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p = holder.getLayoutPosition();
                    if(recycleViewItemClickListener != null){
                        recycleViewItemClickListener.onItemClick(v,p,beds.get(p));
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return beds.size();
        }

        class BViewHolder extends RecyclerView.ViewHolder{

            ViewDataBinding viewDataBinding;

            public ViewDataBinding getViewDataBinding() {
                return viewDataBinding;
            }

            public void setViewDataBinding(ViewDataBinding viewDataBinding) {
                this.viewDataBinding = viewDataBinding;
            }

            public BViewHolder(View itemView) {
                super(itemView);
            }
        }
    }





    class BedPLayoutAdapter extends RecyclerView.Adapter<BedPLayoutAdapter.MyViewHolder>{

        private List<Bed> bedList;
        private Context context;


        public BedPLayoutAdapter(List<Bed> bedList,Context context) {
            this.bedList = bedList;
            this.context = context;

        }

        public List<Bed> getBedList() {
            return bedList;
        }


        public void setBedList(List<Bed> bedList){
            this.bedList = bedList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_grid_bedp,parent,false);
            MyViewHolder viewHolder = new MyViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Bed currentBed = bedList.get(position);
            holder.tv_bedName.setText(currentBed.getBedName());
            holder.tv_patientAge.setText(currentBed.getCurrentpatient() == null ? " " : currentBed.getCurrentpatient().getPatientAge()+"");
            holder.tv_patientName.setText(currentBed.getCurrentpatient() == null ? " " : currentBed.getCurrentpatient().getPatientName());
        }

        @Override
        public int getItemCount() {
            return bedList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView tv_bedName;
            TextView tv_patientName;
            TextView tv_patientAge;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv_bedName = (TextView) itemView.findViewById(R.id.tv_bedNo);
                tv_patientName = (TextView) itemView.findViewById(R.id.tv_patientName);
                tv_patientAge = (TextView) itemView.findViewById(R.id.tv_age);
            }
        }
    }



}
