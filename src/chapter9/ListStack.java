package chapter9;

import java.util.LinkedList;

public class ListStack<E> implements Stack<E> {

    private LinkedList<E> data = new LinkedList<>();

    @Override
    public void push(E e) {
        data.add(e);
    }

    @Override
    public E pop() {
        return data.removeLast();
    }

    @Override
    public E top() {
        return data.getLast();
    }

    @Override
    public int size() {
        return data.size();
    }

}
