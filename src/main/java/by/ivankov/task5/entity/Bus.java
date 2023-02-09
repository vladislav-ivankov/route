package by.ivankov.task5.entity;

import by.ivankov.task5.util.BusIdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Bus extends Thread {
    static Logger logger = LogManager.getLogger();
    private Random random = new Random();
    private long id;
    private int passengers;
    private Route route;

    public enum StateBus {
        UNLOAD, UNLOAD_AND_LOAD
    }

    private StateBus stateBus;

    public Bus(StateBus stateBus) {
        this.route = Route.getInstance();
        this.id = BusIdGenerator.idGenerator();
        this.stateBus = stateBus;
        this.passengers = random.nextInt(25);
    }

    public long getId() {
        return id;
    }
    @Override
    public void run() {
        Route route = Route.getInstance();
        Stop stop = null;
        try {
            stop = route.getStop();
            stop.getSemaphore().acquire();
            logger.log(Level.INFO, "The bus arrives at the stop " + stop.getId());
            System.out.println("The bus " + id + " is arrives at the stop " + stop.getId());
            TimeUnit.SECONDS.sleep(2);
            switch (stateBus) {
                case UNLOAD -> {
                    System.out.println("Passengers out " + route.unload(passengers) + " at the bus stop " + stop.getId());
                }
                case UNLOAD_AND_LOAD -> {
                    route.unload(passengers);
                    System.out.println("Passengers have moved " + route.load(passengers) + " at the bus stop " + stop.getId());
                }
            }
            TimeUnit.SECONDS.sleep(5);
            stop.getSemaphore().release();
            logger.log(Level.INFO, "The bus departs from the stop " + stop.getId());
            System.out.println("The bus " + id + " is departs from the stop " + stop.getId());
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        } finally {
            route.releaseStop(stop);
        }
    }
}

