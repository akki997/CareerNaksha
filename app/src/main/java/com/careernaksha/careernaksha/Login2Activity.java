package com.careernaksha.careernaksha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Login2Activity extends AppCompatActivity {
    Button bl1,bl2;
    EditText e1,e2;
    TextView tv;
    String Uid;
    ImageView fb,twitter,linked,google;
    DatabaseReference db;
    FirebaseUser user;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    CallbackManager mCallbackManager;
    LoginButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText) findViewById(R.id.e2);
        bl1=(Button)findViewById(R.id.bl1);
        bl2=(Button)findViewById(R.id.bl2);
        fb=(ImageView)findViewById(R.id.fb);
        twitter=(ImageView)findViewById(R.id.twitter);
        linked=(ImageView)findViewById(R.id.linked);
        google=(ImageView)findViewById(R.id.google);

        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
       // printkey();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
                progressDialog=new ProgressDialog(Login2Activity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Loging in..");
                progressDialog.show();
            }
        });
        FacebookSdk.sdkInitialize(Login2Activity.this);


        mCallbackManager=CallbackManager.Factory.create();
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog=new ProgressDialog(Login2Activity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Loging in..");
                progressDialog.show();
                LoginManager.getInstance().logInWithReadPermissions(Login2Activity.this,Arrays.asList("email"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
            }
        });



        db=FirebaseDatabase.getInstance().getReference();
        bl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login2Activity.this,SignupActivity.class);
                startActivity(i);
            }
        });
        bl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog=new ProgressDialog(Login2Activity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("Loging in..");
                progressDialog.show();

                if(e1.getText().toString().equals("")||e2.getText().toString().equals(""))
                {
                    Toast.makeText(Login2Activity.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.signInWithEmailAndPassword(e1.getText().toString(),e2.getText().toString())
                            .addOnCompleteListener(Login2Activity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                         FirebaseUser currentUser = mAuth.getCurrentUser();
                                          progressDialog.dismiss();
                                           /*  try{
                                                 Uid=currentUser.getUid();
                                             }
                                             catch (Exception e) {
                                                 db.addValueEventListener(new ValueEventListener() {
                                                     @Override
                                                     public void onDataChange(DataSnapshot dataSnapshot) {
                                                         String name = dataSnapshot.child(Uid).child("name").getValue(String.class);
                                                         Toast.makeText(Login2Activity.this, name, Toast.LENGTH_SHORT).show();
                                                     }

                                                     @Override
                                                     public void onCancelled(DatabaseError databaseError) {

                                                     }
                                                 });
                                             }*/
                                                 Intent i = new Intent(Login2Activity.this, ProfileActivity.class);
                                                 startActivity(i);
                                                 finish();

                                              } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Login2Activity.this, "Enter details carefully", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
            }
        });


    }

    private void printkey() {
        try
        {
            PackageInfo info=getPackageManager().getPackageInfo("com.careernaksha.careernaksha", PackageManager.GET_SIGNATURES);

            for(Signature signature:info.signatures)
            {
                MessageDigest messageDigest=MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.e("KEYHASH", Base64.encodeToString(messageDigest.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Login2Activity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            progressDialog.dismiss();

                            Intent i = new Intent(Login2Activity.this, ProfileActivity.class);
                            startActivity(i);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login2Activity.this, "SignIn Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login2Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

                // ...
            }
        }

    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {



        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            progressDialog.dismiss();
                            Intent i = new Intent(Login2Activity.this, ProfileActivity.class);
                            startActivity(i);

                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(Login2Activity.this, "SignIn Failed..!!", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            String name=currentUser.getEmail();
            Toast.makeText(Login2Activity.this, name, Toast.LENGTH_SHORT).show();

        }
    }
}
