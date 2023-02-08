package by.ivankov.task5.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Route {

    private static Logger logger = LogManager.getLogger();
    private Random random = new Random();
    private static Route instance;
    private static ReentrantLock lock = new ReentrantLock(true);
    private static AtomicBoolean create = new AtomicBoolean(false);
    private AtomicInteger passengers = new AtomicInteger(150);
    private Deque<Stop> stops;

    private Route() {
        stops = new ArrayDeque<>();
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

    public Deque<Stop> getStops() {
        return stops;
    }

    public Random getRandom() {
        return random;
    }

    public Stop getStop() {
        lock.lock();
        Stop stop;
        try {
            stop = stops.poll();
        } finally {
            lock.unlock();
        }
        return stop;
    }

    public void releaseStop(Stop stop) {
        lock.lock();
        try {
            stops.add(stop);
        } finally {
            lock.unlock();
        }
    }

    public void addStop(Stop stop) {
        lock.lock();
        try {
            stops.add(stop);
        } finally {
            lock.unlock();
        }
    }

    public void removeStop(int id) {
        lock.lock();
        try {
            stops.remove(id);
        } finally {
            lock.unlock();
        }
    }

    public int load(int passengers) {
        lock.lock();
        try {
            this.passengers.addAndGet(-random.nextInt(passengers));
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
            this.passengers.addAndGet(random.nextInt(passengers));
        } finally {
            {
                lock.unlock();
            }
        }
        return passengers;
    }
}
