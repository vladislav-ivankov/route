package by.ivankov.task5.entity;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class StopsGenerator {
    BlockingDeque<BusStop> busStops;
    private Route route;

    public StopsGenerator() {
        this.busStops = new LinkedBlockingDeque<>();
        this.route = Route.getInstance();
    }

    public BlockingDeque<BusStop> generatorStops(int numberOfStops) {
        for (int i = 0; i < numberOfStops; i++) {
            busStops.add(new BusStop());
        }
        route.setStops(busStops);
        return busStops;
    }
}
