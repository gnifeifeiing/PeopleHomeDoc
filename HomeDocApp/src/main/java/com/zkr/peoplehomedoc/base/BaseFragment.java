package com.zkr.peoplehomedoc.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkr.peoplehomedoc.utils.OptsharepreInterface;


/**
 * Created by lenovo on 2016/3/16.
 */
public abstract class BaseFragment extends Fragment {
    public View view;
    public OptsharepreInterface share;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
