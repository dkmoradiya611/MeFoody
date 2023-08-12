// Please paste this code, where you want to use..
// [ ' PLEASE PASTE PROPERLY ' ]
// [ ' GIVE THE NAMES OF ACTIVITIES AS WELL AS ID , AS MENTIONED BELOW ' ]

/*
package devang_ui.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.zwagii.Domain.FoodDomain;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
       this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listfood = getListCart();
        boolean existAlready = false;
        int n=0;
        for(int y=0; y<listfood.size(); y++)
        {
            if(listfood.get(y).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=y;
                break;
            }
        }
        if(existAlready){
            listfood.get(n).setNumberInCart(item.getNumberInCart());
        }else
        {
            listfood.add(item);
        }
        tinyDB.putListObject("CartList",listfood);
        Toast.makeText(context, "Added to your cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void minusNumberFood(ArrayList<FoodDomain> listfood, int position,ChnageNumberItemsListener chnageNumberItemsListener){
        if(listfood.get(position).getNumberInCart()==1){
            listfood.remove(position);
        }else{
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList",listfood);
        chnageNumberItemsListener.changed();
    }

    public void plusNumberFood(ArrayList<FoodDomain> listfood,int position, ChnageNumberItemsListener chnageNumberItemsListener){
        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList",listfood);
        chnageNumberItemsListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<FoodDomain> listfood2 = getListCart();
        double fee = 0;
        for(int i=0; i < listfood2.size(); i++){
            fee = fee + (listfood2.get(i).getPrice() * listfood2.get(i).getNumberInCart());
        }
        return fee;
    }


}


 */