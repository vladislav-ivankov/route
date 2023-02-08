package by.ivankov.task5.entity;

import by.ivankov.task5.util.BusIdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Bus extends Thread {
    private static Logger logger = LogManager.getLogger();
    private long id;
    private int passengers;
    public enum StateBus{
        UNLOAD, UNLOAD_AND_LOAD
    }
    private StateBus stateBus;

    public Bus(StateBus stateBus, int passengers) {
        this.id = BusIdGenerator.idGenerator();
        this.stateBus = stateBus;
        this.passengers = passengers;
    }

    public long getId() {
        return id;
    }

    @Override
    public void run() {// TODO: 06.02.2023
        Route route = Route.getInstance();
        Stop stop = null;
        try {
            stop = route.getStop();
            logger.log(Level.INFO, "The bus " + id + " is arriving " + stop.getId());
            switch (stateBus){
                case UNLOAD -> route.unload(passengers);
                case UNLOAD_AND_LOAD -> {
                    route.unload(passengers);
                    route.load(passengers);
                }
            }
            TimeUnit.SECONDS.sleep(2);
            stop.getSemaphore().release();
            logger.log(Level.INFO, "The bus " + id + "is departure " + stop.getId());
        }catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }finally {
            route.releaseStop(stop);
        }
    }
}

