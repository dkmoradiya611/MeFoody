package com.example.mefoody;

public class UpdateDishModel {

    String Dishes,randomUID,Description,Quantity,Price,ImageURL,ChefId;

    public UpdateDishModel(){

    }

    public String getDishes() {
        return Dishes;
    }

    public void setDishes(String dishes) {
        Dishes = dishes;
    }

    public String getRandomUID() {
        return randomUID;
    }

    public void setRandomUID(String randomUID) {
        this.randomUID = randomUID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getChefId() {
        return ChefId;
    }

    public void setChefId(String chefId) {
        ChefId = chefId;
    }
}
