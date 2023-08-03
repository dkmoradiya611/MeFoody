package com.example.mefoody;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mefoody.cheffoodpanel.chefhomefragment;
import com.example.mefoody.cheffoodpanel.cheforderfragment;
import com.example.mefoody.cheffoodpanel.chefpendingorderfragment;
import com.example.mefoody.cheffoodpanel.chefprofilefragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class cheffoodpanel_bottomnavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheffoodpanel_bottomnavigation);
        BottomNavigationView navigationView=findViewById(R.id.chef_bottom_navigation1);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name=getIntent().getStringExtra("PAGE");
        if (name!=null){
            if(name.equalsIgnoreCase("Orderpage")){
                loadcheffragment(new chefpendingorderfragment());
            } else if (name.equalsIgnoreCase("Confirmpage")) {
                loadcheffragment(new cheforderfragment());
            } else if (name.equalsIgnoreCase("AcceptOrderpage")) {
                loadcheffragment(new cheforderfragment());
            }else if (name.equalsIgnoreCase("Deliveredpage")){
                loadcheffragment(new cheforderfragment());
            }
        }else {
            loadcheffragment(new chefhomefragment());
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

       // switch (item.getItemId()){
       //     case a:
       //         fragment=new chefhomefragment();
       //         break;
//
       //     case R.id.pendingorder:
       //         fragment=new chefpendingorderfragment();
       //         break;
//
       //     case R.id.orders:
       //         fragment=new cheforderfragment();
       //         break;
//
       //     case R.id.chefprofile:
       //         fragment=new chefprofilefragment();
       //         break;
//
       // }
        if(item.getItemId()==R.id.chefhome)
        {
            fragment=new chefhomefragment();
        }
        else if (item.getItemId()==R.id.pendingorder)
        {
            fragment=new chefpendingorderfragment();
        }
        else if (item.getItemId()==R.id.orders)
        {
            fragment=new cheforderfragment();
        }
        else if (item.getItemId()==R.id.chefprofile)
        {
            fragment=new chefprofilefragment();
        }
        return loadcheffragment(fragment);
    }

    private boolean loadcheffragment(Fragment fragment) {

        if(fragment!=null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
        return false;

    }
}