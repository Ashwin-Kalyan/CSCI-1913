// CSCI 1913 - Lab 12
// @author: Ashwin Kalyan

/**
 * Creates a queue of CacheBlockQueueNode objects.
 * The queue is implemented as a linked list.
 * The queue is used to store data in a FIFO order.
 */
public class CacheBlockQueue<T> {
    private CacheBlockQueueNode<T> front;
    private CacheBlockQueueNode<T> rear;
    private int size;

    /**
     * Constructor for CacheBlockQueue.
     * Initializes the front and rear pointers to null and size to 0
     */
    public CacheBlockQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * Adds a new element to the end of the queue.
     * If the queue is empty, the new element becomes both the front and rear.
     * If the new element is the same as the last element, it increments the count of the last element.
     * If the new element is different, it creates a new node and adds it to the end of the queue.
     * @param data - the data to be added to the queue
     */
    public void enqueue(T data) {
        if (rear == null) {
            // Queue is empty, create new node
            CacheBlockQueueNode<T> newNode = new CacheBlockQueueNode<>(data, null);
            front = newNode;
            rear = newNode;
            size++;
        } else if (rear.getData().equals(data)) {
            // Same as last element, just increment count
            rear.setCount(rear.getCount() + 1);
            size++;
        } else {
            // Different element, create new node
            CacheBlockQueueNode<T> newNode = new CacheBlockQueueNode<>(data, null);
            rear.setNext(newNode);
            rear = newNode;
            size++;
        }
    }

    /**
     * Returns the data of the front element in the queue.
     * @return - the data of the front element, or null if the queue is empty
     */
    public T front() {
        if (isEmpty()) return null; 
        return front.getData();
    }

    /**
     * Removes the front element from the queue.
     * If the front element has a count greater than 1, it decrements the count.
     * If the count is 1, it removes the front node and updates the front pointer.
     * If the queue becomes empty, it also sets the rear pointer to null.
     * @return - the data of the removed element, or null if the queue is empty
     */
    public T dequeue() {
        if (isEmpty()) return null; 
        T data = front.getData();

        if (front.getCount() > 1) {
            front.setCount(front.getCount() - 1);        
        } else {
            front = front.getNext();
            if (front == null) rear = null;
        }
        // Decrease size only if we removed the front node
        size--; 
        return data;
    }

    /**
     * Returns the count of the front element in the queue.
     * @return - the count of the front element, or 0 if the queue is empty
     */
    public int frontOfLineRepeatCount() {
        if (front == null) return 0; 
        return front.getCount();
    }

    public int getSize() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a string representation of the queue.
     * The format is "data:count, data:count, ..."
     * @return - a string representation of the queue
     */
    @Override
    public String toString() {
        if (isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        CacheBlockQueueNode<T> current = front;
        boolean first = true;

        while (current != null) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(current.getData()).append(":").append(current.getCount());
            current = current.getNext();
            first = false;
        }
        
        return sb.toString();
    }
}