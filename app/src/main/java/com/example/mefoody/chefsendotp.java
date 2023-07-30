package com.example.mefoody;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class chefsendotp extends AppCompatActivity {

    Button verify,resend;
    TextView txt;
    EditText entercode;
    String verificationid;
    String phoneno;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chefsendotp);

        phoneno = getIntent().getStringExtra("phonenumber").trim();

        entercode = findViewById(R.id.txtotpsendchef);
        txt = findViewById(R.id.text1);
        resend = findViewById(R.id.btnresendsendotpchef);
        verify = findViewById(R.id.btnverifysendotpchef);
        auth = FirebaseAuth.getInstance();

        resend.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.INVISIBLE);

        sendverificationcode(phoneno);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = entercode.getText().toString().trim();
                resend.setVisibility(View.INVISIBLE);

                if (code.isEmpty() && code.length() < 6) {
                    entercode.setError("Enter Code");
                    entercode.requestFocus();
                    return;
                }
                verifycode(code);
            }
        });
        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l) {
                txt.setVisibility(View.VISIBLE);
                txt.setText("Resend Code Within " + l / 1000 + " Seconds");

            }

            @Override
            public void onFinish() {
                resend.setVisibility(View.VISIBLE);
                txt.setVisibility(View.INVISIBLE);
            }
        }.start();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resend.setVisibility(View.INVISIBLE);
                resendotp(phoneno);

                new CountDownTimer(60000, 1000) {

                    @Override
                    public void onTick(long l) {
                        txt.setVisibility(View.VISIBLE);
                        txt.setText("Resend Code Within " + l / 1000 + " Seconds");

                    }

                    @Override
                    public void onFinish() {
                        resend.setVisibility(View.VISIBLE);
                        txt.setVisibility(View.INVISIBLE);
                    }
                }.start();
            }
        });
    }

    private void resendotp(String phonenum) {
        sendverificationcode(phonenum);
    }

    private void sendverificationcode(String number) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(number) // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mcallback)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                number,
//                60L,
//                TimeUnit.SECONDS,
//                (Activity) TaskExecutors.MAIN_THREAD,
//                mcallback
//        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationid = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                entercode.setText(code);
                verifycode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(chefsendotp.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void verifycode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationid, code);
        signinwithphone(credential);
    }

    private void signinwithphone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(chefsendotp.this, cheffoodpanel_bottomnavigation.class));
                }else{
                    reusablecodeforall.ShowAlert(chefsendotp.this,"Error",task.getException().getMessage());
                }
            }
        });
    }
}