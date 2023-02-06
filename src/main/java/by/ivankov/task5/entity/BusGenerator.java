package by.ivankov.task5.entity;

import by.ivankov.task5.exception.RouteException;

import javax.swing.plaf.TableHeaderUI;
import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BusGenerator implements Runnable {
    private Bus bus;
    private Route route;
    private int busCount;

    public BusGenerator(Route route, int busCount){
        this.busCount = busCount;
        this.route = route;
    }

    @Override
    public void run() {
        int count = 0;
        while(count < busCount){
            Thread.currentThread().setName("Generator bus");
            count++;
            try {
                route.addBus(new Bus());
            } catch (RouteException e) {
                throw new RuntimeException(e);
            }
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
