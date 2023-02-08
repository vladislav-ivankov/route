package by.ivankov.task5.entity;

import by.ivankov.task5.util.StopIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.StringJoiner;
import java.util.concurrent.Semaphore;

public class Stop {
    private static Logger logger = LogManager.getLogger();
    private long id;
    private Semaphore semaphore;

    public Stop() {
        this.id = StopIdGenerator.idGenerator();
        this.semaphore = new Semaphore(2);
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
        return new StringJoiner(", ", Stop.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .toString();
    }
}
