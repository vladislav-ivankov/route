package by.ivankov.task5.main;


import by.ivankov.task5.entity.*;

import java.util.ArrayDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RouteMain {
    public static void main(String[] args) {
        Route route = Route.getInstance();
        StopsGenerator generator = new StopsGenerator();
        BusGenerator busGenerator = new BusGenerator(20);

        ArrayDeque<BusStop> busStops = new ArrayDeque<>();
        generator.generatorStops(5);

        Bus bus1 = new Bus();
        Bus bus2 = new Bus();
        Bus bus3 = new Bus();
        Bus bus4 = new Bus();
        Bus bus5 = new Bus();
        Bus bus6 = new Bus();
        Bus bus7 = new Bus();
        Bus bus8 = new Bus();
        Bus bus9 = new Bus();
        Bus bus10 = new Bus();
        Bus bus11 = new Bus();
        Bus bus12 = new Bus();
        Bus bus13 = new Bus();
        Bus bus14 = new Bus();
        Bus bus15 = new Bus();
        Bus bus16 = new Bus();
        Bus bus17 = new Bus();
        Bus bus18 = new Bus();
        Bus bus19 = new Bus();
        Bus bus20 = new Bus();

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        service.execute(bus1);
        service.execute(bus2);
        service.execute(bus3);
        service.execute(bus4);
        service.execute(bus5);
        service.execute(bus6);
        service.execute(bus7);
        service.execute(bus8);
        service.execute(bus9);
        service.execute(bus10);
        service.execute(bus11);
        service.execute(bus12);
        service.execute(bus13);
        service.execute(bus14);
        service.execute(bus15);
        service.execute(bus16);
        service.execute(bus17);
        service.execute(bus18);
        service.execute(bus19);
        service.execute(bus20);
        service.shutdown();
    }
}
