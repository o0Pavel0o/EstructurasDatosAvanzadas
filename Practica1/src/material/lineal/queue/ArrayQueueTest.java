package material.lineal.queue;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;




public class ArrayQueueTest {

	@Test
	public void testArrayQueue() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(5);
		
		 assertEquals(0, queue.size());
		 queue.enqueue(1);
		 queue.enqueue(5);
		  // [1, 5]
		 assertEquals(2, queue.size());
		 queue.enqueue(3);
		 queue.enqueue(4);
		  // [1,3,4,5]
		 assertEquals(4, queue.size());
		 queue.dequeue();
		 queue.dequeue();
	       // [3,4]
	      assertEquals(2, queue.size());
	}

	@Test
	public void testSize() {
		 ArrayQueue<Integer> stack = new ArrayQueue<Integer>(5);
	        assertEquals(stack.size(), 0);
	        for (int i=0;i<5;i++) {
	            stack.enqueue(i);
	            assertEquals(stack.size(), i+1);
	        }
	}

	@Test
	public void testIsEmpty() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(5);
		assertTrue(queue.isEmpty());
		queue.enqueue(7);
		queue.dequeue();
		assertTrue(queue.isEmpty());
	}

	@Test(expected = IllegalStateException.class)
	public void testFront()  {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(7);
		queue.front();
	}

	@Test
	public void testEnqueue() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(5);
        for (int i=0;i<5;i++) {
        	queue.enqueue(i);
        }
        assertEquals(queue.size(), 5);
		
	}

	@Test(expected = IllegalStateException.class)
	public void testDequeue() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(5);
		for (int i=0;i<5;i++) {
			queue.enqueue(i);
        }
		for (int i=0;i<5;i++) {
			queue.dequeue();
        }
        assertEquals(queue.size(), 0);
		
	}
}