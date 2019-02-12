package com.careernaksha.careernaksha;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Locale;

public class ProfileActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseReference databaseReference;
    TextView tv,t,tvi;
    Button b1,b2;
    ImageView ivprofilebuilder,ivguidance,ivjob,ivcounselling,ivalumini;
    private TextToSpeech tts;
    private FirebaseAuth auth;
    Uri pdf;
    String name;
    FirebaseStorage storage;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
   // ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // constraintLayout=findViewById(R.id.content);
       // tv=(TextView)findViewById(R.id.textView);
           tvi=findViewById(R.id.tvi);
           b1=findViewById(R.id.b1);
           b2=findViewById(R.id.b2);
       auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();

        intializeTextToSpeech();
        setSupportActionBar(toolbar);
    databaseReference= FirebaseDatabase.getInstance().getReference();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View v=navigationView.getHeaderView(0);
        tv=(TextView)v.findViewById(R.id.textView);
        t=(TextView)findViewById(R.id.t);

        if(user!=null)
        {   name=user.getDisplayName();
            tv.setText(name);
           t.setText("Welcome "+name);

            Toast.makeText(ProfileActivity.this, "Welcome "+ user.getDisplayName(), Toast.LENGTH_SHORT).show();
        }
        ivprofilebuilder=findViewById(R.id.ivprofilebuilder);
        ivguidance=findViewById(R.id.ivguidance);
        ivjob=findViewById(R.id.ivjob);
        ivcounselling=findViewById(R.id.ivcounselling);
        ivalumini=findViewById(R.id.ivalumini);
        ivprofilebuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,new CareerPatriFragment()).addToBackStack(null).commit();
            }
        });

    }

    private void intializeTextToSpeech() {
        tts=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(tts.getEngines().size()==0)
                {
                    Toast.makeText(ProfileActivity.this, "Not Available", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{

                    tts.setLanguage(Locale.US);
                    speak("Hi"+name+"Welcome to CareerNaksha");
                }
            }
        });
    }
    private void speak(String s) {
        if(Build.VERSION.SDK_INT>=21)
        {
            tts.speak(s,TextToSpeech.QUEUE_FLUSH,null,null);

        }
        else{
            tts.speak(s,TextToSpeech.QUEUE_FLUSH,null,null);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           /* super.onBackPressed();
           finish();*/
        }
        if(getFragmentManager().getBackStackEntryCount()==0)
        {
            super.onBackPressed();
        }
        else
        {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
          //  ProfileFragment profileFragment=new ProfileFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,new ProfileFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_upload) {
          /*  if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                select();

            } else {
                ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
            }
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (pdf != null) {
                        Upload(pdf);
                    } else {
                        Toast.makeText(ProfileActivity.this, "Select a file", Toast.LENGTH_SHORT).show();

                    }
                }
            },8000);*/
          /*  AlertDialog.Builder ab=new AlertDialog.Builder(ProfileActivity.this);
            View v= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item,null);

            b1=v.findViewById(R.id.b1);
            b2=v.findViewById(R.id.b2);
            tvi=v.findViewById(R.id.tvi);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        select();

                    }
                    else {
                        ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                    }

                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (pdf!= null) {
                        Upload(pdf);
                    }
                    else if(pdf==null){
                        Toast.makeText(ProfileActivity.this, "Select a file", Toast.LENGTH_SHORT).show();

                    }
                }
            });


            ab.setView(v);
            ab.create();
            ab.show();*/
          getSupportFragmentManager().beginTransaction().replace(R.id.frame,new UploadFragment()).addToBackStack(null).commit();

        }else if (id == R.id.nav_councelor) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,new CounsellorFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_search) {

         getSupportFragmentManager().beginTransaction().replace(R.id.frame,new SearchFragment()).addToBackStack(null).commit();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {

                  FirebaseAuth.getInstance().signOut();
                  finish();
                  startActivity(new Intent(ProfileActivity.this,Login2Activity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

  /*  private void Upload(Uri pdf) {
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File...");
        progressDialog.setProgress(0);
        progressDialog.show();
        final String filename=System.currentTimeMillis()+"";
        StorageReference storageReference=FirebaseStorage.getInstance().getReference();
        storageReference.child("Uploads").child(filename).putFile(pdf).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url=taskSnapshot.getDownloadUrl().toString();
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                databaseReference=FirebaseDatabase.getInstance().getReference();
                databaseReference.child(user.getDisplayName()).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ProfileActivity.this, "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                        else
                        {
                            Toast.makeText(ProfileActivity.this, "Not Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this, "Not Uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int cur=(int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(cur);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9&& grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            select();
        }
        else
        {
            Toast.makeText(ProfileActivity.this, "Please provide permission", Toast.LENGTH_SHORT).show();
        }
    }

    private void select()
    {
        Intent i=new Intent();
        i.setType("application/pdf");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null)
        {
            pdf=data.getData();
            tvi.setText("A file is selected :"+ data.getData().getLastPathSegment());
        }
        else
        {
            Toast.makeText(ProfileActivity.this, "Please select file", Toast.LENGTH_SHORT).show();
        }
    }
*/
}
