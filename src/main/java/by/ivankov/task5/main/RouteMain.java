package by.ivankov.task5.main;


import by.ivankov.task5.entity.Bus;
import by.ivankov.task5.entity.Route;
import by.ivankov.task5.entity.Stop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RouteMain {
    public static void main(String[] args){
        Logger logger = LogManager.getLogger();
        Route route = Route.getInstance();

        ArrayDeque<Stop> stops = new ArrayDeque<>();
        stops.add(new Stop());
        stops.add(new Stop());
        stops.add(new Stop());
        stops.add(new Stop());
        stops.add(new Stop());
        route.setStops(stops);

        Bus bus1 = new Bus(Bus.StateBus.UNLOAD_AND_LOAD);
        Bus bus2 = new Bus(Bus.StateBus.UNLOAD);
        Bus bus3 = new Bus(Bus.StateBus.UNLOAD);
        Bus bus4 = new Bus(Bus.StateBus.UNLOAD_AND_LOAD);
        Bus bus5 = new Bus(Bus.StateBus.UNLOAD);
        Bus bus6 = new Bus(Bus.StateBus.UNLOAD_AND_LOAD);
        Bus bus7 = new Bus(Bus.StateBus.UNLOAD);
        Bus bus8 = new Bus(Bus.StateBus.UNLOAD);
        Bus bus9 = new Bus(Bus.StateBus.UNLOAD_AND_LOAD);

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
        service.shutdown();
    }
}
