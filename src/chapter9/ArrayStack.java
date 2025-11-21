package chapter9;

public class ArrayStack<E> implements Stack<E> {

    @SuppressWarnings("unchecked")
    private E data[] = (E[]) new Object[100];
    private int size = 0; // the real size of the stack

    @Override
    public void push(E e) {
        if (size == data.length) {
            data = java.util.Arrays.copyOf(data,data.length*2);
        }
        data[size++] = e;
    }

    @Override
    public E pop() {
        return data[--size];
    }

    @Override
    public E top() {
        return data[size-1];
    }

    @Override
    public int size() {
        return size;
    }

}
