// Please paste this code, where you want to use..
// [ ' PLEASE PASTE PROPERLY ' ]
// [ ' GIVE THE NAMES OF ACTIVITIES AS WELL AS ID , AS MENTIONED BELOW ' ]



/*

package devang_ui.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.zwagii.Domain.FoodDomain;
import com.example.zwagii.Helper.ManagementCart;
import com.example.zwagii.R;

public class DetailActivity extends AppCompatActivity {
private Button addToCartBtn;
private TextView plusBtn,minusBtn, titleTxt, feeTxt, descriptionTxt, numberOrderTxt, startTxt, calTxt, timeTxt;
private ImageView picFood,backBtn;
private FoodDomain object;
private int numberOrder = 1;
private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        managementCart = new ManagementCart(DetailActivity.this);

        initView();
        getBundle();


        backBtn=findViewById(R.id.backBtnOfDetail);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });

    }

    private void getBundle() {

        object = (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId=this.getResources().getIdentifier(object.getPicurl(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getPrice());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(""+numberOrder);
        calTxt.setText(object.getEnergy() + "Cal");
        startTxt.setText(object.getScore() + "");
        timeTxt.setText(object.getTime() + "min");
        addToCartBtn.setText("Add to cart - $" + Math.round(numberOrder * object.getPrice()));

        plusBtn.setOnClickListener(v -> {
            numberOrder = numberOrder + 1;
            numberOrderTxt.setText(""+numberOrder);
            addToCartBtn.setText("Add to cart - $" + Math.round(numberOrder * object.getPrice()));
        });

        minusBtn.setOnClickListener(v -> {
            numberOrder = numberOrder - 1;
            numberOrderTxt.setText(""+numberOrder);
            addToCartBtn.setText("Add to cart - $" + Math.round(numberOrder * object.getPrice()));
        });

        addToCartBtn.setOnClickListener(v -> {
            object.setNumberInCart(numberOrder);
            managementCart.insertFood(object);
        });
    }

    private void initView() {

        addToCartBtn=findViewById(R.id.addToCartBtn);
        timeTxt=findViewById(R.id.timeTxt2);
        feeTxt=findViewById(R.id.priceTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberItemTxt);
        plusBtn=findViewById(R.id.plusCardBtn);
        minusBtn=findViewById(R.id.MinusCartBtn);
        picFood=findViewById(R.id.foodPic);
        startTxt=findViewById(R.id.StarTxt);
        calTxt=findViewById(R.id.calTxt);
        titleTxt=findViewById(R.id.titleTxt1);
    }
}


 */