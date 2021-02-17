package material.lineal.queue;

/**
 * Interface for a queue: a collection of elements that are
 * inserted and removed according to the first-in first-out
 * principle.
 *
 * @author Jos� F. V�lez
 * @author Abraham Duarte
**/
public interface Queue<E>{
/**
 * Returns the number of elements in the queue.
 * @return number of elements in the queue.
 */
 int size();
/**
 * Returns whether the queue is empty.
 * @return true if the queue is empty, false otherwise.
 */
 boolean isEmpty();
/**
 * Inspects the element at the front of the queue.
 * @return element at the front of the queue.
 */
 E front() throws IllegalStateException;
/**
 * Inserts an element at the rear of the queue.
 * @param element new element to be inserted.
 */
 void enqueue (final E element);
/**
 * Removes the element at the front of the queue.
 * @return element removed.
 */
 E dequeue() throws IllegalStateException;
 
}