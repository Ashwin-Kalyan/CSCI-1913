// CSCI 1913 - Lab 12 
// @author: Ashwin Kalyan

/**
 * CacheBlockQueueNode class represents a node in the CacheBlockQueue.
 * It contains the data, a count of how many times the data has been added to the queue,
 * and a reference to the next node in the queue.
 */
public class CacheBlockQueueNode<T> {
    private T data;
    private int count;
    private CacheBlockQueueNode<T> next;

    /**
     * Constructor for CacheBlockQueueNode.
     * Initializes the data, next node reference, and count.
     * @param data - the data to be stored in the node
     * @param next - the reference to the next node in the queue
     */
    public CacheBlockQueueNode(T data, CacheBlockQueueNode<T> next) {
        this.data = data;
        this.next = next;
        this.count = 1;
    }

    public T getData() {
        return data;
    }

    public int getCount() {
        return count;
    }

    public CacheBlockQueueNode<T> getNext() {
        return next;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setNext(CacheBlockQueueNode<T> next) {
        this.next = next;
    }
}
