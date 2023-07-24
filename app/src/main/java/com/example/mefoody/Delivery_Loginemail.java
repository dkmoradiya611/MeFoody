package com.example.mefoody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Delivery_Loginemail extends AppCompatActivity {

    TextInputLayout email, pass;
    Button signin, signinphone;
    TextView forgotpassword, signup;
    FirebaseAuth Fauth;
    String emailid, pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_loginemail);

        try {
            email=findViewById(R.id.txtemaildelloginemail);
            pass=findViewById(R.id.txtpassdelloginemail);
            signin=findViewById(R.id.btnlogindelloginemail);
            signup=findViewById(R.id.crtdellogin);
            forgotpassword=findViewById(R.id.forgotpassdelloginemail);
            signinphone=findViewById(R.id.btnphonedelloginemail);

            Fauth =FirebaseAuth.getInstance();

            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    emailid=email.getEditText().getText().toString().trim();
                    pwd=pass.getEditText().getText().toString().trim();

                    if (isValid()){
                        final ProgressDialog mdialog=new ProgressDialog(Delivery_Loginemail.this);
                        mdialog.setCanceledOnTouchOutside(false);
                        mdialog.setCancelable(false);
                        mdialog.setMessage("Sign In Please wait...............");
                        mdialog.show();

                        Fauth.signInWithEmailAndPassword(emailid,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    mdialog.dismiss();

                                    if (Fauth.getCurrentUser().isEmailVerified()){
                                        mdialog.dismiss();
                                        Toast.makeText(Delivery_Loginemail.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();
                                        Intent z =new Intent(Delivery_Loginemail.this,DeliveryFoodpanel_BottomNavigation.class);
                                        startActivity(z);
                                    }
                                    else {
                                        reusablecodeforall.ShowAlert(Delivery_Loginemail.this,"Verification failed","You Have Not Verified Your Email");
                                    }
                                }else {
                                    mdialog.dismiss();
                                    reusablecodeforall.ShowAlert(Delivery_Loginemail.this,"Error",task.getException().getMessage());
                                }
                            }
                        });
                    }
                }
            });
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Delivery_Loginemail.this, Delivery_Register.class));
                }
            });
            forgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Delivery_Loginemail.this,DeliveryForgotPassword.class));
                }
            });
            signinphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Delivery_Loginemail.this, Delivery_Loginphone.class));
                }
            });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage()  , Toast.LENGTH_SHORT).show();
        }

    }
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid() {
        email.setErrorEnabled(false);
        email.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");

        boolean isvalid = false, isvalidemail = false, isvalidpassword = false;
        if (TextUtils.isEmpty(emailid)) {
            email.setErrorEnabled(true);
            email.setError("Email is required");
        } else {
            if (emailid.matches(emailpattern)){
                isvalidemail=true;
            }else {
                email.setErrorEnabled(true);
                email.setError("Invalid Email Address");
            }
        }
        if (TextUtils.isEmpty(pwd)){
            pass.setErrorEnabled(true);
            pass.setError("Password is Required");
        }else {
            isvalidpassword=true;
        }
        isvalid=(isvalidemail && isvalidpassword)?true:false;
        return isvalid;
    }
}