package com.evan.etcweb.main.inspire;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evan.etcweb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InspireFragment extends Fragment {


    public InspireFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inspire, container, false);
    }

}
