package com.example.mefoody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Delivery_phonverify extends AppCompatActivity {

    Button verify,resend;
    TextView txt;
    EditText entercode;
    String verificationid;
    String phoneno;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_phonverify);

        phoneno=getIntent().getStringExtra("phonenumber").trim();

        entercode=findViewById(R.id.txtotpdelphverify);
        txt=findViewById(R.id.textt);
        resend=findViewById(R.id.btnresenddelverify);
        verify=findViewById(R.id.btnverifydel);
        auth=FirebaseAuth.getInstance();

        resend.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.INVISIBLE);

        sendverificationcode(phoneno);

        verify.setOnClickListener(view -> {
            String code=entercode.getText().toString().trim();
            resend.setVisibility(View.INVISIBLE);

            if (code.isEmpty() && code.length()<6){
                entercode.setError("Enter Code");
                entercode.requestFocus();
                return;
            }
            verifycode(code);
        });
        new CountDownTimer(60000,1000){

            @Override
            public void onTick(long l) {
                txt.setVisibility(View.VISIBLE);
                txt.setText("Resend Code Within"+l/1000+"Seconds");
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

                new CountDownTimer(60000,1000){

                    @Override
                    public void onTick(long l) {
                        txt.setVisibility(View.VISIBLE);
                        txt.setText("Resend Code Within "+l/1000+" Seconds");

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

    private void sendverificationcode(String number)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                (Activity) TaskExecutors.MAIN_THREAD,
                mcallback
        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        @Override
        public void onCodeSent(String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken){
            super.onCodeSent(s,forceResendingToken);
            verificationid=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
            if (code!=null)
            {
                entercode.setText(code);
                verifycode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(Delivery_phonverify.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void verifycode(String code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationid,code);
        linkcredential(credential);
    }

    private void linkcredential(PhoneAuthCredential credential) {
        auth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener(Delivery_phonverify.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent=new Intent(Delivery_phonverify.this, MainMenu.class);
                            startActivity(intent);
                        }else {
                            reusablecodeforall.ShowAlert(Delivery_phonverify.this,"Error",task.getException().getMessage());
                        }
                    }
                });
    }
}