package com.example.shopkar0.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopkar0.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    Button signInBtn;
    EditText email,password;
    TextView signUpTv;
   ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        progressBar=findViewById(R.id.progressBarSignIn);
        progressBar.setVisibility(View.GONE);
        auth=FirebaseAuth.getInstance();
        signUpTv=findViewById(R.id.StringSignUp);
        email=findViewById(R.id.editTextEmailSignIn);
        password=findViewById(R.id.editTextPasswordSignIn);
        signInBtn=findViewById(R.id.SignInBtn);


        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignInActivity.this, "please wait ....", Toast.LENGTH_SHORT).show();
                SignInUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });



    }

    private void SignInUser() {

        String UserEmail = email.getText().toString();
        String UserPass = password.getText().toString();



        if (TextUtils.isEmpty(UserEmail)) {
            email.setError("Email can't be empty");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(UserEmail).matches()) {
            email.setError("Please enter a valid email address");
            return;
        } else {
            email.setError(null);

        }

        if (TextUtils.isEmpty(UserPass)) {
            password.setError("Password can't be empty");
            return;
        }
        if (!PASSWORD_PATTERN.matcher(UserPass).matches()) {
            password.setError("Too weak password must contain atleast one special char one number and one upper and lowercase letters  ");
            return;
        } else {
            password.setError(null);
        }

        auth.signInWithEmailAndPassword(UserEmail,UserPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignInActivity.this, "Login  SuccessFully", Toast.LENGTH_SHORT).show();
                    Toast.makeText(SignInActivity.this, "Logging In ", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);



                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignInActivity.this, "Error: "+task.getException(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}