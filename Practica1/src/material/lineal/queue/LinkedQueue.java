package material.lineal.queue;

public abstract class LinkedQueue<E> implements Queue<E> {

	private Node<E> top;
	private int size;
	
	private class Node<T> {

		private final T element; 
		private final Node next; 

		public Node(T e, Node n) {
			element = e;
			next = n;
		}

		public T getElement() {
			return element;
		}

		public Node getNext() {
			return next;
		}

	}

	public void LinkedStack() {
		top = null;
		size = 0;
	}

	public boolean isEmpty() {
		return (top == null);
	}

	public int size() {
		return size;
	}

	public E top() {
		if (isEmpty()) {
			return null;
		}
		return top.getElement();
	}

	public void enqueue(E info) {
		Node<E> n = new Node<E>(info, top);
		top = n;
		size++;
	}

	public E dequeue() {
		E info;
		if (isEmpty()) {
			return null;
		} else {
			info = top.getElement();
			top = top.getNext();
			size--;
			return info;
		}
	}

}
