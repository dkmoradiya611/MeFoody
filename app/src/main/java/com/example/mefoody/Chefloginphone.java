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

public class Chefloginphone extends AppCompatActivity {

    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chefloginphone);

        num=findViewById(R.id.txtnumbercheflogphone);
        sendotp=findViewById(R.id.btnsendotpclogphone);
        cpp=findViewById(R.id.ccdchefloginphone);
        signinemail=findViewById(R.id.btnemailchefloginphone);
        signup=findViewById(R.id.crtactchefloginphone);

        Fauth=FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number=num.getText().toString().trim();
                String phonenum=cpp.getDefaultCountryCodeWithPlus()+number;
                Intent b=new Intent(Chefloginphone.this,chefsendotp.class);

                b.putExtra("Phonenum",phonenum);
                startActivity(b);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Chefloginphone.this, Chefregister.class));
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Chefloginphone.this, Chefloginemail.class));
            }
        });
    }
}