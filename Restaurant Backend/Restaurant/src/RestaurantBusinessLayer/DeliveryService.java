package RestaurantBusinessLayer;


import RestaurantDataLayer.Serializator;
import RestaurantPresentationLayer.Client;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryService implements IDeliveryServiceProcessing, Subject, Serializable {

    public int a = 1;

    private static final long serialVersionUID = 1L;

    List<MenuItem> menu = new ArrayList<MenuItem>();
    private final List<Observer> observers = new ArrayList<>();
    HashMap<String, User> users = new HashMap<>();
    List<Order> orders = new ArrayList<>();
    Order currentOrder;
    User currentUser;

    private static DeliveryService INSTANCE = new DeliveryService();

    public HashMap<String, User> getUsers() {
        return users;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public static DeliveryService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DeliveryService();
        }
        return INSTANCE;
    }

    public void serializeSelf() throws IOException {
        Serializator s = new Serializator();
        s.serializeDeliveryService(INSTANCE);
    }

    public void deserializeSelf() throws IOException, ClassNotFoundException {
        Serializator s = new Serializator();
        INSTANCE = s.deserializeDeliveryService();

    }

    private DeliveryService(){

    }

    public void finalyzeOrder(){
        orders.add(currentOrder);

        currentOrder.createBill();

        currentOrder = new Order(orders.size()+1,currentUser,new Date());
    }

    public void logIn(String user, String pass) throws Exception {
        if(users.containsKey(user)){
            if(users.get(user).getPassword().equals(pass))
                currentUser = users.get(user);
            else throw new Exception("Invalid credentials");

            Client client = Client.getInstance();
            client.run();

            currentOrder = new Order(orders.size()+1,currentUser,new Date());
        }
        else throw new Exception("Invalid account");

    }

    public void register(String user, String pass, String name) throws Exception {

        User u = new User(user,pass,name);
        if(!users.containsKey(user))
            users.put(user,u);
        else throw new Exception("Username exists");
    }

    public List<MenuItem> getByName(String text){

        try{
        List<MenuItem> resultList = menu.stream()
                .filter(item -> item.getName().contains(text))
                    .collect(Collectors.toList());

        System.out.println("---------------");
        resultList.forEach(MenuItem::displayProduct);
        return resultList;

        }
        catch(UnsupportedOperationException e){

            System.out.println("---------------");
            return null;
        }
    }

    public List<MenuItem> getMenu(){
        return menu;
    }

    @Override
    public void importProduct(String title, float rating, int calories, int protein, int fat, int sodium, int price) {
        MenuItem m = new BaseProduct(title, menu.size(), rating, calories, protein, fat, sodium, price);
        menu.add(m);

    }

    @Override
    public void importProduct(String name, ArrayList<MenuItem> components) {
        MenuItem m = new CompositeProduct(name, menu.size());
        for(MenuItem i : components)
            m.addBaseProduct(i);

        menu.add(m);
    }

    @Override
    public void updateProduct(String title,int id, int newRating, int newCalories, int newProtein, int newFat, int newSodium, int newPrice) {
        menu.get(id).updateBaseProduct(title,id,newRating,newCalories,newProtein,newFat,newSodium,newPrice);
    }

    @Override
    public void deleteProduct(int id) {
        menu.remove(id);
        for(int i=0; i<menu.size(); i++)
            menu.get(i).setId(i);
    }

    private boolean containsItem(int id){
        for(MenuItem i : menu)
            if(i.getId() == id)return true;

        return false;
    }

    @Override
    public int addToOrder(int id, int qty) {

        if(containsItem(id)){
            if(qty > 0){
                MenuItem i = menu.get(id-1);
                currentOrder.addToOrder(i,qty);
            }
            return 1;
        }
        else return -1;
    }

    public void displayAll(){
        for(int i=1; i< menu.size(); i++)
            menu.get(i).displayProduct();
    }

    public void displayAllUsers(){

        for (Map.Entry mapElement : users.entrySet()) {
            String key = (String)mapElement.getKey();

            System.out.println(key + " : ");
        }
        System.out.println(users);
    }

    public void displayAllOrders(){
        for(Order o : orders){
            System.out.println(o.getOrderId()+"   "+o.getOrderDate().getHours()+ "    " );
            o.getOrders().forEach(i -> System.out.println("    "+i.getProduct().getName()+"    " + i.getQty()));
            System.out.println('\n');
        }
    }

    @Override
    public void attach(RestaurantBusinessLayer.Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(RestaurantBusinessLayer.Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyUpdate() {
        for(Observer o: observers) {
            o.update();
        }
    }
}
