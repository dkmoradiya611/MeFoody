// Please paste this code, where you want to use..
// [ ' PLEASE PASTE PROPERLY ' ]
// [ ' GIVE THE NAMES OF ACTIVITIES AS WELL AS ID , AS MENTIONED BELOW ' ]

/*

package devang_ui.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.zwagii.Domain.FoodDomain;
import com.example.zwagii.Helper.ChnageNumberItemsListener;
import com.example.zwagii.Helper.ManagementCart;
import com.example.zwagii.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    ArrayList<FoodDomain> listFoodSelected;
    private ManagementCart managementCart;
    ChnageNumberItemsListener chnageNumberItemsListener;

    public CartListAdapter(ArrayList<FoodDomain> listFoodSelected, Context context, ChnageNumberItemsListener chnageNumberItemsListener) {
        this.listFoodSelected = listFoodSelected;
        managementCart = new ManagementCart(context);
        this.chnageNumberItemsListener = chnageNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(listFoodSelected.get(position).getTitle());
        holder.feeEachTime.setText("$"+listFoodSelected.get(position).getPrice());
        holder.totalEachItem.setText("$"+Math.round(listFoodSelected.get(position).getNumberInCart() * listFoodSelected.get(position).getPrice()));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberInCart()));


        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(listFoodSelected.get(position).getPicurl(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,30,30))
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood(listFoodSelected, holder.getAdapterPosition(), new ChnageNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        chnageNumberItemsListener.changed();
                    }
                });

            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberFood(listFoodSelected, holder.getAdapterPosition(), new ChnageNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        chnageNumberItemsListener.changed();
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return listFoodSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,feeEachTime,minusItem,plusItem;
        ImageView pic;
        TextView totalEachItem,num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleText_cart);
            pic = itemView.findViewById(R.id.picCart);
            feeEachTime = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartbtn);
            num = itemView.findViewById(R.id.numberItemCarttxt);
        }
    }

}


 */