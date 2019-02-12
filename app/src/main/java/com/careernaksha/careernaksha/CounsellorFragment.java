package com.careernaksha.careernaksha;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class CounsellorFragment extends Fragment {

CardView cview1,cview2,cview3,cview4,cview5;
FirebaseAuth auth;
    DatabaseReference databaseReference1,databaseReference2,databaseReference3,databaseReference4,databaseReference5;
long count=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_counsellor, container, false);
        cview1=view.findViewById(R.id.cview1);
        cview2=view.findViewById(R.id.cview2);
        cview3=view.findViewById(R.id.cview3);
        cview4=view.findViewById(R.id.cview4);
        cview5=view.findViewById(R.id.cview5);
        auth=FirebaseAuth.getInstance();
        databaseReference1= FirebaseDatabase.getInstance().getReference().child("Nimish");
        databaseReference2=FirebaseDatabase.getInstance().getReference().child("Shikha");
        databaseReference3=FirebaseDatabase.getInstance().getReference().child("Sumit");
        databaseReference4=FirebaseDatabase.getInstance().getReference().child("Shiva");
        databaseReference5=FirebaseDatabase.getInstance().getReference().child("Deepa");
        cview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // FirebaseUser user=auth.getCurrentUser();
             //databaseReference1.child(user.getDisplayName()).setValue(count);
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new PickSlotFragment()).addToBackStack(null).commit();
            }
        });

        cview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new PickSlotFragment()).addToBackStack(null).commit();

            }
        });

        cview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new PickSlotFragment()).addToBackStack(null).commit();
            }
        });

        cview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new PickSlotFragment()).addToBackStack(null).commit();
            }
        });

        cview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new PickSlotFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }



}
