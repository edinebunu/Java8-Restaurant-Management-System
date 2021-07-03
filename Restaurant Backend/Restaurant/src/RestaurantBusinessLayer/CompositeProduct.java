package RestaurantBusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem implements Serializable {

    private int id;
    ArrayList<MenuItem> productComponents = new ArrayList<>();
    String compositeName;

    public CompositeProduct(String compositeProdName, int id) {
        this.compositeName = compositeProdName;
        this.id = id;
    }

    public void addBaseProduct(MenuItem newMenuItem) {
        productComponents.add(newMenuItem);
    }

    public void updateCompositeProduct(ArrayList<MenuItem> baseProducts) {

        this.productComponents = baseProducts;
    }

    public void removeBaseProduct(MenuItem newMenuItem) {
        productComponents.remove(newMenuItem);
    }

    public MenuItem getComponent(int componentIndex) {
        return productComponents.get(componentIndex);
    }

    @Override
    public void displayProduct() {
        for(MenuItem i : productComponents)
            i.displayProduct();
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id){this.id = id;}

}
