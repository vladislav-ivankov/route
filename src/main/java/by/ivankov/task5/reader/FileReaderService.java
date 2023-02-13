package by.ivankov.task5.reader;

import by.ivankov.task5.entity.BusStop;
import by.ivankov.task5.exception.RouteException;

import java.util.ArrayDeque;

public interface FileReaderService {
    ArrayDeque<BusStop> reader(String fileName) throws RouteException;
}
