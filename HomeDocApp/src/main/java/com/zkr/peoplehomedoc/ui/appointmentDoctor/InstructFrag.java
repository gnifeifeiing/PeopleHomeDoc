package com.zkr.peoplehomedoc.ui.appointmentDoctor;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkr.peoplehomedoc.R;
/**
 * @Description: -------预约须知
 * @author --------------LF
 * @date ----------------2016/10/8 14:48                
 */  
public class InstructFrag extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_instruct_frag,null);
        return view;
    }
}
