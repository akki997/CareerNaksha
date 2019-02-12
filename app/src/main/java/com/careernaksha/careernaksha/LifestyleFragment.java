package com.careernaksha.careernaksha;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class LifestyleFragment extends Fragment {

EditText el1,el2,el3,el4,el5;
Button bl;
FirebaseAuth auth;
DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_lifestyle, container, false);
        el1=view.findViewById(R.id.el1);
        el2=view.findViewById(R.id.el2);
        el3=view.findViewById(R.id.el3);
        el4=view.findViewById(R.id.el4);
        el5=view.findViewById(R.id.el5);
        bl=view.findViewById(R.id.bl);
        auth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Profile");
        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(el1.getText().toString().equals("") || el2.getText().toString().equals("") || el3.getText().toString().equals("") || el4.getText().toString().equals("")
                        || el5.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Please fill required fields or fill NA...!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FirebaseUser user=auth.getCurrentUser();
                    if(user!=null)
                    {
                        save();
                       getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new ProfileFragment()).addToBackStack(null).commit();
                        Toast.makeText(getContext(), "Profile Successfully Updated..!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    private void save() {

        String entertainment=el1.getText().toString();
        String living=el2.getText().toString();
        String food=el3.getText().toString();
        String travel=el4.getText().toString();
        String miscellaneous=el5.getText().toString();
        Lifestyle lifestyle=new Lifestyle(entertainment,living,food,travel,miscellaneous);
        FirebaseUser user=auth.getCurrentUser();
        databaseReference.child(user.getDisplayName()).push().setValue(lifestyle);

    }

}
