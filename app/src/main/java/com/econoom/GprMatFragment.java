package com.econoom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sebastiao on 14/03/2016.
 */
public class GprMatFragment extends android.support.v4.app.Fragment {

    private View view;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            /*ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);*/
            view = inflater.inflate(R.layout.gpr_mat_fragment, container, false);

        }

        return view;

    }

}
