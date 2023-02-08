package by.ivankov.task5.main;


import by.ivankov.task5.entity.Bus;
import by.ivankov.task5.entity.Route;
import by.ivankov.task5.entity.Stop;
import by.ivankov.task5.exception.RouteException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RouteMain {
    public static void main(String[] args) throws RouteException {

        Route route = Route.getInstance();
        Bus bus = new Bus(Bus.StateBus.UNLOAD_AND_LOAD, 45);
        Bus bus1 = new Bus(Bus.StateBus.UNLOAD, 53);
        Bus bus2 = new Bus(Bus.StateBus.UNLOAD_AND_LOAD, 45);
        Bus bus3 = new Bus(Bus.StateBus.UNLOAD, 47);
        Bus bus4 = new Bus(Bus.StateBus.UNLOAD_AND_LOAD, 47);
        Bus bus5 = new Bus(Bus.StateBus.UNLOAD, 77);
        Bus bus6 = new Bus(Bus.StateBus.UNLOAD_AND_LOAD, 88);
        Bus bus7 = new Bus(Bus.StateBus.UNLOAD_AND_LOAD, 43);
        Bus bus8 = new Bus(Bus.StateBus.UNLOAD, 13);
        Bus bus9 = new Bus(Bus.StateBus.UNLOAD_AND_LOAD, 47);

        List<Stop> stops = new ArrayList<>();
        route.addStop(new Stop());
        route.addStop(new Stop());
        route.addStop(new Stop());
        route.addStop(new Stop());
        route.addStop(new Stop());
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        service.execute(bus);
        service.shutdown();
    }
}
