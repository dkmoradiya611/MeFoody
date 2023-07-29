package com.example.mefoody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mefoody.customerFoodPanel.CustomerCartFragment;
import com.example.mefoody.customerFoodPanel.CustomerHomeFragment;
import com.example.mefoody.customerFoodPanel.CustomerOrdersFragment;
import com.example.mefoody.customerFoodPanel.CustomerProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Custfoodpanel_bottomnavigation extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custfoodpanel_bottomnavigation);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        if(item.getItemId()==R.id.cust_Home)
        {
            fragment = new CustomerHomeFragment();
        }
        if(item.getItemId()==R.id.cart)
        {
            fragment = new CustomerCartFragment();
        }
        if(item.getItemId()==R.id.cust_profile)
        {
            fragment = new CustomerProfileFragment();
        }
        if(item.getItemId()==R.id.cust_Order)
        {
            fragment = new CustomerOrdersFragment();
        }
        if(item.getItemId()==R.id.cust_Home)
        {
            fragment = new CustomerHomeFragment();
        }

        /*switch (item.getItemId()) {
            case R.id.cust_Home:
                fragment = new CustomerHomeFragment();
                break;
        }
        switch (item.getItemId()) {
            case R.id.cart:
                fragment = new CustomerCartFragment();
                break;
        }
        switch (item.getItemId()) {
            case R.id.cust_profile:
                fragment = new CustomerProfileFragment();
                break;
        }
        switch (item.getItemId()) {
            case R.id.cust_Order:
                fragment = new CustomerOrdersFragment();
                break;
        }
        switch (item.getItemId()) {
            case R.id.cust_Home:
                fragment = new CustomerHomeFragment();
                break;
        }*/
        return loadcheffragment(fragment);
    }

    private boolean loadcheffragment(Fragment fragment) {

        if(fragment != null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }
}