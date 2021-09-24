package com.example.GroceryCity.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.GroceryCity.R;
import com.google.firebase.auth.FirebaseAuth;


public class WelcomePageActivity extends AppCompatActivity {

    Button button;
    LinearLayout layoutInner1,layoutInner2;
    ProgressBar progressBar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        auth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBarWelcomePage);
        progressBar.setVisibility(View.GONE);
        layoutInner1=findViewById(R.id.linearlayoutWelcomePageinner1);
        layoutInner2=findViewById(R.id.linearlayoutWelcomePageinner2);

       /* if(auth.getCurrentUser()!=null){
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(this, "logging In ", Toast.LENGTH_SHORT).show();
            finish();
        }*/

        layoutInner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent= new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });

        layoutInner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent= new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}