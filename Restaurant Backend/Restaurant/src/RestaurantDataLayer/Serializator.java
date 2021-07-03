package RestaurantDataLayer;

import RestaurantBusinessLayer.DeliveryService;
import RestaurantBusinessLayer.MenuItem;

import java.io.*;

public class Serializator {

    public void serializeDeliveryService(DeliveryService d) throws IOException {

        DeliveryService instance = DeliveryService.getInstance();

        FileOutputStream fout = new FileOutputStream("Restaurant\\src\\output.ser");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(d);

        out.close();
    }

    public DeliveryService deserializeDeliveryService() throws IOException, ClassNotFoundException {

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Restaurant\\src\\output.ser"));
        DeliveryService instance = (DeliveryService) in.readObject();

        in.close();

        return instance;
    }

    public boolean isSerialized() throws IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Restaurant\\src\\output.ser"));
        return in.available() != 0;

    }

















}
