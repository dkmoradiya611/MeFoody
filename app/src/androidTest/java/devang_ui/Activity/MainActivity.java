// Please paste this code, where you want to use..
// [ ' PLEASE PASTE PROPERLY ' ]
// [ ' GIVE THE NAMES OF ACTIVITIES AS WELL AS ID , AS MENTIONED BELOW ' ]

/*


package devang_ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zwagii.Adapter.FoodListAdapter;
import com.example.zwagii.Domain.FoodDomain;
import com.example.zwagii.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterFoodList;
    private RecyclerView recyclerViewFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerview();
        bottomNavigation();
    }

    private void bottomNavigation() {
      //  LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);

//        homeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,MainActivity.class));
//            }
//        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }

    private void initRecyclerview() {
        ArrayList<FoodDomain> items = new ArrayList<>();
        items.add(new FoodDomain("Cheese Burger","Satisfy your cravings with our juicy Cheese Burger. \n"+
                "Made with 100% Angus beef patty and topped with\n"+
                " melted cheddar cheese, fresh lettuce, tomato, and\n"+
                " our secret sauce, this classic burger will leave you\n"+
                " wanting more. Served with crispy fries and a drink,\n"+
                " it's the perfect meal for any occasion.","fast_1",15,20,120,4));
        items.add(new FoodDomain("Pizza Peperoni","Get a taste of Italy with our delicious peperoni pizza. Made with freshly rolled dough, zesty tomato sauce, mozzarella cheese, and topped with spicy pepperoni Slices, this pizza is sure to be a crowd Pleaser. Perfectly baked in a wood-fired oven, it's the perfect choice for a quick lunch or a family dinner.","fast_2",10,25,200,5));
        items.add(new FoodDomain("Vegetable Pizza","looking for a healthier option? Try our vegetable pizza, made with a variety of a fresh veggies such as bell peppers, onions, mushrooms, olives, and tomatoes. Topped with mozzarella cheese and a tangy tomato sauce, this pizza is full of flavor and goodness. Perfect for a vegetarians and anyone who wants to add more greens to their diet.","fast_3",13,30,100,4.5));

        recyclerViewFood = findViewById(R.id.view1);
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        adapterFoodList = new FoodListAdapter(items);
        recyclerViewFood.setAdapter(adapterFoodList);
    }
}


 */