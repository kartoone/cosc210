package chapter9;

public interface Queue<E> {

    public void enqueue(E e); // adds to the rear of the queue
    public E dequeue(); // removes from the front of the queue
    public E front(); // returns (without removing) whatever is at the front of the queue
    public int size();

}
