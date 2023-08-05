package com.example.mefoody;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.white));

       setContentView(R.layout.activity_splash2);
       if (getSupportActionBar() != null) {
           getSupportActionBar().hide();
       }

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity2.this, MainMenu.class));
                finish();
            }
        },2000);

    }
}