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

public class  cust_phoneverify extends AppCompatActivity {
    Button verify,resend;
    TextView txt;
    EditText entercode;
    String verificationid;
    String phoneno;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_phoneverify);

        phoneno=getIntent().getStringExtra("phonenumber").trim();
        entercode=(EditText)findViewById(R.id.txtotpcustphverify);

        txt=findViewById(R.id.text);
        resend=findViewById(R.id.btnresendcustphverify);
        verify=findViewById(R.id.btnverifycustphverify);
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
//                60,
//                TimeUnit.SECONDS,
//                (Activity) TaskExecutors.MAIN_THREAD,
//                mcallback
//        );
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
            Toast.makeText(cust_phoneverify.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void verifycode(String code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationid,code);
        linkcredential(credential);
    }

    private void linkcredential(PhoneAuthCredential credential) {
        auth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener(cust_phoneverify.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent=new Intent(cust_phoneverify.this, MainMenu.class);
                            startActivity(intent);
                        }else {
                            reusablecodeforall.ShowAlert(cust_phoneverify.this,"Error",task.getException().getMessage());
                        }
                    }
                });
    }
}