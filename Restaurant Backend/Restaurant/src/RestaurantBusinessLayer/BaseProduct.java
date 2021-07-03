package RestaurantBusinessLayer;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {

    private String title;
    private int id;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public BaseProduct(String title,int id, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.id = id;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    @Override
    public void displayProduct(){
        System.out.println(this.id+" - "+this.title+", "+this.rating+", "+this.calories+", "+this.protein+", "+this.fat+", "+this.sodium+", "+this.price);
    }


    public void updateBaseProduct(String title,int id, double rating, int calories, int protein, int fat, int sodium, int price){
        this.title = title;
        this.id = id;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public String getName() {
        return title;
    }

    public int getId(){return this.id; }

    public void setId(int id){this.id = id;}

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
