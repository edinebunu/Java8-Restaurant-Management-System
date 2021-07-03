package RestaurantBusinessLayer;

import java.io.Serializable;

public class OrderItem implements Serializable {
    MenuItem product;
    int qty;

    public OrderItem(MenuItem product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    public MenuItem getProduct() {
        return product;
    }

    public void setProduct(MenuItem product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
