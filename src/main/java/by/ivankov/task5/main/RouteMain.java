package by.ivankov.task5.main;


import by.ivankov.task5.entity.Bus;
import by.ivankov.task5.entity.BusGenerator;
import by.ivankov.task5.entity.Route;
import by.ivankov.task5.entity.Stop;
import by.ivankov.task5.exception.RouteException;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RouteMain {
    public static void main(String[] args) throws RouteException {
        Route route = Route.getInstance();
        BusGenerator busGenerator = new BusGenerator(route, 20);

        List<Stop> stops = new ArrayList<>();
        route.addStop(new Stop());
        route.addStop(new Stop());
        route.addStop(new Stop());
        route.addStop(new Stop());
        route.addStop(new Stop());
        Bus bus = new Bus();
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        service.execute(busGenerator);
        bus.start();
        service.shutdown();
    }
}
