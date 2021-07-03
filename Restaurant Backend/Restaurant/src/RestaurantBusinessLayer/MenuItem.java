package RestaurantBusinessLayer;

import java.util.ArrayList;

public abstract class MenuItem {

    public void updateBaseProduct(String title,int id, double rating, int calories, int protein, int fat, int sodium, int price) {

        throw new UnsupportedOperationException();
    }

    public void updateCompositeProduct(ArrayList<MenuItem> baseProducts) {

        throw new UnsupportedOperationException();
    }

    public void addBaseProduct(MenuItem newMenuItem) {

        throw new UnsupportedOperationException();
    }

    public void removeBaseProduct(MenuItem newMenuItem) {

        throw new UnsupportedOperationException();
    }

    public MenuItem getComponent(int componentIndex) {

        throw new UnsupportedOperationException();
    }

    public abstract void displayProduct();

    public abstract int getId();

    public abstract void setId(int id);

    public String getName(){
        throw new UnsupportedOperationException();
    }

    public int getCalories(){
        throw new UnsupportedOperationException();
    }
    public int getProtein(){
        throw new UnsupportedOperationException();
    }
    public int getFat(){
        throw new UnsupportedOperationException();
    }
    public int getSodium(){
        throw new UnsupportedOperationException();
    }
    public int getPrice(){
        throw new UnsupportedOperationException();
    }

}
