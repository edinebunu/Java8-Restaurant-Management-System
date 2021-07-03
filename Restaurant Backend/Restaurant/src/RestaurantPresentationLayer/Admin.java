package RestaurantPresentationLayer;

import RestaurantBusinessLayer.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Admin extends Observer {

    @Override
    public void update() {

    }

    public void generateReport(int fromHour, int toHour,int minimumQuantity, int minOrders, int minPrice, int specificDay, int noOrders){

        DeliveryService deliveryInstance = DeliveryService.getInstance();

        Map<String, User> users = deliveryInstance.getUsers();
        List<Order> orders = deliveryInstance.getOrders();
        List<MenuItem> menu = deliveryInstance.getMenu();

        try {
            FileWriter myWriter = new FileWriter("Restaurant\\src\\RestaurantBusinessLayer\\adminReport.txt");

            for(Order o : orders){
                if(o.getOrderDate().getHours() >= fromHour && o.getOrderDate().getHours()<= toHour) {
                    myWriter.write("OrderId: " + o.getOrderId() +"\nUser: "+ o.getUsername()+ "\nOrder Components: " + '\n');

                    for(OrderItem i : o.getOrders()){
                        myWriter.write("    -"+i.getProduct().getName()+": "+ i.getQty() + '\n');
                    }

                    myWriter.write('\n');
                }
            }

            myWriter.write("------------------------------------\n");
            myWriter.write("Orders with more than "+minimumQuantity+" orders:\n");

            menu.stream().forEach(menuItem -> {
                    AtomicInteger count = new AtomicInteger(); orders.stream()
                    .forEach(orderItem -> {
                                List<OrderItem> l = orderItem.getOrders().stream().filter(item -> item.getProduct().getId() == menuItem.getId()).collect(Collectors.toList());
                                l.forEach(i -> count.getAndAdd(i.getQty()));
                    });

                    if (count.get() >= minimumQuantity) {
                        try {
                            myWriter.write("    -"+menuItem.getName()+'\n');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            });
            myWriter.write("------------------------------------\n");

            List<User> usersList = new ArrayList(users.values());
            usersList.stream().forEach(user -> {
                List<Order> o = orders.stream().filter( order ->
                   order.getUsername()
                           .equals(user.getUsername()) && order.getValue() >= minPrice && order.getOrders().size() >= minOrders)
                        .collect(Collectors.toList());

//                try {
//                    myWriter.write(user.getUsername()+'\n');
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                if(o.size()>0) {
                    try {
                        myWriter.write(user.getUsername()+'\n');
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });

            myWriter.write("------------------------------------\n");

            menu.stream().forEach(product -> {
                List<Order> o = orders.stream().filter(i -> i.getOrderDate().getDate() == specificDay).collect(Collectors.toList());

                AtomicInteger count = new AtomicInteger();

                o.stream().forEach(order -> {
                    List<OrderItem> orderItems = order.getOrders().stream().filter(item -> item.getProduct().getId() == product.getId()).collect(Collectors.toList());
                    orderItems.forEach(oi -> count.addAndGet(oi.getQty()));
                });

                try {
                    if(count.intValue() > 0)
                    myWriter.write(product.getName()+"  "+ count.intValue() +'\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

//        try(PrintWriter pw = new PrintWriter(Files.newBufferedWriter(
//                Paths.get("C:\\Users\\edmon\\Desktop\\Restaurant Backend\\Restaurant\\src\\RestaurantBusinessLayer\\adminReport.txt")))) {
//            IntStream.range(0, 5000).mapToObj(String::valueOf).forEach(pw::println);
//        }
//        catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }





    }
}
