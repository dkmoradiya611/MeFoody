package com.example.mefoody;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class Chefloginemail extends AppCompatActivity {
    TextInputLayout email,pass;
    Button Signin,Signinphone;
    TextView Forgotpassword,signup;
    FirebaseAuth Fauth;
    String emailid,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chefloginemail);
    }
    String emailpattern;
}