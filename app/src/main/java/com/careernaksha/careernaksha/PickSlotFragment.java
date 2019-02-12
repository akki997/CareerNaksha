package com.careernaksha.careernaksha;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class PickSlotFragment extends Fragment {


    public PickSlotFragment() {
        // Required empty public constructor
    }
 Button btnmon,btntues,btnwed,btnthurs,btnfri,btnsat,btnsun;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pick_slot, container, false);
        btnmon=view.findViewById(R.id.btnmon);
        btntues=view.findViewById(R.id.btntues);
        btnwed=view.findViewById(R.id.btnwed);
        btnthurs=view.findViewById(R.id.btnthurs);
        btnfri=view.findViewById(R.id.btnfri);
        btnsat=view.findViewById(R.id.btnsat);
        btnsun=view.findViewById(R.id.btnsun);
        btnmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new PickTimeFragment()).addToBackStack(null).commit();
            }
        });
        btntues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new PickTimeFragment()).addToBackStack(null).commit();
            }
        });
        btnwed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new PickTimeFragment()).addToBackStack(null).commit();
            }
        });
        btnthurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new PickTimeFragment()).addToBackStack(null).commit();
            }
        });
        btnfri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new PickTimeFragment()).addToBackStack(null).commit();
            }
        });
        btnsat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new PickTimeFragment()).addToBackStack(null).commit();
            }
        });
        btnsun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new PickTimeFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }

}
