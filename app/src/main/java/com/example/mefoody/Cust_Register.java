package com.example.mefoody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;

public class Cust_Register extends AppCompatActivity {
    String[] Maharashtra = {"Mumbai","Pune","Nashik"};
    String[] Gujarat = {"Surat ","Ahemdabad","Vadodara"};
    TextInputLayout Fname,Lname,Email,Pass,cpass,mobileno,localaddress,area,pincode;
    Spinner Statespin,Cityspin;
    Button signup,Emaill,Phone;
    CountryCodePicker Cpp;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fname,lname,emailid,password,confpassword,mobile,Localaddress,Area,Pincode,role="Customer",statee,cityy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_register);
        Fname = (TextInputLayout)findViewById(R.id.txtfnamecustreg);
        Lname = (TextInputLayout)findViewById(R.id.txtlnamecustreg);
        Email = (TextInputLayout)findViewById(R.id.txtemailcustreg);
        Pass = (TextInputLayout)findViewById(R.id.txtpasscustreg);
        cpass = (TextInputLayout)findViewById(R.id.txtcpasscustreg);
        mobileno = (TextInputLayout)findViewById(R.id.txtmnocustreg);
        localaddress = (TextInputLayout)findViewById(R.id.txthnocustreg);
        pincode = (TextInputLayout)findViewById(R.id.txtpcodecustreg);
        Statespin = (Spinner) findViewById(R.id.ddlstatecustreg);
        area = (TextInputLayout)findViewById(R.id.txtareacustreg);
        Cityspin = (Spinner) findViewById(R.id.ddlcitycustreg);

        signup = (Button) findViewById(R.id.btnregcustreg);
        Emaill = (Button) findViewById(R.id.btnemailcustreg);
        Phone = (Button) findViewById(R.id.btnphonecustreg);

        Cpp = (CountryCodePicker) findViewById(R.id.ccdcustreg);
        Statespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Object value = parent.getItemAtPosition(position);
                statee = value.toString().trim();
                if(statee.equals("Maharashtra"))
                {
                    ArrayList<String> list = new ArrayList<>();
                    for(String cities : Maharashtra)
                    {
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Cust_Register.this, android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);
                }
                if(statee.equals("Gujarat"))
                {
                    ArrayList<String> list = new ArrayList<>();
                    for(String cities : Gujarat)
                    {
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Cust_Register.this, android.R.layout.simple_spinner_item,list);
                    Cityspin.setAdapter(arrayAdapter);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Cityspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                cityy = value.toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        databaseReference = firebaseDatabase.getInstance().getReference("Customer");
        FAuth = FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = Fname.getEditText().getText().toString().trim();
                lname = Lname.getEditText().getText().toString().trim();
                emailid = Email.getEditText().getText().toString().trim();
                mobile = mobileno.getEditText().getText().toString().trim();
                password = Pass.getEditText().getText().toString().trim();
                confpassword = cpass.getEditText().getText().toString().trim();
                Area = area.getEditText().getText().toString().trim();
                Localaddress = localaddress.getEditText().getText().toString().trim();
                Pincode = pincode.getEditText().getText().toString().trim();

                if (isValid()) {
                    final ProgressDialog mDialog = new ProgressDialog(Cust_Register.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registration in progress please wait.......");
                    mDialog.show();

                    FAuth.createUserWithEmailAndPassword(emailid, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("User").child(userid);
                                final HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("Role", role);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        HashMap<String, String> hashMap1 = new HashMap<>();
                                        hashMap1.put("Mobile No", mobile);
                                        hashMap1.put("First Name", fname);
                                        hashMap1.put("Last Name", lname);
                                        hashMap1.put("EmailId", emailid);
                                        hashMap1.put("City", cityy);
                                        hashMap1.put("Password", password);
                                        hashMap1.put("Pincode", Pincode);
                                        hashMap1.put("Area", Area);
                                        hashMap1.put("State", statee);
                                        hashMap1.put("Confirm Password", confpassword);
                                        hashMap1.put("Localaddress", Localaddress);

                                        FirebaseDatabase.getInstance().getReference("Chef")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        mDialog.dismiss();

                                                        FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    AlertDialog.Builder builder = new AlertDialog.Builder(Cust_Register.this);
                                                                    builder.setMessage("You have registered! Make sure to verify your email");
                                                                    builder.setCancelable(false);
                                                                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            dialog.dismiss();
//                                                                            String phonenumber=Cpp.getSelectedCountryCodeWithPlus()+mobile;
//                                                                            Intent b=new Intent(Cust_Register.this, cust_phoneverify.class);
//                                                                            b.putExtra("phonenumberr",phonenumber);
//                                                                            startActivity(b);
                                                                        }
                                                                    });

                                                                    AlertDialog alert = builder.create();
                                                                    alert.show();
                                                                } else {
                                                                    mDialog.dismiss();
                                                                    reusablecodeforall.ShowAlert(Cust_Register.this, "Error", task.getException().getMessage());
                                                                }
                                                            }
                                                        });

                                                    }
                                                });

                                    }
                                });
                            } else {
                                mDialog.dismiss();
                                reusablecodeforall.ShowAlert(Cust_Register.this, "Error", task.getException().getMessage());
                            }
                        }
                    });
                }
            }
        });

        Emaill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cust_Register.this, Cust_Loginemail.class));
            }
        });
        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cust_Register.this, Cust_Loginphone.class));
            }
        });
    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public boolean isValid()
    {
        Email.setErrorEnabled(false);
        Email.setError("");
        Fname.setErrorEnabled(false);
        Fname.setError("");
        Lname.setErrorEnabled(false);
        Lname.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");
        mobileno.setErrorEnabled(false);
        mobileno.setError("");
        cpass.setErrorEnabled(false);
        cpass.setError("");
        area.setErrorEnabled(false);
        area.setError("");
        localaddress.setErrorEnabled(false);
        localaddress.setError("");
        pincode.setErrorEnabled(false);
        pincode.setError("");

        boolean isValidname=false,isValidemail=false,isValidpassword=false,isValidconfpassword=false,isValid=false,isValidmobilenum=false,isValidlname=false,isValidarea=false,isValidpincode=false,isValidlocaladd=false;
        //boolean isValid=false, isValidhouseno=false,isValidlname=false,isValidname=false,isValidemail=false,isValidpassword=false,isValidconfpassword=false,isValidmobilenum=false,isValidarea=false,isValidpincode=false;
        if(TextUtils.isEmpty(fname))
        {
            Fname.setErrorEnabled(true);
            Fname.setError("Enter First Name");
        }else
        {
            isValidname = true;
        }
        if(TextUtils.isEmpty(lname))
        {
            Lname.setErrorEnabled(true);
            Lname.setError("Enter Last Name");
        }else
        {
            isValidlname = true;
        }
        if(TextUtils.isEmpty(emailid))
        {
            Email.setErrorEnabled(true);
            Email.setError("Email is Required");
        }else
        {
            if(emailid.matches(emailpattern))
            {
                isValidemail = true;
            }else {
                Email.setErrorEnabled(true);
                Email.setError("Enter a valid Email id");
            }
        }

        if(TextUtils.isEmpty(password))
        {
            Pass.setErrorEnabled(true);
            Pass.setError("Enter Password");
        }else
        {
            if(password.length() < 8)
            {
                Pass.setErrorEnabled(true);
                Pass.setError("Password is weak");
            }else {
                isValidpassword = true;
            }
        }

        if(TextUtils.isEmpty(confpassword))
        {
            cpass.setErrorEnabled(true);
            cpass.setError("Enter password again");
        }else
        {
            if(!password.equals(confpassword))
            {
                cpass.setErrorEnabled(true);
                cpass.setError("Password doesn't match");
            }else {
                isValidconfpassword = true;
            }
        }

        if(TextUtils.isEmpty(mobile))
        {
            mobileno.setErrorEnabled(true);
            mobileno.setError("Mobile no is Required");
        }else
        {
            if(mobile.length() < 10)
            {
                mobileno.setErrorEnabled(true);
                mobileno.setError("Invalid Mobile number");
            }else {
                isValidmobilenum = true;
            }
        }

        if(TextUtils.isEmpty(Area))
        {
            area.setErrorEnabled(true);
            area.setError("Area is Required");
        }else
        {
            isValidarea = true;
        }

        if(TextUtils.isEmpty(Pincode))
        {
            pincode.setErrorEnabled(true);
            pincode.setError("Please enter pincode");
        }else
        {
            isValidpincode = true;
        }

        if(TextUtils.isEmpty(Localaddress))
        {
            localaddress.setErrorEnabled(true);
            localaddress.setError("Enter House no");
        }else
        {
            isValidlocaladd = true;
        }

        isValid = (isValidname && isValidpincode && isValidlname && isValidemail && isValidconfpassword && isValidpassword && isValidmobilenum && isValidarea && isValidlocaladd) ? true : false;
        //isValid = (isValidarea && isValidconfpassword && isValidpassword && isValidpincode && isValidemail && isValidmobilenum && isValidname && isValidhouseno && isValidlname)? true : false ;
        return isValid;
    }

}