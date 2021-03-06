// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
package synthesizer;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] ringBuffer;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        ringBuffer = (T[]) new Object[capacity];    //more about the syntax at http://www.tothenew.com/blog/why-is-generic-array-creation-not-allowed-in-java/
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.

        //Checks to see if the list is full
        if (isFull()) {
            throw new RuntimeException("The array is full");
        }
        ringBuffer[last] = x;
        // Update the last variable to work in a circular array fashion
        // Modulo with the capacity is used to enforce the ringbuffer behavior
        last = (last + 1) % this.capacity;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update first
        if (isEmpty()) {
            throw new RuntimeException("The list is empty");
        }

        T temp = ringBuffer[first];
        // Updating the first variable to work in a circular array fashion
        // Modulo with the capacity is used to enforce the ring buffer behavior
        first = (first + 1) % this.capacity;
        fillCount--;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return ringBuffer[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
