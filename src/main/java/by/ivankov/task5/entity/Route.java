package by.ivankov.task5.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Route {

    private static Logger logger = LogManager.getLogger();
    private static Route instance;
    private static ReentrantLock lock = new ReentrantLock(true);
    private static AtomicBoolean create = new AtomicBoolean(false);
    private AtomicInteger busPassengers = new AtomicInteger();
    private Condition condition = lock.newCondition();
    private BlockingDeque<BusStop> busStops;

    private Route() {
        busStops = new LinkedBlockingDeque<>();
    }

    public static Route getInstance() {
        if (!create.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Route();
                    create.getAndSet(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void setStops(BlockingDeque<BusStop> busStops) {
        this.busStops = busStops;
    }

    public BusStop getStop() {
        BusStop stop = null;
            try {
                stop = busStops.take();
            } catch (InterruptedException e) {
                logger.error("The thread is busy", e);
            }
        return stop;
    }

    public void releaseStop(BusStop busStop) {
        busStops.offer(busStop);
    }

    public int load(int passengers) {
        lock.lock();
        try {
            busPassengers.addAndGet(-passengers);
        } finally {
            {
                lock.unlock();
            }
        }
        return passengers;
    }

    public int unload(int passengers) {
        lock.lock();
        try {
            busPassengers.addAndGet(passengers);
        } finally {
            {
                lock.unlock();
            }
        }
        return passengers;
    }
}
