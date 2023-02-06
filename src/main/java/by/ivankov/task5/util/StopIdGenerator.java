package by.ivankov.task5.util;

public class StopIdGenerator {
    private static int id;
    private StopIdGenerator(){
    }
    public static int idGenerator(){
        return ++id;
    }
}
