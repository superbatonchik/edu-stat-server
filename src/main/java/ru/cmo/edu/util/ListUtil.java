package ru.cmo.edu.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by to on 11.07.2017.
 */
public class ListUtil {
    public static <T> List<T> IterableToList(Iterable<T> iterable) {
        return StreamSupport
                .stream(iterable.spliterator(), true)
                .collect(Collectors.toList());
    }
}
