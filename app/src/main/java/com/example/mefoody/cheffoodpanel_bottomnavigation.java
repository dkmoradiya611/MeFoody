package com.example.mefoody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;


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