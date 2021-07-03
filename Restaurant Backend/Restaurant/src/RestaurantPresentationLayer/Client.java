package RestaurantPresentationLayer;

import RestaurantBusinessLayer.Observer;

import javax.security.auth.Subject;

public class Client extends Observer {

    private static Client INSTANCE;

    public void run(){

    }

    public static Client getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Client();
        }

        return INSTANCE;
    }

    @Override
    public void update() {
        System.out.println( "Client Updated" );
    }

}