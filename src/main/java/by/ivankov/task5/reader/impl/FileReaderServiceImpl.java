package by.ivankov.task5.reader.impl;

import by.ivankov.task5.entity.BusStop;
import by.ivankov.task5.exception.RouteException;
import by.ivankov.task5.reader.FileReaderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayDeque;

public class FileReaderServiceImpl implements FileReaderService {
    static Logger logger = LogManager.getLogger();
    private static final String SEPARATOR = "\\s+";

    @Override
    public ArrayDeque<BusStop> reader(String fileName) throws RouteException {// TODO: 05.02.2023
        ArrayDeque<BusStop> busStops = new ArrayDeque<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            String[] data = line.split(SEPARATOR);
            int numberOfStops = Integer.parseInt(data[0]);
            int numberOfBusses = Integer.parseInt(data[1]);
            for (int i = 0; i < data.length; i++) {
//                stops.add(new Stop(numberOfStops, numberOfBusses));
            }
        } catch (FileNotFoundException e) {
            throw new RouteException(e);
        } catch (IOException e) {
            throw new RouteException(e);
        }
        return busStops;
    }
}
