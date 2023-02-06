package by.ivankov.task5.entity;

import by.ivankov.task5.exception.RouteException;
import by.ivankov.task5.util.BusIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class Bus extends Thread {
    private static Logger logger = LogManager.getLogger();
    private long id;
    private Deque<Bus> buses;
    private Deque<Stop> stops;
    private Stop stop;

    public Bus() {
        this.id = BusIdGenerator.idGenerator();
        this.buses = new ArrayDeque<>();
        this.stops = new ArrayDeque<>();
    }

    public long getId() {
        return id;
    }

    @Override
    public void run() {// TODO: 06.02.2023  
        for (Stop stop : stops) {
            synchronized (stop) {
                while (stop.getCounterBusesAtTheStop() >= stop.getMaxBussesAtStop()) {
                    try {
                        try {
                            stop.getSemaphore().acquire();
                            stop.arrival();
                            TimeUnit.SECONDS.sleep(2);
                            stop.getSemaphore().release();
                            stop.departure();
                        } catch (RouteException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } finally {
                    }
                }
            }
        }
    }
}

