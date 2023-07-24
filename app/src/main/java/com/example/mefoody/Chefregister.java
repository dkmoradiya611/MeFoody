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

import com.google.android.datatransport.runtime.dagger.Reusable;
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

public class Chefregister extends AppCompatActivity {
    String[] Maharashtra = {"Mumbai", "Pune", "Nashik"};
    String[] Gujarat = {"Surat ", "Ahemdabad", "Vadodara"};

    TextInputLayout Fname, Lname, Email, Pass, cpass, mobileno, houseno, area, pincode;
    Spinner Statespin, Cityspin;
    Button signup, Emaill, Phone;
    CountryCodePicker Cpp;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fname, lname, emailid, password, confpassword, mobile, house, Area, Pincode, role = "Chef", statee, cityy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chefregister);

        Fname = (TextInputLayout) findViewById(R.id.txtfnamechefreg);
        Lname = (TextInputLayout) findViewById(R.id.txtlnamechefreg);
        Email = (TextInputLayout) findViewById(R.id.txtemailchefreg);
        Pass = (TextInputLayout) findViewById(R.id.txtpasschefreg);
        cpass = (TextInputLayout) findViewById(R.id.txtcpasschefreg);
        mobileno = (TextInputLayout) findViewById(R.id.txtmnochefreg);
        houseno = (TextInputLayout) findViewById(R.id.txthnochefreg);
        pincode = (TextInputLayout) findViewById(R.id.txtpcodechefreg);
        Statespin = (Spinner) findViewById(R.id.ddlstatechefreg);
        area = (TextInputLayout) findViewById(R.id.txtareachefreg);
        Cityspin = (Spinner) findViewById(R.id.ddlcitychefreg);


        signup = (Button) findViewById(R.id.btnchefreg);
        Emaill = (Button) findViewById(R.id.btnemailchefreg);
        Phone = (Button) findViewById(R.id.btnphonechefreg);

        Cpp = (CountryCodePicker) findViewById(R.id.ccdchefreg);

        Statespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Object value = parent.getItemAtPosition(position);
                statee = value.toString().trim();
                if (statee.equals("Maharashtra")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : Maharashtra) {
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Chefregister.this, android.R.layout.simple_spinner_item, list);
                    Cityspin.setAdapter(arrayAdapter);
                }
                if (statee.equals("Gujarat")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String cities : Gujarat) {
                        list.add(cities);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Chefregister.this, android.R.layout.simple_spinner_item, list);
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

        databaseReference = firebaseDatabase.getInstance().getReference("Chef");
        FAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(view -> {

            fname = Fname.getEditText().getText().toString().trim();
            lname = Lname.getEditText().getText().toString().trim();
            emailid = Email.getEditText().getText().toString().trim();
            mobile = mobileno.getEditText().getText().toString().trim();
            password = Pass.getEditText().getText().toString().trim();
            confpassword = cpass.getEditText().getText().toString().trim();
            Area = area.getEditText().getText().toString().trim();
            house = houseno.getEditText().getText().toString().trim();
            Pincode = pincode.getEditText().getText().toString().trim();


            if (isValid()) {
                final ProgressDialog mDialog = new ProgressDialog(Chefregister.this);
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
                                    hashMap1.put("House", house);

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
                                                                AlertDialog.Builder builder = new AlertDialog.Builder(Chefregister.this);
                                                                builder.setMessage("You have registered! Make sure to verify your email");
                                                                builder.setCancelable(false);
                                                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        dialog.dismiss();
//                                                                        String phonenumber = Cpp.getSelectedCountryCodeWithPlus() + mobile;
//                                                                        Intent b = new Intent(Chefregister.this, chef_phonverify.class);
//                                                                        b.putExtra("phonenumber", phonenumber);
//                                                                        startActivity(b);
                                                                    }
                                                                });

                                                                AlertDialog alert = builder.create();
                                                                alert.show();
                                                            } else {
                                                                mDialog.dismiss();
                                                                reusablecodeforall.ShowAlert(Chefregister.this, "Error", task.getException().getMessage());
                                                            }
                                                        }
                                                    });

                                                }
                                            });

                                }
                            });
                        }
                    }
                });
            }
        });
    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid() {
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
        houseno.setErrorEnabled(false);
        houseno.setError("");
        pincode.setErrorEnabled(false);
        pincode.setError("");

        boolean isValidname = false, isValidemail = false, isValidpassword = false, isValidconfpassword = false, isValid = false, isValidmobilenum = false, isValidlname = false, isValidarea = false, isValidpincode = false, isValidhouseno = false;
        //boolean isValid=false, isValidhouseno=false,isValidlname=false,isValidname=false,isValidemail=false,isValidpassword=false,isValidconfpassword=false,isValidmobilenum=false,isValidarea=false,isValidpincode=false;
        if (TextUtils.isEmpty(fname)) {
            Fname.setErrorEnabled(true);
            Fname.setError("Enter First Name");
        } else {
            isValidname = true;
        }
        if (TextUtils.isEmpty(lname)) {
            Lname.setErrorEnabled(true);
            Lname.setError("Enter Last Name");
        } else {
            isValidlname = true;
        }
        if (TextUtils.isEmpty(emailid)) {
            Email.setErrorEnabled(true);
            Email.setError("Email is Required");
        } else {
            if (emailid.matches(emailpattern)) {
                isValidemail = true;
            } else {
                Email.setErrorEnabled(true);
                Email.setError("Enter a valid Email id");
            }
        }

        if (TextUtils.isEmpty(password)) {
            Pass.setErrorEnabled(true);
            Pass.setError("Enter Password");
        } else {
            if (password.length() < 8) {
                Pass.setErrorEnabled(true);
                Pass.setError("Password is weak");
            } else {
                isValidpassword = true;
            }
        }

        if (TextUtils.isEmpty(confpassword)) {
            cpass.setErrorEnabled(true);
            cpass.setError("Enter password again");
        } else {
            if (!password.equals(confpassword)) {
                cpass.setErrorEnabled(true);
                cpass.setError("Password doesn't match");
            } else {
                isValidconfpassword = true;
            }
        }

        if (TextUtils.isEmpty(mobile)) {
            mobileno.setErrorEnabled(true);
            mobileno.setError("Mobile no is Required");
        } else {
            if (mobile.length() < 10) {
                mobileno.setErrorEnabled(true);
                mobileno.setError("Invalid Mobile number");
            } else {
                isValidmobilenum = true;
            }
        }

        if (TextUtils.isEmpty(Area)) {
            area.setErrorEnabled(true);
            area.setError("Area is Required");
        } else {
            isValidarea = true;
        }

        if (TextUtils.isEmpty(Pincode)) {
            pincode.setErrorEnabled(true);
            pincode.setError("Please enter pincode");
        } else {
            isValidpincode = true;
        }

        if (TextUtils.isEmpty(house)) {
            houseno.setErrorEnabled(true);
            houseno.setError("Enter House no");
        } else {
            isValidhouseno = true;
        }

        isValid = (isValidname && isValidpincode && isValidlname && isValidemail && isValidconfpassword && isValidpassword && isValidmobilenum && isValidarea && isValidhouseno) ? true : false;
        //isValid = (isValidarea && isValidconfpassword && isValidpassword && isValidpincode && isValidemail && isValidmobilenum && isValidname && isValidhouseno && isValidlname)? true : false ;
        return isValid;
    }
}