package com.careernaksha.careernaksha;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


 TextView tvbuild;
 CardView cvbuild;
 FirebaseUser user;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_pfofile, container, false);
       tvbuild=view.findViewById(R.id.tvbuild);
       cvbuild=view.findViewById(R.id.cvbuild);
       user= FirebaseAuth.getInstance().getCurrentUser();
       if(user!=null)
       {
           String name=user.getDisplayName();
           tvbuild.setText("Hi "+name);
       }
       cvbuild.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new BasicFragment()).addToBackStack(null).commit();
           }
       });


       return view;
    }

}
