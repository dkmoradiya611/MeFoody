 package com.example.mefoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

 public class Chooseone extends AppCompatActivity {
    Button chef,customer,deliveryperson;
    Intent intent;
    String type;
    ConstraintLayout bgimage;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseone);

        AnimationDrawable animationDrawable=new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bghome2),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic2),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic3),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic4),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic5),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic6),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic7),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic8),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bggg),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic9),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic10),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic11),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.pic11),3000);

        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        bgimage=findViewById(R.id.back3);
        bgimage.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        intent=getIntent();
        type=intent.getStringExtra("Home").toString().trim();

        chef=findViewById(R.id.chef);
        deliveryperson=findViewById(R.id.delivery);
        customer=findViewById(R.id.customer);

        chef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("Email")){
                    Intent loginemail=new Intent(Chooseone.this,Chefloginemail.class);
                    startActivity(loginemail);
                    finish();
                }
                if (type.equals("Phone")){
                    Intent loginphone=new Intent(Chooseone.this,Chefloginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                if (type.equals("Signup")){
                    Intent register=new Intent(Chooseone.this,Chefregister.class);
                    startActivity(register);
                }
            }
        });
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("Email")){
                    Intent loginemaildel=new Intent(Chooseone.this,Cust_Loginemail.class);
                    startActivity(loginemaildel);
                    finish();
                }
                if (type.equals("Phone")){
                    Intent loginphonedel=new Intent(Chooseone.this,Cust_Loginphone.class);
                    startActivity(loginphonedel);
                    finish();
                }
                if (type.equals("Signup")){
                    Intent registerdel=new Intent(Chooseone.this,Cust_Register.class);
                    startActivity(registerdel);
                }
            }
        });
        deliveryperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("Email")){
                    Intent loginemail=new Intent(Chooseone.this,Delivery_Loginemail.class);
                    startActivity(loginemail);
                    finish();
                }
                if (type.equals("Phone")){
                    Intent loginphone=new Intent(Chooseone.this,Delivery_Loginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                if (type.equals("Signup")){
                    Intent register=new Intent(Chooseone.this,Delivery_Register.class);
                    startActivity(register);
                }
            }
        });
    }
}