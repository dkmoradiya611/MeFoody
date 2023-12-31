package com.example.mefoody;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class Cust_Loginphone extends AppCompatActivity {
    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_loginphone);
        num=findViewById(R.id.txtnocustloginphone);
        sendotp=findViewById(R.id.btnsendotpcustphone);
        cpp=findViewById(R.id.ccdcustloginphone);
        signinemail=findViewById(R.id.btnemailcustloginphone);
        signup=findViewById(R.id.crtcustloginphone);

        Fauth=FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number=num.getText().toString().trim();
                String Phonenumber=cpp.getDefaultCountryCodeWithPlus()+number;
                Intent c=new Intent(Cust_Loginphone.this,Cust_sendotp.class);
                c.putExtra("phonenumber",Phonenumber);
                startActivity(c);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cust_Loginphone.this, Cust_Register.class));
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cust_Loginphone.this, Cust_Loginemail.class));
            }
        });

    }
}