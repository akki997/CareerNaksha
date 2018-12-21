package com.careernaksha.careernaksha;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    Button b, b1, bl1, bl2;
    EditText et1, et2, et3, et4, e1, e2;
    TextView tv;
     private FirebaseAuth mAuth;
     DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        b = (Button) findViewById(R.id.b);
        b1 = (Button) findViewById(R.id.b1);
        bl1 = (Button) findViewById(R.id.bl1);
        bl2 = (Button) findViewById(R.id.bl2);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("user");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab=new AlertDialog.Builder(HomeActivity.this);
                View v= LayoutInflater.from(getApplicationContext()).inflate(R.layout.login,null);
                e1= v.findViewById(R.id.e1);
                e2=v.findViewById(R.id.e2);
                bl1=v.findViewById(R.id.bl1);
                bl2=v.findViewById(R.id.bl2);
                bl1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(HomeActivity.this, "Register Yourself", Toast.LENGTH_SHORT).show();
                    }
                });



                ab.setView(v);
                ab.create();
                ab.show();
                bl2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder ab=new AlertDialog.Builder(HomeActivity.this);
                        View v= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item,null);
                        et1= v.findViewById(R.id.et1);
                        et2=v.findViewById(R.id.et2);
                        et3=v.findViewById(R.id.et3);
                        et4=v.findViewById(R.id.et4);
                        b1=v.findViewById(R.id.b1);
                        b1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (et1.getText().toString().equals("")||et2.getText().toString().equals("")||et3.getText().toString().equals("") || et4.getText().toString().equals("")) {

                                    Toast.makeText(HomeActivity.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                                } else {
                                    mAuth.createUserWithEmailAndPassword(et3.getText().toString(), et4.getText().toString())
                                            .addOnCompleteListener(HomeActivity.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        // Sign in success, update UI with the signed-in user's information

                                                        FirebaseUser user = mAuth.getCurrentUser();
                                                        AddData();
                                                        AlertDialog.Builder ab = new AlertDialog.Builder(HomeActivity.this);
                                                        View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.login, null);
                                                        e1 = v.findViewById(R.id.e1);
                                                        e2 = v.findViewById(R.id.e2);
                                                        bl1 = v.findViewById(R.id.bl1);
                                                        bl2 = v.findViewById(R.id.bl2);


                                                        bl1.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                if (e1.getText().toString().equals("") || e2.getText().toString().equals("")) {
                                                                    Toast.makeText(HomeActivity.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                                                                }
                                                                else {
                                                                    mAuth.signInWithEmailAndPassword(e1.getText().toString(), e2.getText().toString())
                                                                            .addOnCompleteListener(HomeActivity.this, new OnCompleteListener<AuthResult>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        // Sign in success, update UI with the signed-in user's information

                                                                                        FirebaseUser user = mAuth.getCurrentUser();
                                                                                         Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
                                                                                         startActivity(i);
                                                                                         finish();

                                                                                    } else {
                                                                                        // If sign in fails, display a message to the user.
                                                                                        Toast.makeText(HomeActivity.this, "Enter Details Carefully", Toast.LENGTH_SHORT).show();
                                                                                    }

                                                                                    // ...
                                                                                }
                                                                            });

                                                                }
                                                            }
                                                        });


                                                        ab.setView(v);
                                                        ab.create();
                                                        ab.show();
                                                        Toast.makeText(HomeActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();

                                                    } else {
                                                        // If sign in fails, display a message to the user.
                                                        Toast.makeText(HomeActivity.this, "Please enter details carefully", Toast.LENGTH_SHORT).show();
                                                    }

                                                    // ...
                                                }
                                            });

                                }
                            }
                        });

                        ab.setView(v);
                        ab.create();
                        ab.show();

                    }
                });

            }
        });




    }
    public void AddData()
    {
        String name=et1.getText().toString();
        String number=et2.getText().toString();
        String email=et3.getText().toString();
        String pass=et4.getText().toString();
        SaveData saveData=new SaveData(name,number,email,pass);
        databaseReference.push().setValue(saveData);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            Intent intent=new Intent(HomeActivity.this,ProfileActivity.class);
            startActivity(intent);
            finish();
        }


}}

