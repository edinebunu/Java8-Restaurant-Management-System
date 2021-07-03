import RestaurantBusinessLayer.DeliveryService;
import RestaurantDataLayer.FileWriter;
import RestaurantDataLayer.Serializator;
import RestaurantPresentationLayer.Admin;
import RestaurantPresentationLayer.Client;
import RestaurantPresentationLayer.Employee;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String args[]) throws Exception {


        Serializator s = new Serializator();

        DeliveryService ds = DeliveryService.getInstance();


        //ds.logIn("aaa", "qqqqqq");
        //FileWriter.updateMenu();

        ds.deserializeSelf();

        ds = DeliveryService.getInstance();

//        ds.register("aaa", "qqqqqq", "abc");
//        ds.register("bbb", "qqqqqq", "abc");
//        ds.register("ccc", "qqqqqq", "abc");

        ds.logIn("aaa", "qqqqqq");

        //ds.displayAll();

        ds.addToOrder(1,5);
        ds.addToOrder(3,5);
        ds.addToOrder(95,5);

        ds.finalyzeOrder();

        ds.addToOrder(1,5);
        ds.addToOrder(3,5);
        ds.addToOrder(95,7);
        ds.addToOrder(1,2);

        ds.finalyzeOrder();

        ds.displayAllOrders();

        ds.getByName("Qu");

        //ds.serializeSelf();

        Admin a = new Admin();
        a.generateReport(0,23,12,1,0,30,0);


    }
}
