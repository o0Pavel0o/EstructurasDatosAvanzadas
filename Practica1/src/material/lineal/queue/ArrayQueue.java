package material.lineal.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import javax.management.RuntimeErrorException;

public class ArrayQueue <E> implements Queue<E> {
	
	private E[] elementos;
	private int principio, fin;
	private int tam;
	
	public ArrayQueue (int capacidad) {
		tam = 0;
		principio = 0;
		fin = 0;
		elementos = (E[]) new Object [capacidad];
		
	}
	
	public int size() {
		return tam;
	}
	/**
	 * Returns whether the queue is empty.
	 * @return true if the queue is empty, false otherwise.
	 */
	 public boolean isEmpty() {
		 return (tam==0);
	 }
	/**
	 * Inspects the element at the front of the queue.
	 * @return element at the front of the queue.
	 */
	 E front() throws IllegalStateException{
		 if (isEmpty()) {
	            throw new IllegalStateException();
	        }

	        return elementos[principio];
	 }
	/**
	 * Inserts an element at the rear of the queue.
	 * @param element new element to be inserted.
	 */
	 void enqueue (final E element) {
		 if(tam==elementos.length) {
			 throw new RuntimeException("Esta llena");
		 }
		 if(tam!=0) {
			 fin = (fin+1)%elementos.length;
		 }
		 elementos[fin] = element;
		 tam++;
	 }
	/**
	 * Removes the element at the front of the queue.
	 * @return element removed.
	 */
	 E dequeue() throws IllegalStateException{
		 if(tam==0) {
			 throw new RuntimeErrorException(null, "Es vacía");
		 }
		 E aux = elementos[principio];
		 principio = (principio+1)%elementos.length;
		 tam--;
		return aux;
	 }

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	@Override
	public void clear() {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public boolean add(E e) {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	@Override
	public E element() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public boolean offer(E e) {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

	@Override
	public E peek() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public E poll() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	public E remove() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

}
