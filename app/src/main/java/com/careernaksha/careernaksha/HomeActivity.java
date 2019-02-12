package com.careernaksha.careernaksha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    String Uid;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference databaseReference;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();

        databaseReference= FirebaseDatabase.getInstance().getReference();
        b=(Button)findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,Login2Activity.class);
                startActivity(i);
                finish();
            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
         FirebaseUser currentUser = mAuth.getCurrentUser();
       // Uid=currentUser.getUid();

     if(currentUser!=null)
     {

        String name=currentUser.getEmail();
        Toast.makeText(HomeActivity.this, name, Toast.LENGTH_SHORT).show();
         Intent i = new Intent(HomeActivity.this, ProfileActivity.class);
         startActivity(i);
         finish();
     }
    }

}

