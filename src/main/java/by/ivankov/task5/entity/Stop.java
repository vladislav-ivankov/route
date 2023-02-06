package by.ivankov.task5.entity;

import by.ivankov.task5.exception.RouteException;
import by.ivankov.task5.util.StopIdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringJoiner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Stop {
    private long id;
    private static Logger logger = LogManager.getLogger();
    private Semaphore semaphore;
    private Deque<Stop> stops;
    private ReentrantLock lock = new ReentrantLock();
    private int counterBusesAtTheStop;
    private static final int maxBussesAtStop = 2;

    public Stop() {
        this.id = StopIdGenerator.idGenerator();
        this.stops = new ArrayDeque<>();
        semaphore = new Semaphore(maxBussesAtStop);
    }

    public long getId() {
        return id;
    }

    public int getCounterBusesAtTheStop() {
        return counterBusesAtTheStop;
    }

    public void setCounterBusesAtTheStop(int counterBusesAtTheStop) {
        this.counterBusesAtTheStop = counterBusesAtTheStop;
    }

    public int getMaxBussesAtStop() {
        return maxBussesAtStop;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void arrival() throws RouteException {// TODO: 06.02.2023
        lock.lock();
        try {
            while (counterBusesAtTheStop >= maxBussesAtStop) {
                logger.log(Level.INFO, "the bus is arriving" + id);
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RouteException(e);
                }
            }
            counterBusesAtTheStop++;
        } finally {
            lock.unlock();
        }
    }

    public void departure() {// TODO: 06.02.2023
        lock.lock();
        try {
            counterBusesAtTheStop--;
            lock.notifyAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Stop.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("stops=" + stops.size())
                .toString();
    }
}
