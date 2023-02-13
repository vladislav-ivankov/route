package by.ivankov.task5.entity;


public class BusGenerator implements Runnable {
    private int numberOfBusses;

    public BusGenerator(int numberOfBusses) {
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfBusses; i++) {
            Bus bus = new Bus();
            System.out.println("bus" +i);
        }
    }
}
