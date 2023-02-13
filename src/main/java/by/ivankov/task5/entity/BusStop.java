package by.ivankov.task5.entity;

import by.ivankov.task5.util.StopIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.StringJoiner;
import java.util.concurrent.Semaphore;

public class BusStop {
    private static Logger logger = LogManager.getLogger();
    private long id;
    private static final int MAX_BUS_AT_STOP = 2;
    private Semaphore semaphore;

    public BusStop() {
        this.id = StopIdGenerator.idGenerator();
        this.semaphore = new Semaphore(MAX_BUS_AT_STOP);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BusStop.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .toString();
    }
}
