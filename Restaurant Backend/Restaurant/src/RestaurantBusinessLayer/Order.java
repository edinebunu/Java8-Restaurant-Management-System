package RestaurantBusinessLayer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Order implements Serializable {
    private int orderId;
    private String username;
    private Date orderDate;

    List<OrderItem> orders = new ArrayList<>();

    public Order(int orderId, User user, Date date){
        this.orderId = orderId;
        this.username = user.getUsername();
        this.orderDate = date;
    }

    public void addToOrder(MenuItem m, int qty)
    {
        OrderItem orderItem = new OrderItem(m,qty);
        orders.add(orderItem);
    }

    public int getValue(){
        int v = 0;
        for(OrderItem i : orders){
            v += i.getProduct().getPrice();
        }
        return v;
    }

    public void createBill(){

        try {
            FileWriter myWriter = new FileWriter("Restaurant\\src\\RestaurantBusinessLayer\\bill.txt");

            for (OrderItem order : orders)
                myWriter.write(order.getProduct().getName() + ",  quantity: " + order.getQty() + '\n');
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public int getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }
}
