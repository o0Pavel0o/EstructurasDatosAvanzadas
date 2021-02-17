package material.lineal.queue;

import static org.junit.Assert.*;

import org.junit.Test;


public class LinkedQueueTest {
	

@Test
public void testLinkedQueue() {
	
	LinkedQueue<Integer> queue =  new LinkedQueue<Integer>() {
		
		@Override
		public Integer front() throws IllegalStateException {
			return null;
		}
	};

	 for (int i=0;i<5;i++) {
		queue.enqueue(i);
		System.out.println(queue);
	}
			 

	 for (int i=0;i<5;i++) {
		queue.dequeue();
		System.out.println(queue);
	}

}

@Test
public void testIsEmpty() {
	LinkedQueue<Integer> queue = null;
	assertTrue(queue.isEmpty());
	queue.enqueue(7);
   equals(queue.isEmpty());
}

@Test
public void testSize() {
	LinkedQueue<Integer> queue = null;
    assertEquals(queue.size(), 0);
    for (int i=0;i<5;i++) {
    	queue.enqueue(i);
        equals(queue.size());
    }
}

@Test
public void testTop() {
	LinkedQueue<Integer> queue = null;
	queue.enqueue(1);
    equals(queue.dequeue());
    queue.enqueue(2);
    equals(queue.dequeue());
}

@Test
public void testEnqueue() {
	LinkedQueue<Integer> queue = null;
	 for (int i=0;i<5;i++) {
		queue.enqueue(i);
        assertEquals(queue.size(), 0);
    }
}

@Test
public void testDequeue() {
	LinkedQueue<Integer> queue = null;
	queue.enqueue(5);
	queue.enqueue(7);
    queue.dequeue();
    queue.dequeue();
}

}

