package RestaurantBusinessLayer;

import java.util.ArrayList;

public interface IDeliveryServiceProcessing {
    void importProduct(String title, float rating, int calories,
                       int protein, int fat, int sodium, int price);

    void importProduct(String name, ArrayList<MenuItem> components);

    void updateProduct(String title,int id, int newRating, int newCalories,
                       int newProtein, int newFat, int newSodium, int newPrice);

    void deleteProduct(int id);

    int addToOrder(int id, int qty);

}
