package javafoundations;
import javafoundations.LinkedMaxHeap;
import javafoundations.exceptions.*;

/**
 * Represents a heap representation of a queue.
 *
 * @author Kelly Cao, Alexa Halim, Marleigh Ausbrooks
 * @version December 13, 2022
 */
public class PriorityQueue<T extends Comparable<T>> implements Queue<T>
{
    private LinkedMaxHeap<T> heap;
    /**
     * Creates a new, empty priority queue.
     */
    public PriorityQueue() {
        heap = new LinkedMaxHeap<T>();
    }

    /**
     * Adds the specified element to the rear of the queue.
     */
    public void enqueue (T element) {
        heap.add(element);
    }

    /**
     * Removes and returns the element at the front of the queue.
     * 
     * @returns the element at the front of the queue
     */
    public T dequeue() {
        try {
            T temp = heap.removeMax();
            return temp;
        } catch (EmptyCollectionException ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Returns a reference to the element at the front of the queue
     * without removing it.
     * 
     * @returns the element at the front of the queue
     */
    public T first() {
        return heap.getMax();
    }

    /**
     * Returns true if the queue contains no elements and false otherwise.
     * 
     * @returns the boolean whether or not the queue is empty
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Returns the number of elements in the queue.
     * 
     * @returns the number of elements in the queue
     */
    public int size() {
        return heap.size();
    }
    
    

    /**
     * Returns a string representation of the queue.
     * 
     * @returns the string representation of the queue
     */
    public String toString() {
        return heap.toString();
    }
}
