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
public class PersonalFragment extends Fragment {

EditText ep1,ep2,ep3,ep4,ep5,ep6,ep7,ep8,ep9,ep10,ep11,ep12;
Button bp;
    DatabaseReference databaseReference;
    FirebaseAuth auth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_personal, container, false);
       ep1=view.findViewById(R.id.ep1);
        ep2=view.findViewById(R.id.ep2);
        ep3=view.findViewById(R.id.ep3);
        ep4=view.findViewById(R.id.ep4);
        ep5=view.findViewById(R.id.ep5);
        ep6=view.findViewById(R.id.ep6);
        ep7=view.findViewById(R.id.ep7);
        ep8=view.findViewById(R.id.ep8);
        ep9=view.findViewById(R.id.ep9);
        ep10=view.findViewById(R.id.ep10);
        ep11=view.findViewById(R.id.ep11);
        ep12=view.findViewById(R.id.ep12);
        auth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Profile");
        bp=view.findViewById(R.id.bp);
        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ep1.getText().toString().equals("") || ep2.getText().toString().equals("") || ep3.getText().toString().equals("") || ep4.getText().toString().equals("") ||
                        ep5.getText().toString().equals("") || ep6.getText().toString().equals("") || ep7.getText().toString().equals("") || ep8.getText().toString().equals("") ||
                        ep9.getText().toString().equals("") || ep10.getText().toString().equals("") || ep11.getText().toString().equals("") || ep12.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Please fill required fields or fill  NA...!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FirebaseUser user=auth.getCurrentUser();
                    if(user!=null)
                    {
                        save();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new EducationFragment()).addToBackStack(null).commit();
                    }
                }
            }
        });

       return view;
    }

    private void save() {

        String gender=ep1.getText().toString();
        String marital=ep2.getText().toString();
        String familymembers=ep3.getText().toString();
        String facebook=ep4.getText().toString();
        String linkedin=ep5.getText().toString();
        String twitter=ep6.getText().toString();
        String ocupation=ep7.getText().toString();
        String address=ep8.getText().toString();
        String familyincome=ep9.getText().toString();
        String skills=ep10.getText().toString();
        String extracurricular=ep11.getText().toString();
        String highesteducation=ep12.getText().toString();
        Personal personal=new Personal(gender,marital,familymembers,facebook,linkedin,twitter,ocupation,address,familyincome,skills,extracurricular
        ,highesteducation);
        FirebaseUser firebaseUser=auth.getCurrentUser();
        databaseReference.child(firebaseUser.getDisplayName()).push().setValue(personal);
    }

}
