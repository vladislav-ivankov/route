package by.ivankov.task5.entity;

import java.util.*;

public class StopsGenerator {
    ArrayDeque<BusStop> busStops;
    private Route route;

    public StopsGenerator() {
        this.busStops = new ArrayDeque<>();
        this.route = Route.getInstance();
    }

    public ArrayDeque<BusStop> generatorStops(int numberOfStops) {
        for (int i = 0; i < numberOfStops; i++) {
            busStops.add(new BusStop());
        }
        route.setStops(busStops);
        return busStops;
    }
}
