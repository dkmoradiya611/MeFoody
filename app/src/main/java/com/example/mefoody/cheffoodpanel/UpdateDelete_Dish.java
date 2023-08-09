package com.example.mefoody.cheffoodpanel;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mefoody.R;
import com.example.mefoody.UpdateDishModel;
import com.example.mefoody.cheffoodpanel_bottomnavigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.UUID;

public class UpdateDelete_Dish extends AppCompatActivity {

    TextInputLayout desc,qty,pri;
    TextView Dishname;
    ImageButton imageButton;
    Uri imageuri;
    String dburi;
    public Uri mCropImageuri;
    Button Update_dish,Delete_dish;
    String description,quantity,price,dishes,ChefId;
    String RendomUID;
    StorageReference ref;
    StorageReference storageReference;
    FirebaseStorage storage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth Fauth;

    String ID;
    private ProgressDialog progressDialog;
    DatabaseReference dataa;
    String State,City,Area;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_dish);

        desc =(TextInputLayout) findViewById(R.id.description);
        qty = findViewById(R.id.Quantity);
        pri = findViewById(R.id.price);
        Dishname = findViewById(R.id.dish_name);
        imageButton = findViewById(R.id.image_upload);
        Update_dish = findViewById(R.id.Updatedish);
        Delete_dish = findViewById(R.id.Deletedish);
        ID = getIntent().getStringExtra("updatedeletedish");

        String userid="";
        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(userid!=null)
        {
            dataa = FirebaseDatabase.getInstance().getReference("Chef").child(userid);
        }
        dataa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Chef cheff = snapshot.getValue(Chef.class);
                if(cheff!=null) {
                    State = cheff.getStates();
                    City = cheff.getCity();
                    Area = cheff.getArea();
                }

                Update_dish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        description = desc.getEditText().getText().toString().trim();
                        quantity = qty.getEditText().getText().toString().trim();
                        price = pri.getEditText().getText().toString().trim();

                        if(isValid())
                        {
                            if(imageuri != null)
                            {
                                uploadImage();
                            }else {
                                updatedesc(dburi);
                            }
                        }
                    }
                });

                Delete_dish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateDelete_Dish.this);
                        builder.setMessage("Are you sure you want to delete dish");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                firebaseDatabase.getReference("FoodDetails").child(State).child(City).child(Area)
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(ID).removeValue();
                                AlertDialog.Builder food = new AlertDialog.Builder(UpdateDelete_Dish.this);
                                food.setMessage("Your dish has been deleted !");
                                food.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(UpdateDelete_Dish.this, cheffoodpanel_bottomnavigation.class));
                                    }
                                });
                                AlertDialog alert = food.create();
                                alert.show();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });

                String userid="";
                userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if(userid!=null)
                {

                   progressDialog = new ProgressDialog(UpdateDelete_Dish.this);

                    databaseReference = FirebaseDatabase.getInstance().getReference("FoodDetails").child(City).child(userid);//.child(ID);
                    //.child(State).child(Area)
                }
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UpdateDishModel updateDishModel = snapshot.getValue(UpdateDishModel.class);
                        desc.getEditText().setText(updateDishModel.getDescription());
                        qty.getEditText().setText(updateDishModel.getQuantity());
                        Dishname.setText("Dish name:"+updateDishModel.getDishes());
                        dishes = updateDishModel.getDishes();
                        pri.getEditText().setText(updateDishModel.getPrice());
                        Glide.with(UpdateDelete_Dish.this).load(updateDishModel.getImageURL()).into(imageButton);
                        dburi = updateDishModel.getImageURL();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Fauth = FirebaseAuth.getInstance();
                databaseReference = firebaseDatabase.getInstance().getReference("FoodDetails");
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSelectedImageclick(v);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updatedesc(String dburi) {

        ChefId =FirebaseAuth.getInstance().getCurrentUser().getUid();
        FoodDetails info = new FoodDetails(dishes,quantity,price,description,dburi,ID,ChefId);
        firebaseDatabase.getInstance().getReference("FoodDetails").child(State).child(City).child(Area)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(ID)
                .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(UpdateDelete_Dish.this, "Dish Updates Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadImage() {

        if(imageuri != null){

            progressDialog.setTitle("Uploading....");
            progressDialog.show();
            RendomUID = UUID.randomUUID().toString();
            ref = storageReference.child(RendomUID);
            ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            updatedesc(String.valueOf(uri));
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(UpdateDelete_Dish.this, "Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setMessage("Upload "+(int) progress+"%");
                    progressDialog.setCanceledOnTouchOutside(false);
                }
            });

        }
    }

    private boolean isValid(){

        desc.setEnabled(false);
        desc.setError("");
        qty.setEnabled(false);
        qty.setError("");
        pri.setEnabled(false);
        pri.setError("");

        boolean isValidDescription=false,isValidPrice=false,isValidQuantity=false,isValid=false;
        if(TextUtils.isEmpty(description)){
            desc.setEnabled(true);
            desc.setError("Description is Required");
        }
        else
        {
            desc.setError(null);
            isValidDescription=true;
        }
        if(TextUtils.isEmpty(quantity)){
            desc.setEnabled(true);
            desc.setError("Enter a number of Plates or Items");
        }
        else
        {
            isValidQuantity=true;
        }
        if(TextUtils.isEmpty(price)){
            desc.setEnabled(true);
            desc.setError("Please Mention a Price");
        }
        else
        {
            isValidPrice=true;
        }
        isValid=(isValidDescription&&isValidQuantity&&isValidPrice)?true:false;
        return isValid;
    }


    private void  startCropImageActivity(Uri imageuri){
        CropImage.activity(imageuri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }
    private void onSelectedImageclick(View v){
        CropImage.startPickImageActivity(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mCropImageuri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        startCropImageActivity(mCropImageuri);
        } else {
            Toast.makeText(this, "Cancelling! Permission Not Granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){
                ((ImageButton) findViewById(R.id.image_upload)).setImageURI(result.getUri());
                Toast.makeText(this, "Cropped Successfully!" + result.getSampleSize(), Toast.LENGTH_SHORT).show();
            }
            else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Toast.makeText(this, "Failed to Crop"+result.getError(), Toast.LENGTH_SHORT).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}