package com.yarward.basemvp2.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

/**
 * Created by Michelle_Hong on 10/27/2017.
 *
 * @des Presenter层接口
 */

public interface IPresenter {

    // for Activity View and Fragment View LifeCycle Controller
    public void onCreate(@Nullable Bundle savedInstanceState);

    public void onResume();

    public void onStart();

    public void onRestart();

    public void onPause();

    public void onStop();

    public void onDestroy();

    public void onSaveInstanceState(Bundle outState) ;

    public void onRestoreInstanceState(Bundle savedInstanceState);

    public void onActivityResult(int requestCode, int resultCode, Intent data);


    // only for Fragment
    public void onAttach();

    public void onCreateView(Bundle savedInstanceState);

    public void onActivityCreated();

    public void onDestroyView();

    public void onDetach();

    public void onResult();



}
