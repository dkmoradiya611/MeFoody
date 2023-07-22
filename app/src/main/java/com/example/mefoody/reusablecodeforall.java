package com.example.mefoody;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class reusablecodeforall {
    public static void ShowAlert(Context context,String titile,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setTitle(titile).setMessage(message).show();
    }
}
