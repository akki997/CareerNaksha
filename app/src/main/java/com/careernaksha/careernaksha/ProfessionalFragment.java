package com.careernaksha.careernaksha;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfessionalFragment extends Fragment {

EditText pr1,pr2,pr3,pr4,pr5,pr6;
TextView tp,tp1;
Button bpr;
FirebaseAuth auth;
DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_professional, container, false);
        pr1=view.findViewById(R.id.pr1);
        pr2=view.findViewById(R.id.pr2);
        pr3=view.findViewById(R.id.pr3);
        pr4=view.findViewById(R.id.pr4);
        pr5=view.findViewById(R.id.pr5);
        pr6=view.findViewById(R.id.pr6);
        tp=view.findViewById(R.id.tp);
        tp1=view.findViewById(R.id.tp1);
        bpr=view.findViewById(R.id.bpr);
        auth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Profile");
        bpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pr1.getText().toString().equals("") || pr2.getText().toString().equals("") || pr3.getText().toString().equals("") || pr4.getText().toString().equals("")
                        || pr5.getText().toString().equals("") || pr6.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Please fill required fields of fill NA....!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FirebaseUser user=auth.getCurrentUser();
                    if(user!=null)
                    {
                        send();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new InterestFragment()).addToBackStack(null).commit();

                    }
                }
            }
        });
        return  view;
    }

    private void send() {

        String j1pos=pr1.getText().toString();
        String j1loc=pr2.getText().toString();
        String j1salary=pr3.getText().toString();
        String j2pos=pr4.getText().toString();
        String j2loc=pr5.getText().toString();
        String j2salary=pr6.getText().toString();
        Professional professional=new Professional(j1pos,j1loc,j1salary,j2pos,j2loc,j2salary);
        FirebaseUser user=auth.getCurrentUser();
        databaseReference.child(user.getDisplayName()).push().setValue(professional);
    }


}
