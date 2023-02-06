package by.ivankov.task5.util;

public class BusIdGenerator {
    private static int id;
    private BusIdGenerator(){
    }
    public static int idGenerator(){
        return ++id;
    }
}
