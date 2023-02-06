package by.ivankov.task5.entity;


import by.ivankov.task5.exception.RouteException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Route {
    private static Logger logger = LogManager.getLogger();
    private static Route instance;
    private static ReentrantLock lock = new ReentrantLock();
    private List<Stop> stops;
    private List<Bus> buses;
    private Bus bus;
    private static final int maxBussesInRoute = 20;
    private static final int minBussesInRoute = 0;
    private int bussesCounter = 0;

    private Route() {
        stops = new ArrayList<>();
        buses = new ArrayList<>();
    }

    public static Route getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new Route();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    public synchronized boolean addBus(Bus element) throws RouteException { // TODO: 06.02.2023    
        if (bussesCounter < maxBussesInRoute) {
            notifyAll();
            buses.add(element);
            logger.log(Level.INFO, "Bus added to the route: " + buses.size());
            bussesCounter++;
        } else {
            logger.log(Level.INFO, "the route is busy: " + buses.size());
        }
        return true;
    }

    public synchronized Bus removeBus(int id) throws RouteException {
        if (bussesCounter > minBussesInRoute) {
            notifyAll();
            for (Bus bus : buses) {
                if (id == bus.getId()) {
                    bussesCounter--;
                    logger.log(Level.INFO, "the route is busy" + id, buses.size());
                    buses.remove(bus);
                    return bus;
                }
            }
        }
        logger.log(Level.ERROR, "there are no buses on the route");
        return null;
    }

    public void addStop(Stop stop) {
        stops.add(stop);
    }

   public void removeStop(int id) {
        stops.remove(id);
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public List<Stop> getStops() {
        return stops;
    }
}
