package com.careernaksha.careernaksha;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseUser user;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
            }
        },3000);

    }

   /* @Override
    protected void onStart() {
        super.onStart();
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            Intent i=new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(i);
            finish();
        }
    }*/
}
