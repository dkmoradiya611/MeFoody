// Please paste this code, where you want to use..
// [ ' PLEASE PASTE PROPERLY ' ]
// [ ' GIVE THE NAMES OF ACTIVITIES AS WELL AS ID , AS MENTIONED BELOW ' ]

/*

package devang_ui.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zwagii.Adapter.CartListAdapter;
import com.example.zwagii.Helper.ChnageNumberItemsListener;
import com.example.zwagii.Helper.ManagementCart;
import com.example.zwagii.R;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt,taxTxt,deliveryTxt,totaltxt,emptyTxt;
    private double tax;
    private ScrollView scrollView;
    private ImageView backBtn;
    private AppCompatButton orderBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);
        initView();
        initList();
        calculateCart();
        setVariable();
        
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
    }

    public void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(),this, new ChnageNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);

        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }


    private void calculateCart(){
        double percentTax=0.02;
        double delivery =10;
        tax = Math.round(managementCart.getTotalFee() * percentTax * 100.0)/100.0;

        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totaltxt.setText("$"+total);


    }

    private void initView() {

        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totaltxt = findViewById(R.id.totalTxt);
        recyclerViewList = findViewById(R.id.view3);
        scrollView = findViewById(R.id.scrollViewCart);
        backBtn = findViewById(R.id.backBtn);
        orderBtn = findViewById(R.id.orderBtn);
        emptyTxt = findViewById(R.id.emptyTxt);

    }
}


*/