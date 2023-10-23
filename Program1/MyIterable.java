package org.example;

import java.util.*;

/**
 * The MyIterable class implements the Iterable interface. It is also a generic class whose constructor takes an
 * array of any type and reverses
 * @param <T>
 */
public class MyIterable<T> implements Iterable<T> {
    private List<T> list;
    public MyIterable(T [] t) {
        list = Arrays.asList(t);
        Collections.sort(list,Collections.reverseOrder());
    }
    @Override
    public Iterator<T> iterator() {
        return list.listIterator();
    }
}