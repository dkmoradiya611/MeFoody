package com.example.mefoody.cheffoodpanel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.mefoody.R;

public class chefprofilefragment extends Fragment {
    Button postDish;
    ConstraintLayout backing;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_chef_profile,null);
        getActivity().setTitle("Post Dish");
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

        backing = v.findViewById(R.id.back1);
        backing.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();
        postDish = v.findViewById(R.id.Post_back);

        postDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),chef_postdish.class));
            }
        });
        return v;
    }
}