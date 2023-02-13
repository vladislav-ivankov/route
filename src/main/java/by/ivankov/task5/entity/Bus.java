package by.ivankov.task5.entity;

import by.ivankov.task5.util.BusIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Bus extends Thread {
    static Logger logger = LogManager.getLogger();
    private Random random = new Random();
    private static final int MAX_PASSENGERS_IN_BUS = 50;
    private long id;
    private int passengers;

    public enum StateBus {
        UNLOAD, UNLOAD_AND_LOAD
    }

    private StateBus stateBus;

    public Bus() {
        this.id = BusIdGenerator.idGenerator();
        this.stateBus = randomState();
        this.passengers = random.nextInt(MAX_PASSENGERS_IN_BUS);
    }

    public long getId() {
        return id;
    }

    public StateBus randomState() {
        int randomValue = random.nextInt(2);
        return randomValue == 0 ? StateBus.UNLOAD : StateBus.UNLOAD_AND_LOAD;
    }

    @Override
    public void run() {
        Route route = Route.getInstance();
        BusStop busStop = null;
        try {
            busStop = route.getStop();
            busStop.getSemaphore().acquire();
            logger.info("The bus " + id + " is arrives at the stop " + busStop.getId());
            System.out.println("The bus " + id + " is arrives at the stop " + busStop.getId() +"\n");
            TimeUnit.SECONDS.sleep(2);
            switch (stateBus) {
                case UNLOAD -> {
                    logger.info("Passengers out" + route.unload(passengers) + " at the bus stop "  + busStop.getId());
                    System.out.println("Passengers out " + route.unload(passengers) + " at the bus stop " + busStop.getId());
                }
                case UNLOAD_AND_LOAD -> {
                    route.unload(passengers);
                    logger.info("Passengers have moved " +route.load(passengers) + " at the bus stop " + busStop.getId());
                    System.out.println("Passengers have moved " + route.load(passengers) + " at the bus stop " + busStop.getId());
                }
            }
            TimeUnit.SECONDS.sleep(5);
            busStop.getSemaphore().release();
            logger.info("The bus departs from the stop " + busStop.getId());
            System.out.println("The bus " + id + " is departs from the stop " + busStop.getId() +"\n");
        } catch (InterruptedException e) {
            logger.error("The thread is busy", e);
        } finally {
            route.releaseStop(busStop);
        }
    }
}

