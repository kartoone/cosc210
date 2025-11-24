package chapter9;

import java.util.LinkedList;

public class ListQueue<E> implements Queue<E> {

    // front of the queue will be at the beginning of the list
    // rear of the queue will be at the end of the list
    private LinkedList<E> data = new LinkedList<>();

    @Override
    public void enqueue(E e) {
        data.addLast(e);
    }

    @Override
    public E dequeue() {
        return data.removeFirst();
    }

    @Override
    public E front() {
        return data.getFirst();
    }

    @Override
    public int size() {
        return data.size();
    }

}
