package by.ivankov.task5.reader;

import by.ivankov.task5.entity.Stop;
import by.ivankov.task5.exception.RouteException;

import java.util.ArrayDeque;

public interface FileReaderService {
    ArrayDeque<Stop> reader(String fileName) throws RouteException;
}
