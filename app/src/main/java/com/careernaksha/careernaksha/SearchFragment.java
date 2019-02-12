package com.careernaksha.careernaksha;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


   EditText ed1;
  RecyclerView rv;
  DatabaseReference databaseReference;
  FirebaseAuth auth;
  FirebaseUser user;
  ArrayList<String>CollegeName;
  ArrayList<String>Exam;
    ArrayList<String>Loc;
  SearchAdapter searchAdapter;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        ed1=view.findViewById(R.id.ed1);
        rv=view.findViewById(R.id.rv);
        auth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        CollegeName=new ArrayList<>();
        Exam=new ArrayList<>();
        Loc=new ArrayList<>();
        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty())
                {setAdapter(s.toString());

                }
                else
                {
                    CollegeName.clear();
                    Exam.clear();
                    Loc.clear();
                    rv.removeAllViews();
                }

            }
        });
        return view;
    }

    private void setAdapter(final String string) {
        user=auth.getCurrentUser();
        databaseReference.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CollegeName.clear();
                Exam.clear();
                Loc.clear();
                rv.removeAllViews();
                int counter=0;
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    String uid=snapshot.getKey();
                    String name=snapshot.child("collegename").getValue(String.class);
                    String email=snapshot.child("exams_fee_mode_duration").getValue(String.class);
                    String loc=snapshot.child("location").getValue(String.class);


                    if(name.toLowerCase().contains(string.toLowerCase()))
                    {

                        CollegeName.add(name);
                        Exam.add(email);
                        Loc.add(loc);
                        counter++;
                    }
                   else if(name.toLowerCase().equals(string.toLowerCase()))
                    {
                        CollegeName.add(name);
                        Exam.add(email);
                        Loc.add(loc);
                        counter++;
                    }


                       else if (!name.toLowerCase().contains(string.toLowerCase())) {
                            //  Toast.makeText(getContext(), "Showing results for..", Toast.LENGTH_SHORT).show();
                            String st = string.substring(0, 1);
                            if (name.toLowerCase().equals(st.toLowerCase())) {

                                CollegeName.add(name);
                                Exam.add(email);
                                Loc.add(loc);
                                counter++;
                            }
                            else if(name.substring(0,1).toLowerCase().contains(st.toLowerCase()))
                            {

                                CollegeName.add(name);
                                Exam.add(email);
                                Loc.add(loc);
                                counter++;
                            }

                        }



                    if(counter==15)
                        break;
                }
                searchAdapter=new SearchAdapter(getContext(),CollegeName,Exam,Loc);
                rv.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
