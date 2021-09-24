package com.example.GroceryCity.activites;

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

import com.example.GroceryCity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

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

    TextView signInTv;
    Button  signUpBtn;
    EditText name,email,password;
    ProgressBar progressBar;
   private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBarSignUp);
        progressBar.setVisibility(View.GONE);
        signUpBtn=findViewById(R.id.SignUpBtn);
        signInTv=findViewById(R.id.StringSignIn);
        name=findViewById(R.id.editTextNameSignUp);
        email=findViewById(R.id.editTextEmailSignUp);
        password=findViewById(R.id.editTextPasswordSignUp);


        signInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createUser();
                progressBar.setVisibility(View.VISIBLE);


            }
        });
    }

    // field Validation


    private void createUser() {
        String UserName = name.getText().toString();
        String UserEmail = email.getText().toString();
        String UserPass = password.getText().toString();

        if (TextUtils.isEmpty(UserName)) {
            name.setError("Username can't be empty");
            return;
        } else {
            name.setError(null);
        }

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
            password.setError("Password too weak");
            return;
        } else {
            password.setError(null);
        }




        auth.createUserWithEmailAndPassword(UserEmail,UserPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignUpActivity.this, "Sign Up SuccessFully", Toast.LENGTH_SHORT).show();
                         clear();
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignUpActivity.this, "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                                  clear();
                }

            }
        });
    }


    private void clear(){
        name.setText(null);
        email.setText(null);
        password.setText(null);
    }
}