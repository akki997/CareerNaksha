package com.careernaksha.careernaksha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    Button bt1;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);
        bt1=(Button) findViewById(R.id.bt1);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("user");
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog=new ProgressDialog(SignupActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Signing up..");
                progressDialog.show();
                if(et1.getText().toString().equals("")||et2.getText().toString().equals("")||et3.getText().toString().equals("")||et4.getText().toString().equals(""))
                {
                    Toast.makeText(SignupActivity.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.createUserWithEmailAndPassword(et3.getText().toString(), et4.getText().toString())
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Add();
                                        Update();
                                        progressDialog.dismiss();

                                             Intent i = new Intent(SignupActivity.this, Login2Activity.class);
                                             startActivity(i);
                                             finish();



                                    } else {
                                        // If sign in fails, display a message to the user.
                                        progressDialog.dismiss();
                                        Toast.makeText(SignupActivity.this, "Enter details carefully!!", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
            }
        });

    }
    public void Add()
    {
        String name=et1.getText().toString();
        String number=et2.getText().toString();
        String email=et3.getText().toString();
        String pass=et4.getText().toString();
        SaveData saveData=new SaveData(name,number,email,pass);
        databaseReference.push().setValue(saveData);
    }
    private void Update()
    {
        final FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null)
        {
           UserProfileChangeRequest profileUpdates=new UserProfileChangeRequest.Builder().setDisplayName(et1.getText().toString().trim()).build();

        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignupActivity.this, user.getDisplayName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        }


    }
   /* @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       if(currentUser!=null)
       {
           Intent i=new Intent(SignupActivity.this,ProfileActivity.class);
           startActivity(i);
           finish();

       }

    }*/

}
