package com.example.mefoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class Delivery_Loginphone extends AppCompatActivity {

    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_loginphone);

        num=findViewById(R.id.txtnodelloginphone);
        sendotp=findViewById(R.id.btnsendotpdelloginphon);
        cpp=findViewById(R.id.ccddelloginphone);
        signinemail=findViewById(R.id.btnemaildelloginphone);
        signup=findViewById(R.id.acsignup);

        Fauth=FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number=num.getText().toString().trim();
                String phonenum=cpp.getDefaultCountryCodeWithPlus()+number;
                Intent b=new Intent(Delivery_Loginphone.this,Delivery_sendotp.class);

                b.putExtra("Phonenum",phonenum);
                startActivity(b);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Delivery_Loginphone.this, Delivery_Register.class));
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Delivery_Loginphone.this, Delivery_Loginemail.class));
            }
        });
    }
}